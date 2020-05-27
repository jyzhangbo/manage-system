package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceInfo {

    private String deviceNum;
    private String deviceName;
    private String img;


}
