package com.htht.weather.springCloudWeather.service.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.htht.weather.springCloudWeather.service.WeatherDataService;
import com.htht.weather.springCloudWeather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/** WeatherDataService 实现
 * Created by wuqiw on 2018/6/19.
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService{
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

    private static final long TIME_OUT = 1800L;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.doGetWeahter(uri);
    }

    @Override
    public WeatherResponse getWeatherByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeahter(uri);
    }
    private WeatherResponse doGetWeahter(String uri) {
        //todo 先去缓存中查找 如果没有 那就调用第三方接口
        String key = uri;
        String strBody = null;
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse resp = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        if(stringRedisTemplate.hasKey(uri)){
            strBody =  ops.get(key);
        }else{
        //todo 去第三方调用
            ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
            if (respString.getStatusCodeValue() == 200) {
                strBody = respString.getBody();
            }
            //数据写入缓存
            ops.set(key,strBody,TIME_OUT, TimeUnit.SECONDS);
        }
        try {
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }
}
