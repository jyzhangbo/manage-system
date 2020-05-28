package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListDeviceTaskResp {

    private String deviceName;
    private String deviceNum;

}
