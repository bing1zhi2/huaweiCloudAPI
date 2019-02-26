package httputil;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by bing1 on 2019/2/25.
 */
public class HttpUtils {

    public static String send(String url, Map<String,String> map,String encoding) throws ParseException, IOException {
        String body = "";

        //����httpclient����
        CloseableHttpClient client = HttpClients.createDefault();
        //����post��ʽ�������
        HttpPost httpPost = new HttpPost(url);

        //װ�����
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(map!=null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //���ò��������������
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

        System.out.println("�����ַ��"+url);
        System.out.println("���������"+nvps.toString());

        //����header��Ϣ
        //ָ������ͷ��Content-type������User-Agent��
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //ִ��������������õ������ͬ��������
        CloseableHttpResponse response = client.execute(httpPost);
        //��ȡ���ʵ��
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //��ָ������ת�����ʵ��ΪString����
            body = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        //�ͷ�����
        response.close();
        return body;
    }

}
