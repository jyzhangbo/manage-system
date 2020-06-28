package com.github.managesystem.collection.handle;

import com.github.managesystem.collection.CollectServer;
import com.github.managesystem.collection.model.CommandEnum;
import com.github.managesystem.collection.model.ProtocolDecodeOutData;
import com.github.managesystem.collection.model.ResponseModel;
import com.github.managesystem.entity.DeviceControlRecord;
import com.github.managesystem.service.IDeviceControlRecordService;
import com.github.managesystem.service.IDeviceDataService;
import com.github.managesystem.util.TimeUtils;
import com.github.managesystem.util.TransformUtils;
import com.github.managesystem.util.WebContextUtils;
import com.google.common.primitives.Bytes;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Encoding;
import org.nutz.lang.Strings;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
public class CollectServerHandler extends SimpleChannelInboundHandler<ProtocolDecodeOutData> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtocolDecodeOutData msg) throws Exception {
        log.info("数据：{}",Json.toJson(msg, JsonFormat.tidy()));

        if(!CollectServer.channelHandlerMap.containsKey(msg.devNum)){
            CollectServer.channelHandlerMap.put(msg.devNum,ctx);
            IDeviceControlRecordService deviceControlRecordService = WebContextUtils.findBean(IDeviceControlRecordService.class);
            List<DeviceControlRecord> records = deviceControlRecordService.getControlRecord(msg);
            ResponseModel resp = new ResponseModel();
            resp.setDevNum(msg.devNum);
            if(records.size() > 0) {
                resp.setCommand(CommandEnum.COMMAND_86.getValue());
                resp.setRecords(records);
                ctx.channel().writeAndFlush(resp);
            }
        }

        if(Strings.equals(msg.command,"01")) {
            IDeviceDataService deviceDataService = WebContextUtils.findBean(IDeviceDataService.class);
            deviceDataService.putData(msg);
        }

        ResponseModel resp = new ResponseModel();
        resp.setDevNum(msg.devNum);
        resp.setCommand(CommandEnum.COMMAND_81.getValue());
        resp.setOldCommand(msg.getCommand());
        ctx.channel().writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("消息处理异常:{}",cause.getMessage());
        if(ctx.channel().isActive()){
            ctx.close();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("{},连接断开",ctx.channel().remoteAddress().toString());
        removeChannnelMap(ctx);
        ctx.channel().close();
    }

    /**
     *删除map中ChannelHandlerContext
     *  */
    private void removeChannnelMap(ChannelHandlerContext ctx){
        for(Map.Entry<String,ChannelHandlerContext> entry : CollectServer.channelHandlerMap.entrySet()){
            if(entry.getValue().equals(ctx)){
                CollectServer.channelHandlerMap.remove(entry.getKey());
            }
        }
    }

}
