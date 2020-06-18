package com.kkxu.demo.common.utils;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2016102900775466";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCMhLEAxaiMT0sY/l6l/hMVA3CgBIruwGzrvWt63IC+loaOHhCdsdlivUtUdW715VG2YwCcUN5BcIDj7lHl4+LmPLfhso1oIflnx65L8OWprlxptK8uIVlVM7/8oNWX8KhkGRu2goSUcCcgg8Bk9Yca3ULYeSgfFLk4F4XWFQQJuvMsg8NlAoC8kDDK6vaefmNaLBvWNq0JQPvEo21WSsvePEFWqhNJqZfLfyiVQPxTe+1lgwQ1JrHhyEJX84KR0jgr2j4ALJF9Dp/xW3Ozqp+vPWlbYl5uz6VZru2FfqNcqnGh/rFB1v9IpxzuD/v4BYtlIVsxHeHdb/I3uUa9/mY3AgMBAAECggEAL9+lTlYJLoR1WXaQWI1PgwFBBcOGcgEtGsPWMm2dj1FJVnH3x0IohfNoDsZTn4MZa8IfGyKSFkl2IDG5xVD2pOpa25IxiyDIEAt/3m+cT0sQQRNF+7lw7g5Lgo5pNvLO7AuB3ZRqBwiOzA0XJ05fUU/Azm+4s54j3HBnMKDIV2fQtvsIQswySY2J542f3tZChxKbD310m74KYg2bP3GPwYCN8TH658vk2jvmhKRS7a7NrIDhnAgFrJFhMAcPJcXFNSaPz4kHMS0X0AwPqO+dK0Lh2pksQWwJrGvwOFyfSEFsFNflgiEeP3T9/DWCTzLlkXHA02a0k9a/pQd2ybBTAQKBgQDH69RmuAIlhuKi1+S2hUDTDg8N3I4nX0hPl0Z7OYTCK11rGyz/3iWoWTX8WjMD1i+yUUsOwF+y8yDRTEVs0Yo0VW40NvlhsmI3QMTvz/qwBATBvZraV+tnifLz87zU8Icml4IkHHS+GK6ch777tRPv70XJ/FNJCiC9aeRV4qVuJwKBgQCz7zERTdVHm085I3Wzb3tenSNb6oScfUWO6pars+DsCXQrPWpos86i+HfxbgUrjm4hRExX0aym0z1X5bQl7kUjLGpUj/R2ZLg63GMnSH/lFnID08UNdhja7anaJpGQXIlsWz/NsjHpfkPtcezbJrmZ8Wq4rg4bCyF88nCNO2RhcQKBgHYytOpiIkrDm90BXaSX1gKyTi441wbqK5t66d3S3O3bq6i2cX15frQwwl1Hit/a02pO69x12l63phB4UpeKr3ZPiA9w7TJYv/7A2W6bfTBxv8o36oTq9C/L/ItE8MDI0G79pqDo/hCiESJUk5LHtVjnE1DfUPJe/YpfP/OTQ5GDAoGAYrUkxclc5xtcKow5VWYi15R8gT/136MZNX2bijXzfYjyIYZfiXCaOuzzX45rZ6mK4NUGzGZJwHrBfCaW3PgJFXKYIiuWgBxgtGmSO0qaXqIhidfykZ/norIeK35c/ETHXpnwNnc6ykxqFygYN2DXEIxPb2CMfGluxYx6EgAy4QECgYEAh4tdZN9x0JRNnppH8JrsSg2/zkaKujZNUmq+tzIfuknrQ6d4MEElYlqjn3agkuK481RQV+Q8XM2jvGCYM+TVWp6p8wx/0sDDvZ1cIgVr1wnfroWIKVWZbGDKennLQNc3dj7YbKgfB8p7+LyDtyptX5WMeHRfDrWjOMM+2yaQUzI=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjZufka4ya9rH6xLRe+sSdDPQtkys9zKJl5H4czu3GHqzALX2v/QYXafRHF7tZSd3xu6vNi+Rwa1UQMbjsYYGdy8cjd037b1TyhDTNb8gFzL4hM5uOSbvgkMwysER2CkoS/SXxI49fkqaNwx49DeFHBGWGHCXZEtTsDTfl6ylkzZWPnNU6w/NHt1pVKYZXQjd8WcogCSQ1GhjPgxgPMF3vP7dQQr6EXNWI0zDvwdwmzPdsjonOVGO9JNAc1erCfuJ6jmPa/MQoEzTzz49Z5e0JdzpaxUfP5Rzgaof4Kz0vdyfdWm5HEcM5d5qpHRNME06FNFXSR5+JOy2jBQj0fQRYwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.baidu.com";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://www.baidu.com";
//    public static String return_url = "button";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志存放地址
    public static String log_path = "D:\\alipaylog";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
