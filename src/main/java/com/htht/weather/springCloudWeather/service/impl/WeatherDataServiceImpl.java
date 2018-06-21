package com.htht.weather.springCloudWeather.service.impl;

import com.htht.weather.springCloudWeather.service.WeatherDataService;
import com.htht.weather.springCloudWeather.util.GsonUtils;
import com.htht.weather.springCloudWeather.vo.WeatherResponse;
import com.htht.weather.springCloudWeather.vo.Weathers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/** WeatherDataService 实现
 * Created by wuqiw on 2018/6/19.
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService{
    private static final String WEATHER_URI = "https://www.sojson.com/open/api/weather/json.shtml?";

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
        //2、通过getForEntity()调用
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeahter(uri);
    }


    private WeatherResponse doGetWeahter(String uri) {
        //todo 先去缓存中查找 如果没有 那就调用第三方接口
        String data = "";
        Weathers strBody = null;
        WeatherResponse resp = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        if(stringRedisTemplate.hasKey(uri)){
            data =  ops.get(uri);
            resp = (WeatherResponse) GsonUtils.getInstance().fromJson(data,WeatherResponse.class);
        }else{  //第三方去调用
            ResponseEntity<Weathers> respString = this.restTemplate.getForEntity(uri, Weathers.class);
          /*  mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;*/
            if (respString.getStatusCodeValue() == 200) {
                strBody = respString.getBody();
            }
            if(strBody != null){
                resp.setData(strBody);
                resp.setStatus(1);
                resp.setDesc("正常");
                data = GsonUtils.getInstance().toJson(resp);
                ops.set(uri,data,TIME_OUT, TimeUnit.SECONDS);
            }
        }
        return resp;
    }

    @Override
    public void syncDateByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        this.saveWeatherData(uri);
    }

    /**
     * 把天气数据放在缓存
     */
    private void saveWeatherData(String uri){
        String key = uri;
        String strBody = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        //调用服务接口来获取
        ResponseEntity<String>  resString  = restTemplate.getForEntity(uri,String.class);

        if(resString.getStatusCodeValue() == 200){
            strBody = resString.getBody();
        }
        // 数据写入缓存
        ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
    }
}
