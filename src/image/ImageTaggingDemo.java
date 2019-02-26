package image;

/**
 * Created by bing1 on 2019/2/25.
 */
import com.alibaba.fastjson.JSONObject;
import com.huawei.ais.sdk.AisAccess;
import image.base.ClientContextUtils;
import image.base.ResponseProcessUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

import java.io.File;
import java.io.IOException;

/**
 *  ͼƬ��ǩ�����ʹ��ʾ����
 */
public class ImageTaggingDemo {
    //
    // ͼƬ��ǩ�����ʹ��ʾ������
    //
    private static void imageTaggingDemo() throws IOException {
        //
        // 1. ��ClientContextUtils����, ���ú÷���ͼ���ǩ����Ļ�����Ϣ,
        // Ȼ���ڴ˴����ɶ�Ӧ��һ���ͻ������Ӷ���
        //
        AisAccess service = new AisAccess(ClientContextUtils.getAuthInfo());

        //
        // 1.a �˴�֧��ʹ�ô���ʽ����ͼ���ǩ�������ڲ���ֱ�ӷ��ʻ�Ϊ�ƹ�����������, ���磬�������硣
        // ���ʹ�ô˴���ʽ����Ҫͬʱ��ClientContextUtils�У�������Ӧ�Ĵ���������Ĳ�����(ProxyHostInfo)
        //
        //AisAccess service = new AisAccessWithProxy(ClientContextUtils.getAuthInfo(), ClientContextUtils.getProxyHost());

        try {
            //
            // 2.��������ͼƬ��ǩ������Ҫ�Ĳ���
            //
            String uri = "/v1.0/image/tagging";
//            byte[] fileData = FileUtils.readFileToByteArray(new File("data/image-tagging-demo-1.jpg"));
            String imagePath = "F:\\work\\project\\imageSuperResolution\\test.jpg";
            byte[] fileData = FileUtils.readFileToByteArray(new File(imagePath));
            String fileBase64Str = Base64.encodeBase64String(fileData);

            JSONObject json = new JSONObject();
            json.put("image", fileBase64Str);
            json.put("threshold", 60);
            StringEntity stringEntity = new StringEntity(json.toJSONString(), "utf-8");

            // 3.����ͼƬ��ǩ�����Ӧ��uri����, ����ͼƬ��ǩ������Ҫ�Ĳ�����
            // �ò�����Ҫͨ��JSON����ķ�ʽ����, ʹ��POST�������÷���
            HttpResponse response = service.post(uri, stringEntity);

            // 4.��֤������÷��ص�״̬�Ƿ�ɹ������Ϊ200, Ϊ�ɹ�, ����ʧ�ܡ�
            ResponseProcessUtils.processResponseStatus(response);

            // 5.������񷵻ص��ַ��������ʶ������
            ResponseProcessUtils.processResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // 6.ʹ����ϣ��رշ���Ŀͻ�������
            service.close();
        }
    }

    //
    // ����ں���
    //
    public static void main(String[] args) throws IOException {
        // ������ں���
        imageTaggingDemo();
    }
}