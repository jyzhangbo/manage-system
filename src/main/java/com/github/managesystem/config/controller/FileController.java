package com.github.managesystem.config.controller;

import com.github.managesystem.entity.Img;
import com.github.managesystem.model.resp.Result;
import com.github.managesystem.service.IImgService;
import org.nutz.img.Colors;
import org.nutz.img.Images;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Maths;
import org.nutz.lang.Streams;
import org.nutz.lang.random.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @Author:zhangbo
 * @Date:2020/5/25 11:03
 */
@Controller
public class FileController {

    @Autowired
    private IImgService imgService;

    @PostMapping("/file/upload")
    @ResponseBody
    public Result uploadFile(@RequestParam("upfile") MultipartFile upfile) throws Exception {

        String filename = upfile.getOriginalFilename();
        // 检查文件格式
        String prefix = filename.substring(filename.lastIndexOf(".") + 1);
        System.out.println("-----------------------filename:"+filename);
        System.out.println("-----------------------prefix:"+prefix);
        String hashcode = R.UU16();
        String fn = hashcode + "." + prefix;

        // 全路径
        String fullPath = "/home/img" + File.separator + fn;
        // 保存文件
        Files.write(fullPath, upfile.getInputStream());

        String fileUrl = "http://localhost:8080" + fullPath;

        imgService.save(Img.builder().imgName(hashcode).imgUrl(fileUrl).build());

        return Result.ok(fileUrl);
    }

    @RequestMapping(value = "/home/img/{filename}", method = {RequestMethod.GET})
    public void fetchFile(@PathVariable String filename,HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String fileName = "/home/img/" + filename;
        response.setHeader("Cache-control", "max-age=" + 15 * 24 * 60 * 60);

        File f = new File(fileName);
        if (f.exists()) {
            Streams.writeAndClose(response.getOutputStream(), Files.findFileAsStream(fileName));
        }
    }

}
