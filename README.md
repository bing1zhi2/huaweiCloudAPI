# huawei  CloudAPI
HuaWei  API   demo  ,  image enhence

# 使用方法
光照增強
image.base.DKEnhanceUtil



     # 調用光照增強,不使用代理
     * @param namePath 圖片全路徑名字 F:\\work\\project\\imageSuperResolution\\darkenhance\\dark.jpg
     * @return
     * Map  statusCode, int 型
            jsonStr ,  string 型

     # 调用光照增强,使用代理
     * @param host 代理域名
     * @param port 端口
     * @param userName 用户名
     * @param passwd  密码
     * @param namePath 图片全路径名字 F:\\work\\project\\imageSuperResolution\\darkenhance\\dark.jpg
     * @return Map
     * statusCode, int 型
       jsonStr ,  string 型


   #eg:
   DKEnhanceUtil enhance = new DKEnhanceUtil();
   enhance.setBrightness(0.8); //不使用默认值时，调用前要设置参数，否则不用设置
   enhance.doImageEnhence("F:\\work\\project\\imageSuperResolution\\darkenhance\\dark.jpg");


#去雾
image.base.DeFogEnhanceUtil


   #eg:
   DeFogEnhanceUtil enhance = new DeFogEnhanceUtil();
   enhance.setNatural_look(false); //不使用默认值时，调用前要设置参数，否则不用设置
   enhance.setGamma(1.6); //不使用默认值时，调用前要设置参数，否则不用设置
   enhance.doImageEnhence("F:\\work\\project\\imageSuperResolution\\defog\\defog2.jpg");


# 图像超分辨率
image.base.SuperResolutionUtil

    #eg:
    SuperResolutionUtil enhance = new SuperResolutionUtil();
    enhance.setScale(3); //不使用默认值时，调用前要设置参数，否则不用设置
    enhance.setModel("ESPCN"); //不使用默认值时，调用前要设置参数，否则不用设置
    enhance.doImageEnhence("F:\\work\\project\\imageSuperResolution\\superResolution\\sr.jpg");





# 依赖包：
maven:

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.32</version>
        </dependency>

        <dependency>
            <groupId>commons-lang</groupId>
            <artifactId>commons-lang</artifactId>
            <version>2.6</version>
        </dependency>

        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>2.4</version>
        </dependency>

        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.7</version>
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient-cache</artifactId>
            <version>4.5.7</version>
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpmime</artifactId>
            <version>4.5.7</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.9.5</version>
        </dependency>


        <dependency>
            <groupId>joda-time</groupId>
            <artifactId>joda-time</artifactId>
            <version>2.9.2</version>
        </dependency>
