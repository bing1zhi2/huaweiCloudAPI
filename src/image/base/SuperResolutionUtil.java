package image.base;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by bing1 on 2019/2/26.
 */
public class SuperResolutionUtil extends ImageEnhence {


    //放大倍数，默认为3，取值范围：3或4。
    private int scale=3;
    /*
     图像超分辨率重建采用的算法模式，支持ESPCN和SRCNN，默认 ESPCN。取值为：

            “ESPCN”：Efficient Sub-Pixel Convolutional Neural Network。

            “SRCNN”： Super-Resolution Convolutional Neural Network。
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
     * 調用 图像超分,不使用代理
     * @param namePath 圖片全路徑名字 F:\\work\\project\\imageSuperResolution\\darkenhance\\dark.jpg
     * @return
     * Map  statusCode, int 型
    jsonStr ,  string 型
     */
    public Map<String, Object> doImageEnhence(String namePath) {

        return imageEnhence(null, 0, null, null, namePath);
    }

    /**
     * 调用 图像超分,使用代理
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
