package image.base;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by bing1 on 2019/2/26.
 */
public class DeFogEnhanceUtil extends ImageEnhence{


    //gamma矫正值，默认为1.5，取值范围：[0.1,10]。
    private double gamma=1.5;
    //是否保持自然观感，默认是true。如果选择false，则图像只追求去雾效果，图像视觉效果可能不自然。
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
     * 調用光照增強,不使用代理
     * @param namePath 圖片全路徑名字 F:\\work\\project\\imageSuperResolution\\darkenhance\\dark.jpg
     * @return
     * Map  statusCode, int 型
    jsonStr ,  string 型
     */
    public Map<String, Object> doImageEnhence(String namePath) {

        return imageEnhence(null, 0, null, null, namePath);
    }

    /**
     * 调用光照增强,使用代理
     * @param host 代理域名
     * @param port 端口
     * @param userName 用户名
     * @param passwd  密码
     * @param namePath 图片全路径名字 F:\\work\\project\\imageSuperResolution\\darkenhance\\dark.jpg
     * @return Map
     * statusCode, int 型
    jsonStr ,  string 型
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
