package com.github.managesystem.collection.handle.encoder;

import com.github.managesystem.collection.model.ResponseModel;
import com.github.managesystem.util.TransformUtils;
import com.google.common.primitives.Bytes;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author:zhangbo
 * @Date:2020/6/6 18:21
 */
public class ResponseEncoder extends MessageToByteEncoder<ResponseModel> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ResponseModel msg, ByteBuf out) throws Exception {
        StringBuilder start = new StringBuilder()
                .append("AA")
                .append(msg.getDevNum())
                .append("00000605")
                .append(msg.getCommand());
        byte[] startByte = TransformUtils.hexStringToBytes(start.toString());
        StringBuilder content;
        switch (msg.getCommand()){
            case "86":
                content = ControlEncoder.encoder(msg);
                break;
            case "81":
                content = AckEncoder.encoder(msg);
                break;
            default:
                content = new StringBuilder();
                break;
        }

        byte[] contentByte = TransformUtils.hexStringToBytes(content.toString());
        byte[] checkSum = new byte[1];
        checkSum[0] = sumCheck(contentByte, contentByte.length);

        byte[] sizeByte = TransformUtils.shortoByteArray(Short.valueOf(String.valueOf(contentByte.length)));

        byte[] end = {0x16};
        byte[] concat = Bytes.concat(startByte,sizeByte,contentByte,checkSum, end);
        out.writeBytes(concat);
    }

    private byte sumCheck(byte[] b, int len){
        int sum = 0;
        for(int i = 0; i < len; i++){
            sum = sum + b[i];
        }
        if(sum > 0xff){ //超过了255，使用补码（补码 = 原码取反 + 1）
            sum = ~sum;
            sum = sum + 1;
        }
        return (byte) (sum & 0xff);
    }


    public static void main(String[] args) throws Exception{

        if(TransformUtils.hexStringToBytes("86")[0] == 0x86){
            System.out.println("11");
        }
    }
}
