package image.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huawei.ais.sdk.AisAccess;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

import java.io.File;

/**
 * Created by bing1 on 2019/2/25.
 * 
 * ֻ֧��ʶ��PNG��JPEG��BMP��ʽ��ͼƬ��
 ͼ��ߴ粻����1280��720px��
 ֻ֧��ͼ�����Ϊ24��32bit��
 ��֧�ִ���ѹ���ȳ���25�ĸ߶�����ѹ��ͼƬ��
 Ŀǰ����֤API���õĲ���������
 */
public class DeFogUtil {

    public static void defog(){
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
            String uri = "/v1.0/vision/defog";
//            byte[] fileData = FileUtils.readFileToByteArray(new File("data/image-tagging-demo-1.jpg"));
            String imagePath = "F:\\work\\project\\imageSuperResolution\\defog";
            String out_fr = "_out.jpg";

//            String name="defog";


            String name="defog2";
            String infile1 = imagePath + "\\"+ name +".jpg";
            String outfile1 = imagePath + "\\"+ name + out_fr;


            byte[] fileData = FileUtils.readFileToByteArray(new File(infile1));
            String fileBase64Str = Base64.encodeBase64String(fileData);

            JSONObject json = new JSONObject();
            json.put("image", fileBase64Str);

            StringEntity stringEntity = new StringEntity(json.toJSONString(), "utf-8");

            // 3.����ͼƬ��ǩ�����Ӧ��uri����, ����ͼƬ��ǩ������Ҫ�Ĳ�����
            // �ò�����Ҫͨ��JSON����ķ�ʽ����, ʹ��POST�������÷���
            HttpResponse response = service.post(uri, stringEntity);

            // 4.��֤������÷��ص�״̬�Ƿ�ɹ������Ϊ200, Ϊ�ɹ�, ����ʧ�ܡ�
            ResponseProcessUtils.processResponseStatus(response);


            if (response != null) {
                // 4.��֤������÷��ص�״̬�Ƿ�ɹ������Ϊ200, Ϊ�ɹ�, ����ʧ�ܡ�
                ResponseProcessUtils.processResponseStatus(response);

                // 5.������񷵻ص��ַ��������ʶ������
                String result = ResponseProcessUtils.processResponse(response);
//                ResponseProcessUtils.processResponseWithImage(response,"test.png");


                JSONObject resp = JSON.parseObject(result);
                String imageString = (String)resp.get("result");
                byte[] fileBytes = com.cloud.sdk.util.Base64.decode(imageString);
                ResponseProcessUtils.writeBytesToFile(outfile1, fileBytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // 6.ʹ����ϣ��رշ���Ŀͻ�������
            service.close();
        }
    }


    public static void main(String[] args) {
        defog();
    }
}
