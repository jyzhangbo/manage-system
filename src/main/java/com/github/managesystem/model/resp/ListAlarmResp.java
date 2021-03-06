package com.github.managesystem.model.resp;

import com.github.managesystem.entity.AlarmLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/19 15:30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListAlarmResp {

    private List<AlarmLog> infos;

    private Long total;

}
