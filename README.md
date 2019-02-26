# huawei  CloudAPI
HuaWei  API   demo  ,  image enhence

# 使用方法
引用 ais-java-client.jar 和 java-sdk-core .jar
修改ClientContextUtils 中配置的密钥

### 华为官方网站:  https://support.huaweicloud.com/api-image/image_03_0018.html

## 光照增強
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


## 去雾
image.base.DeFogEnhanceUtil


   #eg:
   DeFogEnhanceUtil enhance = new DeFogEnhanceUtil();
   enhance.setNatural_look(false); //不使用默认值时，调用前要设置参数，否则不用设置
   enhance.setGamma(1.6); //不使用默认值时，调用前要设置参数，否则不用设置
   enhance.doImageEnhence("F:\\work\\project\\imageSuperResolution\\defog\\defog2.jpg");


## 图像超分辨率
image.base.SuperResolutionUtil

    #eg:
    SuperResolutionUtil enhance = new SuperResolutionUtil();
    enhance.setScale(3); //不使用默认值时，调用前要设置参数，否则不用设置
    enhance.setModel("ESPCN"); //不使用默认值时，调用前要设置参数，否则不用设置
    enhance.doImageEnhence("F:\\work\\project\\imageSuperResolution\\superResolution\\sr.jpg");



# 效果
[效果展示](https://mp.csdn.net/postedit/87936375)

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
            <version>[2.9.8,)</version>
        </dependency>



        <dependency>
            <groupId>joda-time</groupId>
            <artifactId>joda-time</artifactId>
            <version>2.9.2</version>
        </dependency>


# 错误码

##公共
    AIS.0002	验证Token异常。
	AIS.0003	服务内部错误。
	AIS.0004	无权限操作。
	AIS.0005	服务不存在。
	AIS.0007	服务未开通。
	AIS.0010	缺少Header或者Header为空。
	AIS.0011	缺少请求参数或者参数为空。
	AIS.0012	请求参数不支持。
	AIS.0013	请求方法不允许。
	AIS.0014	输入数据JSON格式错误。
	AIS.0015	输入数据BASE64解码错误。
	AIS.0020	请求体大小超过限制。
	AIS.0021	请求的文件类型不支持。
	AIS.0022	URL不合法。
	AIS.0023	存储在OBS上的文件过大。
	AIS.0024	从OBS上获取文件失败。原因可能为签名过期或签名验证未通过等。
	AIS.0025	存储在OBS上的文件为空。
	AIS.0026	token类型错误，应使用project类型的token。
	AIS.0027	此账户已被冻结。
	AIS.0028	此任务不属于当前用户。
	AIS.0029	从外网下载文件失败。检查文件url是否正确以及是否在支持的范围内。
	AIS.0030	任务未找到。任务已过期被清理了或者传入的job id错误，检查job id是否正确。
	AIS.0031	未处理完的任务总数达到限制。暂停提交新任务。
	AIS.0032	月免费调用量已使用完。如需继续使用给关联的华为云账户充值即可。
##图像识别	AIS.0201	获取输入图像异常。
	AIS.0202	图片格式不支持。
	AIS.0203	输入参数不符合规范。
	AIS.0204	图片位深不支持。
	AIS.0205	图片分辨率超限。
	AIS.0206	算法计算失败。
	AIS.0401	输入参数有误，请输入视频URL。
	AIS.0402	下载视频失败，请检查视频URL是否正确。
	AIS.0403	未能识别此视频的背景音乐。
	AIS.0404	背景音乐识别内部错误。
	AIS.0501	输入参数不合法。
	AIS.0502	图像格式不支持。
	AIS.0503	图像受损。
	AIS.0504	图像大小不符合要求。
	AIS.0505	算法运行失败。
	AIS.0506	出现内部错误。





#状态码

状态码	编码	状态说明
100	Continue	继续请求。
这个临时响应用来通知客户端，它的部分请求已经被服务器接收，且仍未被拒绝。
101	Switching Protocols	切换协议。只能切换到更高级的协议。
例如，切换到HTTPS的新版本协议。
200	OK	服务器已成功处理了请求。
201	Created	创建类的请求完全成功。
202	Accepted	已经接受请求，但未处理完成。
203	Non-Authoritative Information	非授权信息，请求成功。
204	No Content	请求完全成功，同时HTTP响应不包含响应体。
在响应OPTIONS方法的HTTP请求时返回此状态码。
205	Reset Content	重置内容，服务器处理成功。
206	Partial Content	服务器成功处理了部分GET请求。
300	Multiple Choices	多种选择。请求的资源可包括多个位置，相应可返回一个资源特征与地址的列表用于用户终端（例如：浏览器）选择。
301	Moved Permanently	永久移动，请求的资源已被永久的移动到新的URI，返回信息会包括新的URI。
302	Found	资源被临时移动。
303	See Other	查看其它地址。
使用GET和POST请求查看。
304	Not Modified	所请求的资源未修改，服务器返回此状态码时，不会返回任何资源。
305	Use Proxy	所请求的资源必须通过代理访问。
306	Unused	已经被废弃的HTTP状态码。
400	Bad Request	非法请求。
建议直接修改该请求，不要重试该请求。
401	Unauthorized	在客户端提供认证信息后，返回该状态码，表明服务端指出客户端所提供的认证信息不正确或非法。
402	Payment Required	保留请求。
403	Forbidden	请求被拒绝访问。
返回该状态码，表明请求能够到达服务端，且服务端能够理解用户请求，但是拒绝做更多的事情，因为该请求被设置为拒绝访问，建议直接修改该请求，不要重试该请求。
404	Not Found	所请求的资源不存在。
建议直接修改该请求，不要重试该请求。
405	Method Not Allowed	请求中带有该资源不支持的方法。
建议直接修改该请求，不要重试该请求。
406	Not Acceptable	服务器无法根据客户端请求的内容特性完成请求。
407	Proxy Authentication Required	请求要求代理的身份认证，与401类似，但请求者应当使用代理进行授权。
408	Request Timeout	服务器等候请求时发生超时。
客户端可以随时再次提交该请求而无需进行任何更改。
409	Conflict	服务器在完成请求时发生冲突。
返回该状态码，表明客户端尝试创建的资源已经存在，或者由于冲突请求的更新操作不能被完成。
410	Gone	客户端请求的资源已经不存在。
返回该状态码，表明请求的资源已被永久删除。
411	Length Required	服务器无法处理客户端发送的不带Content-Length的请求信息。
412	Precondition Failed	未满足前提条件，服务器未满足请求者在请求中设置的其中一个前提条件。
413	Request Entity Too Large	由于请求的实体过大，服务器无法处理，因此拒绝请求。为防止客户端的连续请求，服务器可能会关闭连接。如果只是服务器暂时无法处理，则会包含一个Retry-After的响应信息。
414	Request URI Too Long	请求的URI过长（URI通常为网址），服务器无法处理。
415	Unsupported Media Type	服务器无法处理请求附带的媒体格式。
416	Requested Range Not Satisfiable	客户端请求的范围无效。
417	Expectation Failed	服务器无法满足Expect的请求头信息。
422	Unprocessable Entity	请求格式正确，但是由于含有语义错误，无法响应。
429	Too Many Requests	表明请求超出了客户端访问频率的限制或者服务端接收到多于它能处理的请求。建议客户端读取相应的Retry-After首部，然后等待该首部指出的时间后再重试。
500	Internal Server Error	表明服务端能被请求访问到，但是不能理解用户的请求。
501	Not Implemented	服务器不支持请求的功能，无法完成请求。
502	Bad Gateway	充当网关或代理的服务器，从远端服务器接收到了一个无效的请求。
503	Service Unavailable	被请求的服务无效。
建议直接修改该请求，不要重试该请求。
504	Gateway Timeout	请求在给定的时间内无法完成。客户端仅在为请求指定超时（Timeout）参数时会得到该响应。
505	HTTP Version Not Supported	服务器不支持请求的HTTPS协议的版本，无法完成处理。
