package com.github.managesystem.collection.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/6/6 14:39
 */
@Data
public class ProtocolDecodeOutData {

    private byte[] devId;

    private String devNum;

    private List<DeviceAttr> attrs;

    public byte[] command;

    public ProtocolDecodeOutData() {
        attrs = new ArrayList<DeviceAttr>();
    }

    public void addDeviceAttr(DeviceAttr attr) {
        attrs.add(attr);
    }

}
