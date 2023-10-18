package com.simple.helloblog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

@SpringBootTest
class HelloBlogApplicationTests {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class TestClass {
        private String name;
        private Integer age;
    }

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("test", new TestClass("test", 18));
        Object test = redisTemplate.opsForValue().get("test");
        Assert.notNull(test, "test is null");
        Assert.isInstanceOf(TestClass.class, test, "test is not TestClass");
    }

}
