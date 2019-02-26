package image.base;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by bing1 on 2019/2/26.
 * <p>
 * 光照增强api
 * 使用方法
 *
 * <p>
 * doImageEnhence("F:\\work\\project\\imageSuperResolution\\darkenhance\\dark.jpg");
 * <p>
 * 使用代理：
 * doImageEnhence(String host,int port,String userName,String passwd,String namePath)
 */
public class DKEnhanceUtil extends ImageEnhence {

    //亮度值，默认值0.9，取值范围：[0,1]。
    private double brightness=0.9;

    public double getBrightness() {
        return brightness;
    }

    public void setBrightness(double brightness) {
        this.brightness = brightness;
    }


    /**
     * 調用光照增強,不使用代理
     * @param namePath 圖片全路徑名字 F:\\work\\project\\imageSuperResolution\\darkenhance\\dark.jpg
     * @return
     * Map  statusCode, int 型
            jsonStr ,  string 型
     */
    public  Map<String, Object> doImageEnhence(String namePath) {

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
        json.put("brightness", getBrightness());
        return json.toJSONString();
    }




    public static void main(String[] args) {
        DKEnhanceUtil enhance = new DKEnhanceUtil();
        enhance.setBrightness(0.8);
        enhance.doImageEnhence("F:\\work\\project\\imageSuperResolution\\darkenhance\\dark.jpg");
    }
}
