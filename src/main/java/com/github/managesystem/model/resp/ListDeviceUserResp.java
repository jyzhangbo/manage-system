package com.github.managesystem.model.resp;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/28 14:34
 */
@Data
public class ListDeviceUserResp {

    private List<ListDeviceUserInfo> infos = new ArrayList<>();

    private Long total;

}
