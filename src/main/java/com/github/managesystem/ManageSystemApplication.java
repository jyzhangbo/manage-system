package com.github.managesystem;

import com.github.managesystem.collection.CollectServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@Slf4j
public class ManageSystemApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication app = new SpringApplication(ManageSystemApplication.class);
        app.run(args);
        try {
            new CollectServer(7890).start();
        }catch (Exception e){
            log.error("数据采集服务异常");
        }

    }

}
