package com.github.managesystem.model.constant;

import com.github.managesystem.entity.DeviceData;
import com.github.managesystem.model.resp.ChartYData;
import com.github.managesystem.model.resp.QueryDataCharResp;
import com.github.managesystem.util.TimeUtils;
import lombok.Getter;
import org.nutz.lang.Strings;

import java.util.*;

/**
 * @Author:zhangbo
 * @Date:2020/5/30 15:10
 */
@Getter
public enum  AttributeEnum {

    ATTRIBUTE_T1("attribute_t1","T1"),
    ATTRIBUTE_T2("attribute_t2","T2"),
    ATTRIBUTE_T3("attribute_t3","T3"),
    ATTRIBUTE_T4("attribute_t4","T4"),
    ATTRIBUTE_T5("attribute_t5","T5"),
    ATTRIBUTE_T6("attribute_t6","T6"),
    ATTRIBUTE_T7("attribute_t7","T7"),
    ATTRIBUTE_T8("attribute_t8","T8");

    public String value;

    public String attributeName;

    private AttributeEnum(String attributeName,String value) {

        this.attributeName = attributeName;
        this.value = value;
    }

    public static String getAttributeName(String value){
        for(AttributeEnum attributeEnum : AttributeEnum.values()){
            if(Strings.equals(value,attributeEnum.getValue())){
                return attributeEnum.getAttributeName();
            }
        }
        return "";
    }

    public static Map<String,Double> deviceDataToMap(DeviceData data){
        Map<String,Double> map = new LinkedHashMap<>();
        map.put(AttributeEnum.ATTRIBUTE_T1.value,data.getAttributeT1());
        map.put(AttributeEnum.ATTRIBUTE_T2.value,data.getAttributeT2());
        map.put(AttributeEnum.ATTRIBUTE_T3.value,data.getAttributeT3());
        map.put(AttributeEnum.ATTRIBUTE_T4.value,data.getAttributeT4());
        map.put(AttributeEnum.ATTRIBUTE_T5.value,data.getAttributeT5());
        map.put(AttributeEnum.ATTRIBUTE_T6.value,data.getAttributeT6());
        map.put(AttributeEnum.ATTRIBUTE_T7.value,data.getAttributeT7());
        map.put(AttributeEnum.ATTRIBUTE_T8.value,data.getAttributeT8());
        return map;
    }

    public static QueryDataCharResp deviceDataToChart(List<DeviceData> datas,Map<String,String> header) {
        List<String> xDatas = new ArrayList<>();
        List<ChartYData> yDatas = new ArrayList<>();
        List<Double> t1 = new LinkedList<>();
        List<Double> t2 = new LinkedList<>();
        List<Double> t3 = new LinkedList<>();
        List<Double> t4 = new LinkedList<>();
        List<Double> t5 = new LinkedList<>();
        List<Double> t6 = new LinkedList<>();
        List<Double> t7 = new LinkedList<>();
        List<Double> t8 = new LinkedList<>();
        for(DeviceData data : datas){
            xDatas.add(TimeUtils.formatTime(data.getDataTime()));
            t1.add(data.getAttributeT1());
            t2.add(data.getAttributeT2());
            t3.add(data.getAttributeT3());
            t4.add(data.getAttributeT4());
            t5.add(data.getAttributeT5());
            t6.add(data.getAttributeT6());
            t7.add(data.getAttributeT7());
            t8.add(data.getAttributeT8());
        }

        yDatas.add(ChartYData.builder().name(header.get(ATTRIBUTE_T1.value)).values(t1).build());
        yDatas.add(ChartYData.builder().name(header.get(ATTRIBUTE_T2.value)).values(t2).build());
        yDatas.add(ChartYData.builder().name(header.get(ATTRIBUTE_T3.value)).values(t3).build());
        yDatas.add(ChartYData.builder().name(header.get(ATTRIBUTE_T4.value)).values(t4).build());
        yDatas.add(ChartYData.builder().name(header.get(ATTRIBUTE_T5.value)).values(t5).build());
        yDatas.add(ChartYData.builder().name(header.get(ATTRIBUTE_T6.value)).values(t6).build());
        yDatas.add(ChartYData.builder().name(header.get(ATTRIBUTE_T7.value)).values(t7).build());
        yDatas.add(ChartYData.builder().name(header.get(ATTRIBUTE_T8.value)).values(t8).build());


        return QueryDataCharResp.builder().tableHeader(header).xDatas(xDatas).yDatas(yDatas).build();
    }

}
