package image.base;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by bing1 on 2019/2/26.
 */
public class DeFogEnhanceUtil extends ImageEnhence{


    //gamma����ֵ��Ĭ��Ϊ1.5��ȡֵ��Χ��[0.1,10]��
    private double gamma=1.5;
    //�Ƿ񱣳���Ȼ�۸У�Ĭ����true�����ѡ��false����ͼ��ֻ׷��ȥ��Ч����ͼ���Ӿ�Ч�����ܲ���Ȼ��
    private boolean natural_look = true;


    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public boolean isNatural_look() {
        return natural_look;
    }

    public void setNatural_look(boolean natural_look) {
        this.natural_look = natural_look;
    }

    /**
     * �{�ù�������,��ʹ�ô���
     * @param namePath �DƬȫ·������ F:\\work\\project\\imageSuperResolution\\darkenhance\\dark.jpg
     * @return
     * Map  statusCode, int ��
    jsonStr ,  string ��
     */
    public Map<String, Object> doImageEnhence(String namePath) {

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
        json.put("gamma", getGamma());
        json.put("natural_look", isNatural_look());
        return json.toJSONString();
    }




    public static void main(String[] args) {
        DeFogEnhanceUtil enhance = new DeFogEnhanceUtil();
        enhance.setNatural_look(false);
        enhance.setGamma(1.6);
        enhance.doImageEnhence("F:\\work\\project\\imageSuperResolution\\defog\\defog2.jpg");
    }
}
