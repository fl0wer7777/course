package com.haley.demo.utils;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NativeHttpClient;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.CIDResult;
import cn.jpush.api.push.GroupPushClient;
import cn.jpush.api.push.GroupPushResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.*;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.*;
import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NativeHttpClient;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;

import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class SendUtils {
    protected static final Logger LOG = LoggerFactory.getLogger(SendUtils.class);

    // demo App defined in resources/jpush-api.conf
    protected static final String APP_KEY = "f8d8ccde2fc3cd5c8329e44f";
    protected static final String MASTER_SECRET = "7d764960df68ef8bdec1754e";

    public static final String ALERT = "测试广播";

    public static void main(String[] args) {

        /*testSendPush1();*/

    }


    public static void testSendPush1(String name, String message) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        final PushPayload payload = buildPushObject_all_alias_alert(name, message);
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
            System.out.println(result);
            // 如果使用 NettyHttpClient，需要手动调用 close 方法退出进程
            // If uses NettyHttpClient, call close when finished sending request, otherwise process will not exit.
            // jpushClient.close();
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            LOG.error("Sendno: " + payload.getSendno());

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.error("Sendno: " + payload.getSendno());
        }
    }

    public static PushPayload buildPushObject_all_alias_alert(String name, String message) {

        Map<String, String> extras = new HashMap<String, String>();
        extras.put("test", "测试参数");
        // you can set anything you want in this builder, read the document to avoid collision.
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias("1"))
                .setMessage(Message.newBuilder()
                        .setMsgContent("春江潮水连海平")
                        .build())
                .setNotification(Notification.newBuilder()
                        .setAlert(message)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(name)
                                .addExtras(extras).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
//                .setSMS(SMS.newBuilder()
//                        .setDelayTime(1000)
//                        .setTempID(2000)
//                        .addPara("Test", 1)
//                        .setActiveFilter(true)
//                        .build())
//                .setNotification3rd(Notification3rd.newBuilder()
//                        .setContent("Hi, JPush")
//                        .setTitle("msg testing")
//                        .setChannelId("channel1001")
//                        .setUriActivity("cn.jpush.android.ui.OpenClickActivity")
//                        .setUriAction("cn.jpush.android.intent.CONNECTION")
//                        .setBadgeAddNum(1)
//                        .setBadgeClass("com.test.badge.MainActivity")
//                        .setSound("sound")
//                        .addExtra("news_id", 124)
//                        .addExtra("my_key", "a value")
//                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setTimeToLive(43200)
                        .build())
                .build();

    }


    public static PushResult testSendPush(String name, String message) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        final PushPayload payload = buildPushObject_all_alias_alert(name, message);
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("获得结果" + result);
            System.out.println(result);

            return result;
        } catch (APIConnectionException e) {
            LOG.error("连接错误 ", e);
            LOG.error("发送号码为: " + payload.getSendno());

        } catch (APIRequestException e) {
            LOG.error("极光服务器回复错误 ", e);
            LOG.error("Sendno: " + payload.getSendno());
        }
        return null;
    }


    public static PushPayload buildPushObject_android_and_ios() {
        Map<String, String> extras = new HashMap<String, String>();
        extras.put("内容1", "我就是内容1111");
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias("haha"))
                .setNotification(Notification.newBuilder()
                        .setAlert("弹框内容")
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle("弹框标题")
                                .addExtras(extras).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1)
                                .addExtra("extra_key", "extra_value").build())
                        .build())
                .build();
    }


}
