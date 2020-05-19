package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/18 17:43
 */
@Data
@Builder
public class ListUserResp {

    private List<ListUserInfo> infos;

    private Integer total;

}
