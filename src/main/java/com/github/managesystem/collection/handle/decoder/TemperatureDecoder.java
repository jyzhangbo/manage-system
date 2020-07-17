package com.github.managesystem.collection.handle.decoder;

import com.github.managesystem.collection.model.DeviceAttr;
import com.github.managesystem.collection.model.ProtocolDecodeOutData;
import com.github.managesystem.model.req.ControlDeviceTemp;
import com.github.managesystem.util.TimeUtils;
import com.github.managesystem.util.TransformUtils;
import com.google.common.primitives.Bytes;

import java.time.LocalDateTime;

/**
 * @Author:zhangbo
 * @Date:2020/6/6 14:38
 */
public class TemperatureDecoder{

    /**
     * 定义数据类型
      */
    public static final String uploadData = "00";

    //  定义数据各项数据长度
    private static final int totalLen = 23;
    private static final int timeLen = 7;

    //  定义键值
    private static final String[] KEY_T = {"T1","T2","T3","T4","T5","T6","T7","T8"};

    public static DeviceAttr decode(byte[] in) {
        DeviceAttr attr = new DeviceAttr();
        short year = TransformUtils.byteArrayToShort(in);
        int month = (int) in[2];
        int day = (int) in[3];
        int hour = (int) in[4];
        int minute = (int) in[5];
        int second = (int) in[6];

        attr.setDataType(uploadData);
        attr.setTime(TimeUtils.formatTime(LocalDateTime.of(year,month,day,hour,minute,second)));

        for (int i = 0, index = timeLen; index < totalLen; ) {

            short t = TransformUtils.byteArrayToShort(in, index);
            index = index + 2;
            int v = t & 0x7FFF;

//          计算整数部分
            int num1 = v / 10;
//          计算小数部分
            int num2 = v % 10;
            String value = String.valueOf(num1) + "." + String.valueOf(num2);
            if ((t & 0x8000) == 0x8000) { //温度为负值
                value = "-" + value;
            }
            attr.addAttr(KEY_T[i++], value);
        }
        return attr;
    }


    public static String encode(ControlDeviceTemp temp){

        short startTime = Double.valueOf(temp.getStartTime() * 10).shortValue();
        short startTemp = Double.valueOf(temp.getStartTemp() * 10).shortValue();
        short upSpeed = Double.valueOf(temp.getUpSpeed() * 10).shortValue();
        short constantTemp = Double.valueOf(temp.getConstantTemp() * 10).shortValue();
        short constantTime = Double.valueOf(temp.getConstantTime() * 10).shortValue();
        short downSpeep = Double.valueOf(temp.getDownSpeed() * 10).shortValue();
        short endTemp = Double.valueOf(temp.getEndTemp() * 10).shortValue();

        byte[] concat = Bytes.concat(TransformUtils.shortoByteArray(startTime), TransformUtils.shortoByteArray(startTemp),
                TransformUtils.shortoByteArray(upSpeed),TransformUtils.shortoByteArray(constantTemp),
                TransformUtils.shortoByteArray(constantTime),TransformUtils.shortoByteArray(downSpeep)
                ,TransformUtils.shortoByteArray(endTemp));

        return TransformUtils.byteToHexString(concat);
    }

    public static void main(String[] args) {
        System.out.println(Double.valueOf(-3.5*10).shortValue());


    }
}
