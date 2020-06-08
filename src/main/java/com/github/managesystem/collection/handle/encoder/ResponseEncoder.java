package com.github.managesystem.collection.handle.encoder;

import com.github.managesystem.collection.model.ResponseModel;
import com.github.managesystem.entity.Task;
import com.github.managesystem.util.TimeUtils;
import com.github.managesystem.util.TransformUtils;
import com.google.common.primitives.Bytes;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

/**
 * @Author:zhangbo
 * @Date:2020/6/6 18:21
 */
public class ResponseEncoder extends MessageToByteEncoder<ResponseModel> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ResponseModel msg, ByteBuf out) throws Exception {

        String now = TimeUtils.formatTime(LocalDateTime.now());
        String year = TransformUtils.integerStrToHexString(now.substring(0,4));
        String mounth = TransformUtils.integerStrToHexString(now.substring(5,7));
        String day = TransformUtils.integerStrToHexString(now.substring(8,10));

        String hour = TransformUtils.integerStrToHexString(now.substring(11,13));
        String minute = TransformUtils.integerStrToHexString(now.substring(14,16));
        String second = TransformUtils.integerStrToHexString(now.substring(17));

        String message = new StringBuilder()
                .append("AA")
                .append(TransformUtils.byteToHexString(msg.getDevId()))
                .append("0000060581000B029A0181")
                .append(year)
                .append(mounth)
                .append(day)
                .append(hour).append(minute).append(second)
                .append("5516")
                .toString();

        out.writeBytes(TransformUtils.hexStringToBytes(message));
    }

    public static void main(String[] args) throws Exception{
        byte[] a = TransformUtils.hexStringToBytes("AA5932382015504B37470027000000060581000B");

        System.out.println(TransformUtils.byteToHexString(a));
    }
}
