package com.github.managesystem.model.req;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/6/24 15:46
 */
@Data
public class ControlDeviceReq {



    private String deviceNum;
    private ControlDeviceTemp tempControl ;
    private String modelType;
    private String tapControl1;
    private String tapControl2;
    private List<String> probeType1;
    private List<String> probeType2;
    private Integer type;
    private String manualControl1;
    private String manualControl2;

    public ControlDeviceReq(){
        this.tempControl = new ControlDeviceTemp();
        this.probeType1=new ArrayList<>();
        this.probeType2=new ArrayList<>();
    }


}
