package com.github.managesystem.collection.handle;

import com.github.managesystem.collection.model.ProtocolDecodeOutData;
import com.github.managesystem.collection.model.ResponseModel;
import com.github.managesystem.service.IDeviceDataService;
import com.github.managesystem.util.TimeUtils;
import com.github.managesystem.util.TransformUtils;
import com.github.managesystem.util.WebContextUtils;
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

@Slf4j
public class CollectServerHandler extends SimpleChannelInboundHandler<ProtocolDecodeOutData> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtocolDecodeOutData msg) throws Exception {
        log.info(Json.toJson(msg, JsonFormat.tidy()));
        msg.setDevNum(Encoding.CHARSET_ASCII.decode(ByteBuffer.wrap(msg.getDevId())).toString());
       // IDeviceDataService deviceDataService = WebContextUtils.findBean(IDeviceDataService.class);
       // deviceDataService.putData(msg);
        ResponseModel resp = new ResponseModel();
        resp.setDevId(msg.getDevId());
        resp.setCommand(msg.getCommand());

        ctx.channel().writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
