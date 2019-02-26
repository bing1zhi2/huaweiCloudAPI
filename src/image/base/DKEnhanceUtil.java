package image.base;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by bing1 on 2019/2/26.
 * <p>
 * ������ǿapi
 * ʹ�÷���
 *
 * <p>
 * doImageEnhence("F:\\work\\project\\imageSuperResolution\\darkenhance\\dark.jpg");
 * <p>
 * ʹ�ô���
 * doImageEnhence(String host,int port,String userName,String passwd,String namePath)
 */
public class DKEnhanceUtil extends ImageEnhence {

    //����ֵ��Ĭ��ֵ0.9��ȡֵ��Χ��[0,1]��
    private double brightness=0.9;

    public double getBrightness() {
        return brightness;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }


    /**
     * �{�ù�������,��ʹ�ô���
     * @param namePath �DƬȫ·������ F:\\work\\project\\imageSuperResolution\\darkenhance\\dark.jpg
     * @return
     * Map  statusCode, int ��
            jsonStr ,  string ��
     */
    public  Map<String, Object> doImageEnhence(String namePath) {

        return imageEnhence(null, 0, null, null, namePath);
    }

    /**
     * ���ù�����ǿ,ʹ�ô���
     * @param host ��������
     * @param port �˿�
     * @param userName �û���
     * @param passwd  ����
     * @param namePath ͼƬȫ·������ F:\\work\\project\\imageSuperResolution\\darkenhance\\dark.jpg
     * @return Map
     * statusCode, int ��
       jsonStr ,  string ��
     */
    public  Map<String, Object> doImageEnhence(String host, int port, String userName, String passwd, String namePath) {

        return imageEnhence(host, port, userName, passwd, namePath);
    }


    @Override
    String getURI() {
        return ClientContextUtils.URI_DARK_ENHANCE;
    }

    @Override
    String getJSONString(String imageBase64) {
        JSONObject json = new JSONObject();
        json.put("image", imageBase64);
        json.put("brightness", getBrightness());
        return json.toJSONString();
    }




    public static void main(String[] args) {
        DKEnhanceUtil enhance = new DKEnhanceUtil();
        enhance.setBrightness(0.8);
        enhance.doImageEnhence("F:\\work\\project\\imageSuperResolution\\darkenhance\\dark.jpg");
    }
}
