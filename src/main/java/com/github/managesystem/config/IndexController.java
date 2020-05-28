package com.github.managesystem.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:
 * ${DESCRIPTION}
 *
 * @author Yangjy
 * @date 2018-04-12
 */
@Controller
public class IndexController {



    @RequestMapping("/")
    public String goToindex(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }


}
