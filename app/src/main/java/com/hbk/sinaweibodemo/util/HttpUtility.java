package com.hbk.sinaweibodemo.util;

import android.content.Context;
import com.hbk.sinaweibodemo.login.AccessTokenKeeper;
import org.codehaus.jackson.map.DeserializationConfig;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HongBinKim on 14/8/12.
 */
public class HttpUtility {

    public static RestTemplate getMappingJacksonTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
        converter.getObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        restTemplate.getMessageConverters().add(converter);
        return restTemplate;
    }

    public static RestTemplate getStringHttpMessageTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        StringHttpMessageConverter converter = new StringHttpMessageConverter();

        restTemplate.getMessageConverters().add(converter);
        return restTemplate;
    }

    public static Map<String, String> getBaseHttpParmas(Context context) {
        Map<String, String> pram = new HashMap<String, String>();
        pram.put(Constants.ACCESS_TOKEN, AccessTokenKeeper.getAccessToken(context));
        pram.put("source", Constants.APP_KEY);
        return pram;
    }
}
