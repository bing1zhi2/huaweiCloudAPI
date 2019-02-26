package image.base;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by bing1 on 2019/2/26.
 */
public class SuperResolutionUtil extends ImageEnhence {


    //�Ŵ�����Ĭ��Ϊ3��ȡֵ��Χ��3��4��
    private int scale=3;
    /*
     ͼ�񳬷ֱ����ؽ����õ��㷨ģʽ��֧��ESPCN��SRCNN��Ĭ�� ESPCN��ȡֵΪ��

            ��ESPCN����Efficient Sub-Pixel Convolutional Neural Network��

            ��SRCNN���� Super-Resolution Convolutional Neural Network��
     */
    private String model = "ESPCN";


    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    /**
     * �{�� ͼ�񳬷�,��ʹ�ô���
     * @param namePath �DƬȫ·������ F:\\work\\project\\imageSuperResolution\\darkenhance\\dark.jpg
     * @return
     * Map  statusCode, int ��
    jsonStr ,  string ��
     */
    public Map<String, Object> doImageEnhence(String namePath) {

        return imageEnhence(null, 0, null, null, namePath);
    }

    /**
     * ���� ͼ�񳬷�,ʹ�ô���
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
        json.put("scale", getScale());
        json.put("model", getModel());
        return json.toJSONString();
    }



    public static void main(String[] args) {
        SuperResolutionUtil enhance = new SuperResolutionUtil();
        enhance.setScale(3);
        enhance.setModel("ESPCN");
        enhance.doImageEnhence("F:\\work\\project\\imageSuperResolution\\superResolution\\sr.jpg");
    }
}
