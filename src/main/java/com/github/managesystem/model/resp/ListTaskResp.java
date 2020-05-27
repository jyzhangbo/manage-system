package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/21 17:29
 */
@Data
public class ListTaskResp {

    private Long total;

    private List<ListTaskInfo> tasks = new ArrayList<>();

}
