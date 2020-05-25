package com.github.managesystem.controller;

import com.github.managesystem.model.resp.Result;
import org.nutz.img.Colors;
import org.nutz.img.Images;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Streams;
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

/**
 * @Author:zhangbo
 * @Date:2020/5/25 11:03
 */
@Controller
public class FileController {


    @PostMapping("/file/upload")
    @ResponseBody
    public Result uploadFile(@RequestParam("upfile") MultipartFile upfile) throws Exception {

        String filename = upfile.getOriginalFilename();

        // 检查文件格式
        String prefix = filename.substring(filename.lastIndexOf(".") + 1);
        System.out.println("-----------------------filename:"+filename);
        System.out.println("-----------------------prefix:"+prefix);

        String hashcode = Lang.md5(upfile.getInputStream());


        String fn = hashcode.substring(0, 2) + File.separator + hashcode + "." + prefix;

        // 全路径
        String fullPath = "/home" + File.separator + fn;

        // 保存文件
        Files.write(fullPath, upfile.getInputStream());
        return Result.ok(fullPath);
    }

    @RequestMapping(value = "/**", method = {RequestMethod.GET})
    public void fetchFile(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String path = request.getRequestURI();
        path = URLDecoder.decode(path);
        String fileName = "/home" + path;
        response.setHeader("Cache-control", "max-age=" + 15 * 24 * 60 * 60);

        String width = request.getParameter("width");
        String height = request.getParameter("height");
        int req_width = 0;
        int req_height = 0;
        if (width != null && width.length() > 0) {
            req_width = Integer.valueOf(width);
        }
        if (height != null && height.length() > 0) {
            req_height = Integer.valueOf(height);
        }
        File f = new File(fileName);
        if (f.exists()) {
            if (req_width > 0 && req_height > 0) {
                String targetName = "";
                int index = fileName.lastIndexOf(".");
                if (index > 0) {
                    targetName = fileName.substring(0, index);
                    targetName += "_" + req_width + "_" + req_height;
                    targetName += fileName.substring(index);
                    InputStream infile = Files.findFileAsStream(targetName);
                    if (infile != null) {
                        Streams.writeAndClose(response.getOutputStream(), infile);
                    } else {
                        Images.zoomScale(fileName, targetName, req_width, req_height, Colors.as("#FFFFFF"));
                        Streams.writeAndClose(response.getOutputStream(), Files.findFileAsStream(targetName));
                    }

                } else {
                    Streams.writeAndClose(response.getOutputStream(), Files.findFileAsStream(fileName));
                }

            } else {
                Streams.writeAndClose(response.getOutputStream(), Files.findFileAsStream(fileName));
            }
        }
    }

}
