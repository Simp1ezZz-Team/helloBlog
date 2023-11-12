package com.simple.helloblog;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import com.simple.helloblog.entity.Role;
import com.simple.helloblog.model.vo.RoleVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
class HelloBlogApplicationTests {

    @Value("${sa-token.salt}")
    private String salt;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Test
    void testSha256() {
        String pw1 = SaSecureUtil.sha256("zx199857");
        String pw2 = SaSecureUtil.sha256("zx199857");
        Assert.equals(pw1, pw2, "两次sha256加密结果不一致");
        String pws1 = SaSecureUtil.sha256BySalt("zx199857", salt);
        String pws2 = SaSecureUtil.sha256BySalt("zx199857", salt);
        Assert.equals(pws1, pws2, "两次sha256BySalt加密结果不一致");
        System.out.println(SaSecureUtil.sha256BySalt("123456", salt));
    }
    @Test
    void testConvertBean() {
        Role role = Role.builder().roleId(1).roleName("aaa").roleDesc("gbasrasfq案说法").build();
        RoleVO roleVO = Convert.convert(RoleVO.class, role);
        Assert.notNull(roleVO, "转换失败");
        System.out.println(roleVO);
    }

    @Test
    void testKafka() {
        kafkaTemplate.send("quickstart-events", "hello kafka");
    }
}
