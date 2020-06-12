package com.github.managesystem.collection.handle.encoder;

import com.github.managesystem.collection.model.ResponseModel;
import com.github.managesystem.util.TransformUtils;
import com.google.common.primitives.Bytes;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author:zhangbo
 * @Date:2020/6/6 18:21
 */
@Slf4j
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

        log.info("resp data: {}",TransformUtils.byteToHexString(concat));
        out.writeBytes(concat);
    }

    private byte sumCheck(byte[] b, int len){
        int sum = 0;
        for(int i = 0; i < len; i++){
            sum = sum + b[i];
        }

        return (byte) (sum & 0xff);
    }


    public static void main(String[] args) throws Exception{
        ResponseEncoder encoder = new ResponseEncoder();
        byte[] b1 = new byte[1];
        byte[] b = {0x72,0x65,0x01,0x77,0x77};
        b1[0] = encoder.sumCheck(b, b.length);
        System.out.println(TransformUtils.byteToHexString(b1));
    }
}
