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
 * 只支持识别PNG、JPEG、BMP格式的图片。
 图像尺寸不超过1280×720px。
 只支持图像深度为24或32bit。
 不支持处理压缩比超过25的高度有损压缩图片。
 目前不保证API调用的并发能力。
 */
public class DeFogUtil {

    public static void defog(){
        //
        // 1. 在ClientContextUtils类中, 配置好访问图像标签服务的基本信息,
        // 然后，在此处生成对应的一个客户端连接对象
        //
        AisAccess service = new AisAccess(ClientContextUtils.getAuthInfo());

        //
        // 1.a 此处支持使用代理方式访问图像标签服务，用于不能直接访问华为云官网服务的情况, 例如，内网网络。
        // 如果使用此处方式，需要同时在ClientContextUtils中，配置相应的代理服务器的参数类(ProxyHostInfo)
        //
        //AisAccess service = new AisAccessWithProxy(ClientContextUtils.getAuthInfo(), ClientContextUtils.getProxyHost());

        try {
            //
            // 2.构建访问图片标签服务需要的参数
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

            // 3.传入图片标签服务对应的uri参数, 传入图片标签服务需要的参数，
            // 该参数主要通过JSON对象的方式传入, 使用POST方法调用服务
            HttpResponse response = service.post(uri, stringEntity);

            // 4.验证服务调用返回的状态是否成功，如果为200, 为成功, 否则失败。
            ResponseProcessUtils.processResponseStatus(response);


            if (response != null) {
                // 4.验证服务调用返回的状态是否成功，如果为200, 为成功, 否则失败。
                ResponseProcessUtils.processResponseStatus(response);

                // 5.处理服务返回的字符流，输出识别结果。
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

            // 6.使用完毕，关闭服务的客户端连接
            service.close();
        }
    }


    public static void main(String[] args) {
        defog();
    }
}
