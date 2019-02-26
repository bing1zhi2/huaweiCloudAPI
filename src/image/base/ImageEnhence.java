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

            // 3.传入图片标签服务对应的uri参数, 传入图片标签服务需要的参数，
            // 该参数主要通过JSON对象的方式传入, 使用POST方法调用服务
            HttpResponse response = service.post(uri, stringEntity);

            // 4.验证服务调用返回的状态是否成功，如果为200, 为成功, 否则失败。
            int statusCode = ResponseProcessUtils.processResponseStatus(response);
            // 5.处理服务返回的字符流，输出识别结果。
            String result = ResponseProcessUtils.processResponse(response);
            resultMap.put("statusCode", statusCode);
            resultMap.put("jsonStr",result);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            // 6.使用完毕，关闭服务的客户端连接
            if (service != null)
                service.close();
        }

        return resultMap;
    }

    /**
     * 子类实现自己的URI配置
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
     *子类来覆盖它 子类的实现自己决定是否使用代理，
     * @return
     */
    public  boolean useProxy(){
        return false;
    }
}
