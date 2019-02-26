package image.base;

import com.cloud.sdk.util.StringUtils;
import com.huawei.ais.sdk.AisAccess;
import com.huawei.ais.sdk.AisAccessWithProxy;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bing1 on 2019/2/26.
 */
public abstract class ImageEnhence {

    public  Map<String,Object> imageEnhence(String host,int port,String userName,String passwd,String namePath) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        AisAccess service = null;

        if (useProxy()) {
            service = getAisAccessProxy(host, port, userName, passwd);
        } else {
            service = new AisAccess(ClientContextUtils.getAuthInfo());
        }


        String uri = getURI();
        try {
            byte[] fileData = FileUtils.readFileToByteArray(new File(namePath));
            String fileBase64Str = Base64.encodeBase64String(fileData);
            String JsonStr = getJSONString(fileBase64Str);

            StringEntity stringEntity = new StringEntity(JsonStr, "utf-8");

            // 3.����ͼƬ��ǩ�����Ӧ��uri����, ����ͼƬ��ǩ������Ҫ�Ĳ�����
            // �ò�����Ҫͨ��JSON����ķ�ʽ����, ʹ��POST�������÷���
            HttpResponse response = service.post(uri, stringEntity);

            // 4.��֤������÷��ص�״̬�Ƿ�ɹ������Ϊ200, Ϊ�ɹ�, ����ʧ�ܡ�
            int statusCode = ResponseProcessUtils.processResponseStatus(response);
            // 5.������񷵻ص��ַ��������ʶ������
            String result = ResponseProcessUtils.processResponse(response);
            resultMap.put("statusCode", statusCode);
            resultMap.put("jsonStr",result);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            // 6.ʹ����ϣ��رշ���Ŀͻ�������
            if (service != null)
                service.close();
        }

        return resultMap;
    }

    /**
     * ����ʵ���Լ���URI����
     * @return
     */
    abstract  String getURI();

    abstract String getJSONString(String imageBase64);

    final AisAccess getAisAccessProxy(String host,int port,String userName,String passwd) {
        AisAccess service =null;
        if (StringUtils.isNullOrEmpty(host) || StringUtils.isNullOrEmpty(passwd) || port ==0) {
            service = new AisAccessWithProxy(ClientContextUtils.getAuthInfo(), ClientContextUtils.getProxyHost());
        }else{
            service = new AisAccessWithProxy(ClientContextUtils.getAuthInfo(), ClientContextUtils.getProxyHost(host,port,userName,passwd));
        }
        return service;
    }

    /**
     *������������ �����ʵ���Լ������Ƿ�ʹ�ô���
     * @return
     */
    public  boolean useProxy(){
        return false;
    }
}
