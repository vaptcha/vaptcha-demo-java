package com.vaptcha.inteface;

import com.vaptcha.constant.Constant;
import com.vaptcha.domain.HttpResp;
import com.vaptcha.domain.Login;
import com.vaptcha.core.Vaptcha;
import com.vaptcha.utils.Common;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Verify {
    private final Vaptcha vaptcha = Vaptcha.getInstance(Constant.SecretKey, Constant.Vid, Constant.Scene);

    /**
     * mock login
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object VaptchaVerify(@RequestBody Login verify, HttpServletRequest request) {

        String token = verify.getToken();
        String server = verify.getServer();
        String username = verify.getUsername();
        String password = verify.getPassword();
        String clientIP = Common.ClientIP(request);
        com.vaptcha.domain.Verify result = vaptcha.Verify(clientIP, token, server);
        if (result.getSuccess() == Constant.VerifySuccess) {
            // token is correct,do other logic
            // such as login、register
            if (username.equals("root") && password.equals("root")) {
                return new HttpResp("login success", 200);
            }
            return new HttpResp("username or password fail", 400);
        } else {
            // token is not correct
            // tell fronted reset VAPTCHA, let user tray again
            return new HttpResp("verify fail", 400);
        }
    }
}
