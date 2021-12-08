package com.vaptcha.core;

import com.google.gson.Gson;
import com.vaptcha.constant.Constant;
import com.vaptcha.domain.Verify;
import com.vaptcha.utils.Common;
import com.vaptcha.utils.HttpClientUtil;

import java.util.HashMap;

/**
 * Vaptcha demo
 */
public class Vaptcha {
    private String SecretKey;
    private String Vid;
    private Integer Scene;

    private Vaptcha() {
    }

    private Vaptcha(String secretKey, String vid, Integer scene) {
        SecretKey = secretKey;
        Vid = vid;
        Scene = scene;
    }

    private static volatile Vaptcha vaptcha;

    public static Vaptcha getInstance(String secretKey, String vid, Integer scene) {
        if (vaptcha == null) {
            synchronized (Vaptcha.class) {
                if (vaptcha == null) {
                    vaptcha = new Vaptcha(secretKey, vid, scene);
                }
            }
        }
        return vaptcha;
    }


    /**
     * SendVerify
     *
     * @param token token
     */
    public Verify Verify(String clientIP, String token, String server) {
        if (token.length() == 0) {
            return new Verify(Constant.VerifyFail, "invalid token", 0);
        }
        // important: check is real vaptcha server
        if (!Common.IsVAPTCHAServer(server)) {
            return new Verify(Constant.VerifyFail, "invalid server", 0);
        }
        return doVerify(clientIP, token, server);
    }

    /**
     * sendVerify Check if the token is correct
     *
     * @param token token user submit
     * @param ip    clientIP
     */
    private Verify doVerify(String ip, String token, String server) {
        HashMap<String, Object> m = new HashMap<>();
        m.put("id", this.Vid);
        m.put("secretkey", this.SecretKey);
        m.put("scene", this.Scene);
        m.put("ip", ip);
        m.put("token", token);
        try {
            Gson gson = new Gson();
            String req = gson.toJson(m);
            String result = HttpClientUtil.postJSON(server, req);
            System.out.println("request " + req);
            Verify resp = gson.fromJson(result, Verify.class);
            System.out.println("response " + resp);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Verify();
    }
}