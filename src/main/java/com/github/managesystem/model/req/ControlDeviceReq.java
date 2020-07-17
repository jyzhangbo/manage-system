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
    private ControlDeviceTemp tempControl;
    private String modelType;
    private String tapControl1;
    private String tapControl2;
    private List<Integer> probeType1;
    private List<Integer> probeType2;


}
