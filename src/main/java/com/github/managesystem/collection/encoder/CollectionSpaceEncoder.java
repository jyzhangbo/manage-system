package com.github.managesystem.collection.encoder;

import com.github.managesystem.collection.model.ControlModel;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author:zhangbo
 * @Date:2020/6/6 18:19
 */
public class CollectionSpaceEncoder extends MessageToByteEncoder<ControlModel> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ControlModel msg, ByteBuf out) throws Exception {
        byte[] b = {0x01};
        out.readBytes(b);
    }
}
