package image;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huawei.ais.sdk.AisAccess;
import image.base.ClientContextUtils;
import image.base.ResponseProcessUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

import java.io.File;


/**
 * Created by bing1 on 2019/2/25.
 *
 * ����ͼ���ؽ�

 ֻ֧��ʶ��PNG��JPEG��BMP��ʽ��ͼƬ��
 ͼƬ�ߴ粻����800��600px��
 �Ŵ�߶�ֻ֧��3����4���Ŵ�
 ֻ֧��ͼ�����Ϊ24��32bit��
 Ŀǰ����֤API���õĲ���������
 */
public class TestSuperResolution {
    public static void main(String[] args)  {

        AisAccess service = new AisAccess(ClientContextUtils.getAuthInfo());

        try {
            String uri = "/v1.0/vision/super-resolution";

            String imagePath = "F:\\work\\project\\imageSuperResolution\\superResolution";

            String out_fr = "_out.jpg";

//            String name="sr";
//            String name="sr1";
            String name="sr";
            String infile1 = imagePath + "\\"+ name +".jpg";
            String outfile1 = imagePath + "\\"+ name + out_fr;


            byte[] fileData = FileUtils.readFileToByteArray(new File(infile1));

            String fileBase64Str = Base64.encodeBase64String(fileData);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("image", fileBase64Str);
            jsonObject.put("scale", 3);
            jsonObject.put("model", "SRCNN");

            //        HttpResponse response=;

            StringEntity stringEntity = new StringEntity(jsonObject.toJSONString(), "utf-8");

            // 3.����ͼƬ��ǩ�����Ӧ��uri����, ����ͼƬ��ǩ������Ҫ�Ĳ�����
            // �ò�����Ҫͨ��JSON����ķ�ʽ����, ʹ��POST�������÷���
            HttpResponse response = service.post(uri, stringEntity);

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


        }catch (Exception e){
            e.printStackTrace();
        }finally{
            service.close();
        }




    }

}
