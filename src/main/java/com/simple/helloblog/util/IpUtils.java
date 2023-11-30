package com.simple.helloblog.util;

import cn.hutool.extra.servlet.JakartaServletUtil;
import com.simple.helloblog.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import lombok.experimental.UtilityClass;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

/**
 * IP地址工具类
 *
 * @author 魑魅魍魉
 * @date 2023/11/30 20:54:10
 */
@UtilityClass
public class IpUtils {

    private static final Searcher searcher;

    static {
        // 解决项目打包找不到ip2region.xdb
        try {
            InputStream inputStream = new ClassPathResource("/ipdb/ip2region.xdb").getInputStream();
            //将 ip2region.db 转为 ByteArray
            byte[] cBuff = FileCopyUtils.copyToByteArray(inputStream);
            searcher = Searcher.newWithBuffer(cBuff);
        } catch (IOException e) {
            throw new ServiceException("ip2region.xdb加载失败");
        }
    }

    /**
     * 获取 IP 地址
     *
     * @param request request请求
     * @return {@link String} ip地址
     */
    public static String getIpAddress(HttpServletRequest request){
        return JakartaServletUtil.getClientIP(request);
    }

    /**
     * 获取 IP 对应地理位置
     *
     * @param ipAddress IP地址
     * @return {@link String} ip地址对应的地理位置
     */
    public static String getIpSource(String ipAddress){
        try {
            String address = searcher.search(ipAddress);
            if (StringUtils.hasText(address)) {
                address = address.replace("|0", "");
                address = address.replace("0|", "");
                return address;
            }
            return address;
        } catch (Exception e) {
            return "";
        }
    }

}
