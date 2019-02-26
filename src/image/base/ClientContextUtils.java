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
     * ����֧��ʹ�ô���ģʽ�������磬 ��ʱʹ�õĴ�������������Ϣ
     */
    public static ProxyHostInfo getProxyHost() {
        return new ProxyHostInfo("proxy.example.com", /* ����������Ϣ */
                8080,        /* ���������Ķ˿� */
                "*******",   /* ������û��� */
                "*******"   /* �����û���Ӧ������ */
        );
    }

    /**
     * ����֧��ʹ�ô���ģʽ�������磬 ��ʱʹ�õĴ�������������Ϣ ʹ��ָ���Ĵ��������Ϣ
     * @param host ����
     * @param port �˿�
     * @param userName �û���
     * @param passwd  ����
     * @return  ProxyHostInfo ����
     */
    public static ProxyHostInfo getProxyHost(String host,int port,String userName,String passwd) {
        return new ProxyHostInfo(host, /* ����������Ϣ */
                port,        /* ���������Ķ˿� */
                userName,   /* ������û��� */
                passwd   /* �����û���Ӧ������ */
        );
    }
    /*
    ͼ����ǿĬ��url
     */
    public static final String URI_DARK_ENHANCE = "/v1.0/vision/dark-enhance";
    public static final String URI_DARK_DEFOG = "/v1.0/vision/defog";
    public static final String URI_DARK_SUPER_RESOLUTION= "/v1.0/vision/super-resolution";

}
