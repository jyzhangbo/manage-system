package com.github.managesystem.service;

import com.github.managesystem.entity.User;
import com.github.managesystem.model.req.ListCompanyNameReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author:zhangbo
 * @Date:2020/5/28 16:11
 */
@Service
public class BaseService {

    @Autowired
    private IUserService userService;


    public List<String> listCompanyName(ListCompanyNameReq req){
        List<String> companyNames = new ArrayList<>();
        List<User> users = userService.list();
        for (User user : users){
            if(!companyNames.contains(user.getCompanyName())){
                companyNames.add(user.getCompanyName());
            }
        }
        return companyNames;
    }

}
