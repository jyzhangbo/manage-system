package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/21 17:29
 */
@Data
@Builder
public class ListTaskResp {

    private List<ListTaskInfo> tasks;

}
