package com.github.managesystem.model.resp;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/19 9:48
 */
@Data
public class ListDeviceAdminResp {

    private List<ListDeviceAdminInfo> infos = new ArrayList<>();

    private Long total;

}
