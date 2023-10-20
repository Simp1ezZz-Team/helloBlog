package com.simple.helloblog;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloBlogApplicationTests {

    @Value("${sa-token.salt}")
    private String salt;

    @Test
    void testSha256() {
        String pw1 = SaSecureUtil.sha256("zx199857");
        String pw2 = SaSecureUtil.sha256("zx199857");
        Assert.equals(pw1, pw2, "两次sha256加密结果不一致");
        String pws1 = SaSecureUtil.sha256BySalt("zx199857", salt);
        String pws2 = SaSecureUtil.sha256BySalt("zx199857", salt);
        Assert.equals(pws1, pws2, "两次sha256BySalt加密结果不一致");
        System.out.println(SaSecureUtil.sha256BySalt("zx199857", salt));
    }

}
