package com.yungyu.oauthserver.controller;

import com.yungyu.oauthserver.untils.ImageCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/code")
public class ValidateController {

    public final static String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

    @GetMapping("/image")
    public void createCode (HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode =  ImageCode.createImageCode();
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_KEY_IMAGE_CODE, imageCode);
        ImageIO.write(imageCode.getImage(), "jpeg", response.getOutputStream());
    }

}