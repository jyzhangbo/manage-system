package com.github.managesystem.collection.handle.decoder;

import com.github.managesystem.collection.model.ObjectDecoderState;
import com.github.managesystem.collection.model.ProtocolDecodeOutData;
import com.github.managesystem.util.TransformUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.ReferenceCountUtil;
import org.nutz.lang.Strings;

import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/6/3 12:04
 */
public class ProtocolReceiveDecoder extends ReplayingDecoder<ObjectDecoderState> {

    private int length;

    private ProtocolDecodeOutData body = new ProtocolDecodeOutData();

    public ProtocolReceiveDecoder(){
        super(ObjectDecoderState.READ_HEADER);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {


        switch (state()) {
            case READ_HEADER:
                byte[] b = new byte[1];
                b[0] = in.readByte();
                String header = TransformUtils.byteToHexString(b);
                if(Strings.equalsIgnoreCase(header,"AA")) {
                    checkpoint(ObjectDecoderState.READ_DEVID);
                }
                return;
            case READ_DEVID:
                ByteBuf byteBuf = in.readBytes(12);
                byte[] devId = new byte[12];
                byteBuf.readBytes(devId);
                body.devNum = TransformUtils.byteToHexString(devId);
                ReferenceCountUtil.release(byteBuf);
                checkpoint(ObjectDecoderState.READ_VERSION);
                return;
            case READ_VERSION:
                in.readByte();
                checkpoint(ObjectDecoderState.READ_DEVTYPE);
                return;
            case READ_DEVTYPE:
                in.readShort();
                checkpoint(ObjectDecoderState.READ_NUM);
                return;
            case READ_NUM:
                in.readByte();
                checkpoint(ObjectDecoderState.READ_COMMAND);
                return;
            case READ_COMMAND:
                byte[] command = new byte[1];
                command[0] = in.readByte();
                body.command = TransformUtils.byteToHexString(command);
                checkpoint(ObjectDecoderState.READ_LENGTH);
                return;
            case READ_LENGTH:
                short i = in.readShort();
                length = Short.toUnsignedInt(i);
                checkpoint(ObjectDecoderState.READ_CONTENT);
                return;
            case READ_CONTENT:
                ByteBuf content = in.readBytes(length);
                byte[] contentByte = new byte[length];
                content.readBytes(contentByte);
                ReferenceCountUtil.release(content);
                body.content = contentByte;
                checkpoint(ObjectDecoderState.READ_CHECK);
                return;
            case READ_CHECK:
                in.readByte();
                checkpoint(ObjectDecoderState.READ_END);
                return;
            case READ_END:
                in.readByte();
                checkpoint(ObjectDecoderState.READ_HEADER);
                out.add(body);
                return;
            default:
                throw new Error("no ... ");

        }

    }

    public static void main(String[] args) {
        short i = 1;
        int length = Short.toUnsignedInt(i);
        System.out.println(length);
    }
}
