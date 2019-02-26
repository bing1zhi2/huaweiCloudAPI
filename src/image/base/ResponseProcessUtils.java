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
     * ��ӡ�����������ɵ�HTTP״̬��
     *
     * @param response ��Ӧ����
     */
    public static int processResponseStatus(HttpResponse response) {
        int statusCode=response.getStatusLine().getStatusCode();
        System.out.println("statusCode:" + statusCode);
        return statusCode;
    }

    /**
     * ��ӡ�����������ɺ�ת��Ϊ�ı����ַ�������Ҫ����JSON���ݵ�չʾ
     *
     * @param response ��Ӧ����
     * @throws UnsupportedOperationException
     * @throws IOException
     */
    public static String processResponse(HttpResponse response) throws UnsupportedOperationException, IOException {
        String resultStr = HttpClientUtils.convertStreamToString(response.getEntity().getContent());
        System.out.println(resultStr);
        return resultStr;
    }

    /**
     * ������Base64�����ͼ���ļ�������
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
     *  ���ֽ�����д�뵽�ļ�, ����֧�ֶ������ļ�(��ͼƬ)������
     * @param fileName �ļ���
     * @param data ����
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
