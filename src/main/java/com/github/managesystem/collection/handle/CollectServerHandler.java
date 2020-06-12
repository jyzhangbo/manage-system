package com.github.managesystem.collection.handle;

import com.github.managesystem.collection.model.CommandEnum;
import com.github.managesystem.collection.model.ProtocolDecodeOutData;
import com.github.managesystem.collection.model.ResponseModel;
import com.github.managesystem.entity.DeviceControlRecord;
import com.github.managesystem.service.IDeviceDataService;
import com.github.managesystem.util.TimeUtils;
import com.github.managesystem.util.TransformUtils;
import com.github.managesystem.util.WebContextUtils;
import com.google.common.primitives.Bytes;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Encoding;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class CollectServerHandler extends SimpleChannelInboundHandler<ProtocolDecodeOutData> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtocolDecodeOutData msg) throws Exception {
        log.info("数据：{}",Json.toJson(msg, JsonFormat.tidy()));
        IDeviceDataService deviceDataService = WebContextUtils.findBean(IDeviceDataService.class);
        List<DeviceControlRecord> records = deviceDataService.putData(msg);
        ResponseModel resp = new ResponseModel();
        resp.setDevNum(msg.devNum);

        if(records.size() > 0) {
            resp.setCommand(CommandEnum.COMMAND_86.getValue());
            resp.setRecords(records);
            ctx.channel().writeAndFlush(resp);
        }

        resp.setCommand(CommandEnum.COMMAND_81.getValue());
        ctx.channel().writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("消息处理异常:{}",cause.getMessage());
        ctx.close();
    }

}
