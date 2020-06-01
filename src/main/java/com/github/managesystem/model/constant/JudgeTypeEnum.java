package com.github.managesystem.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author kai
 * @date 2020/5/29
 * 告警判断
 */
@AllArgsConstructor
@Getter
public enum JudgeTypeEnum {
    JUDGE_TYPE_1(1, "="),
    JUDGE_TYPE_2(2, ">"),
    JUDGE_TYPE_3(3, "<"),
    JUDGE_TYPE_4(4,"≥"),
    JUDGE_TYPE_5(5,"≤");

    private int code;
    private String desc;

    public static String valueOf(int code) {
        for (JudgeTypeEnum fsEnum : JudgeTypeEnum.values()) {
            if (fsEnum.getCode() == code) {
                return fsEnum.desc;
            }
        }
        return null;
    }
}
