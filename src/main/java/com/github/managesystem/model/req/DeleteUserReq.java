package com.github.managesystem.model.req;

import lombok.Data;

@Data
public class DeleteUserReq {

    private String loginName;

    private String companyName;
}
