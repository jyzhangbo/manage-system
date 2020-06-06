package com.github.managesystem.collection.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:zhangbo
 * @Date:2020/6/3 15:29
 */
@Data
public class DeviceAttr {

    private String dataType;
    private String time;
    private Map<String, String> data;

    public DeviceAttr() {
        data = new HashMap<String, String>();
    }

    public boolean addAttr(String k, String v) {

        if (data.containsKey(k)) {
            return false;
        }

        data.put(k, v);
        return true;
    }

    public boolean removeAttr(String k) {

        if (data.containsKey(k)) {
            data.remove(k);
            return true;
        }

        return false;
    }

}
