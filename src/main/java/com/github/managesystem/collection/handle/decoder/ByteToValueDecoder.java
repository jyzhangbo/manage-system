package com.github.managesystem.collection.handle.decoder;

import com.github.managesystem.collection.handle.decoder.TemperatureDecoder;
import com.github.managesystem.collection.model.MessageHexBody;
import com.github.managesystem.collection.model.ProtocolDecodeOutData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.nutz.lang.Encoding;

import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/6/4 16:19
 */
public class ByteToValueDecoder extends MessageToMessageDecoder<MessageHexBody> {

    @Override
    protected void decode(ChannelHandlerContext ctx, MessageHexBody msg, List<Object> out) throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(msg.content);
        ProtocolDecodeOutData protocolDecodeOutData = new ProtocolDecodeOutData();
        protocolDecodeOutData.setDevId(msg.devId);
        protocolDecodeOutData.command = msg.command;
        byte[] length = new byte[1];
        in.read(length);
        int len = new Integer(length[0]);

        for(int i = 0; i < len; i++){
            byte[] type = new byte[1];
            in.read(type);

            if(Integer.valueOf(type[0]) == 0x00){
                byte[] temperature = new byte[23];
                in.read(temperature);
                protocolDecodeOutData.addDeviceAttr(TemperatureDecoder.decode(temperature));
            }else if(Integer.valueOf(type[0]) == 0x01){
                in.skip(1);
            }else if(Integer.valueOf(type[0]) == 0x81){
                in.skip(7);
            }
        }
        out.add(protocolDecodeOutData);
    }


    public static void main(String[] args) throws Exception{
        byte[] b = {0x59,0x32,0x38,0x58,0x56,0x50,};
        System.out.println(Encoding.CHARSET_ASCII.decode(ByteBuffer.wrap(b)).toString());

    }
}
