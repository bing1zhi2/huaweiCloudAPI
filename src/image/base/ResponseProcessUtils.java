package image.base;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.http.HttpResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.sdk.util.Base64;
import com.huawei.ais.sdk.util.HttpClientUtils;

/**
 * Created by bing1 on 2019/2/25.
 */
public class ResponseProcessUtils {

    /**
     * 打印出服务访问完成的HTTP状态码
     *
     * @param response 响应对象
     */
    public static int processResponseStatus(HttpResponse response) {
        int statusCode=response.getStatusLine().getStatusCode();
        System.out.println("statusCode:" + statusCode);
        return statusCode;
    }

    /**
     * 打印出服务访问完成后，转化为文本的字符流，主要用于JSON数据的展示
     *
     * @param response 响应对象
     * @throws UnsupportedOperationException
     * @throws IOException
     */
    public static String processResponse(HttpResponse response) throws UnsupportedOperationException, IOException {
        String resultStr = HttpClientUtils.convertStreamToString(response.getEntity().getContent());
        System.out.println(resultStr);
        return resultStr;
    }

    /**
     * 处理返回Base64编码的图像文件的生成
     *
     * @param response
     * @throws UnsupportedOperationException
     * @throws IOException
     */
    public static void processResponseWithImage(HttpResponse response, String fileName) throws UnsupportedOperationException, IOException {
        String result = HttpClientUtils.convertStreamToString(response.getEntity().getContent());
        JSONObject resp = JSON.parseObject(result);
        String imageString = (String)resp.get("result");
        byte[] fileBytes = Base64.decode(imageString);
        writeBytesToFile(fileName, fileBytes);
    }

    /**
     *  将字节数组写入到文件, 用于支持二进制文件(如图片)的生成
     * @param fileName 文件名
     * @param data 数据
     * @throws IOException
     */
    public static void writeBytesToFile(String fileName, byte[] data) throws IOException{

        FileChannel fc = null;
        try {
            ByteBuffer bb = ByteBuffer.wrap(data);
            fc = new FileOutputStream(fileName).getChannel();
            fc.write(bb);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        finally {
            fc.close();
        }
    }
}
