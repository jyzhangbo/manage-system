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

    public String devNum;

    public List<DeviceAttr> attrs;

    public String command;

    public byte[] content;

    public String contentStr;

    public ProtocolDecodeOutData() {
        attrs = new ArrayList<DeviceAttr>();
    }

    public void addDeviceAttr(DeviceAttr attr) {
        attrs.add(attr);
    }

    public void clearDeviceAttr() {
        attrs.clear();
    }

}
