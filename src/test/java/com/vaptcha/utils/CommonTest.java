package com.vaptcha.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonTest {

    @Test
    void isVAPTCHAServer() {
//        String server = "https://a.vaptcha.net/verify";
        String server = "https://a.vaptcha.xxx/verify";
        Boolean ok = Common.IsVAPTCHAServer(server);
        System.out.println(ok);
    }
}