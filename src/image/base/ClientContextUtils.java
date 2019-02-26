package image.base;

import com.huawei.ais.common.AuthInfo;
import com.huawei.ais.common.ProxyHostInfo;

/**
 * Created by bing1 on 2019/2/25.
 */
public class ClientContextUtils {
    private static final AuthInfo HEC_AUTH = new AuthInfo(
            "https://image.cn-north-1.myhuaweicloud.com",
            "cn-north-1",
            "WNUYXD1EFMNAW0RZL25V",
            "EedmFTLdyRJmdXrSnCPnFdhnfurE3wgO2ZHDLub9");

    public static AuthInfo getAuthInfo() {
        return HEC_AUTH;
    }

    /**
     * 用于支持使用代理模式访问网络， 此时使用的代理主机配置信息
     */
    public static ProxyHostInfo getProxyHost() {
        return new ProxyHostInfo("proxy.example.com", /* 代理主机信息 */
                8080,        /* 代理主机的端口 */
                "*******",   /* 代理的用户名 */
                "*******"   /* 代理用户对应的密码 */
        );
    }

    /**
     * 用于支持使用代理模式访问网络， 此时使用的代理主机配置信息 使用指定的代理服务信息
     * @param host 域名
     * @param port 端口
     * @param userName 用户名
     * @param passwd  密码
     * @return  ProxyHostInfo 对象
     */
    public static ProxyHostInfo getProxyHost(String host,int port,String userName,String passwd) {
        return new ProxyHostInfo(host, /* 代理主机信息 */
                port,        /* 代理主机的端口 */
                userName,   /* 代理的用户名 */
                passwd   /* 代理用户对应的密码 */
        );
    }
    /*
    图像增强默认url
     */
    public static final String URI_DARK_ENHANCE = "/v1.0/vision/dark-enhance";
    public static final String URI_DARK_DEFOG = "/v1.0/vision/defog";
    public static final String URI_DARK_SUPER_RESOLUTION= "/v1.0/vision/super-resolution";

}
