package com.github.managesystem.model.req;

import lombok.Data;

@Data
public class EditUserReq {

    private String loginName;

    private String password;

    private String companyName;

    private String phone;

}
