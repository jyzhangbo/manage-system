package com.github.managesystem.collection.handle.decoder;

import com.github.managesystem.collection.model.ProtocolDecodeOutData;
import com.github.managesystem.util.TransformUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import org.nutz.lang.Encoding;
import org.nutz.lang.Strings;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/6/4 16:19
 */
@Slf4j
public class ByteToValueDecoder extends MessageToMessageDecoder<ProtocolDecodeOutData> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ProtocolDecodeOutData msg, List<Object> out) throws Exception {
        if(Strings.equals(msg.command,"01")) {
            msg.clearDeviceAttr();
            ByteArrayInputStream in = new ByteArrayInputStream(msg.content);
            byte[] length = new byte[1];
            in.read(length);
            int len = new Integer(length[0]);

            for (int i = 0; i < len; i++) {
                byte[] type = new byte[1];
                in.read(type);

                if (Integer.valueOf(type[0]) == 0x00) {
                    byte[] temperature = new byte[23];
                    in.read(temperature);
                    msg.addDeviceAttr(TemperatureDecoder.decode(temperature));
                } else if (Integer.valueOf(type[0]) == 0x01) {
                    in.skip(1);
                } else if (Integer.valueOf(type[0]) == 0x81) {
                    in.skip(7);
                } else if(Integer.valueOf(type[0]) == 0x8A){
                    in.skip(1);
                } else if(Integer.valueOf(type[0]) == 0x8B){
                    in.skip(1);
                } else if(Integer.valueOf(type[0]) == 0x8C){
                    in.skip(2);
                } else if(Integer.valueOf(type[0]) == 0x8D){
                    in.skip(14);
                } else if(Integer.valueOf(type[0]) == 0x85){
                    in.skip(1);
                } else if(Integer.valueOf(type[0]) == 0x86){
                    in.skip(2);
                } else if(Integer.valueOf(type[0]) == 0x90){
                    in.skip(1);
                }
            }
            out.add(msg);
        } else {
            return;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("消息转换异常:{}",cause.getMessage());
        if(ctx.channel().isActive()){
            ctx.close();
        }
    }

    public static void main(String[] args) throws Exception{
        byte[] b = {0x59,0x32,0x38,0x58,0x56,0x50,};
        System.out.println(Encoding.CHARSET_ASCII.decode(ByteBuffer.wrap(b)).toString());

    }
}
