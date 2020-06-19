package com.github.managesystem.config.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.managesystem.entity.Img;
import com.github.managesystem.entity.Task;
import com.github.managesystem.entity.User;
import com.github.managesystem.model.constant.RoleEnum;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.exception.ResultCode;
import com.github.managesystem.model.resp.Result;
import com.github.managesystem.service.IImgService;
import com.github.managesystem.service.IUserService;
import org.nutz.img.Colors;
import org.nutz.img.Images;
import org.nutz.lang.*;
import org.nutz.lang.random.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
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

    @Value("${picture.url}")
    private String picUrl;

    @Autowired
    private IUserService userService;

    @PostMapping("/file/upload")
    @ResponseBody
    public Result uploadFile(@RequestParam("token") String token,@RequestParam("upfile") MultipartFile upfile) throws Exception {

        String filename = upfile.getOriginalFilename();
        // 检查文件格式
        String prefix = filename.substring(filename.lastIndexOf(".") + 1);
        System.out.println("-----------------------filename:"+filename);
        System.out.println("-----------------------prefix:"+prefix);
        System.out.println("-----------------------userName:"+token);
        String hashcode = R.UU16();
        String fn = hashcode + "." + prefix;

        // 全路径
        String fullPath = "/home/img" + File.separator + fn;
        BufferedOutputStream out= null;
        try {
            //修改图片宽和高
            BufferedImage read = ImageIO.read(upfile.getInputStream());
            read = Images.scale(read, 686, 217);

            out = new BufferedOutputStream(new FileOutputStream(fullPath));
            ImageIO.write(read, prefix, out);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            out.close();
        }

        String fileUrl = picUrl + fullPath;
        User user = userService.getOne(new QueryWrapper<User>().eq(User.LOGIN_NAME, token),false);
        if(user == null){
            throw new CodeException(ResultCode.ERROR_SYSTEM);
        }
        if(Strings.equals(user.getUserRole(),RoleEnum.ADMIN.value)){
            user.setCompanyName(user.getUserRole());
        }
        imgService.save(Img.builder().imgName(hashcode).imgUrl(fileUrl).companyName(user.getCompanyName()).build());

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
