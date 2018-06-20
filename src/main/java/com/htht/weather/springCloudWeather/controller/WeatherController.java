package com.htht.weather.springCloudWeather.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.htht.weather.springCloudWeather.service.WeatherDataService;
import com.htht.weather.springCloudWeather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/** weather controller
 * Created by wuqiw on 2018/6/19.
 */
@Controller
@RequestMapping(value = "/weather",produces = "text/plain;charset=UTF-8")
public class WeatherController {
    @Autowired
    private WeatherDataService weatherDataService;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/cityId/{cityId}")
    public WeatherResponse getWeatherByCityId(@PathVariable("cityId") String cityId){
        return  weatherDataService.getDataByCityId(cityId);
    }
    @GetMapping("/cityName/{cityName}")
    public WeatherResponse getWeatherByCityName(@PathVariable("cityName") String cityName){
        return  weatherDataService.getWeatherByCityName(cityName);
    }
    @RequestMapping("/json")
    public Object genJson() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("descp", "this is spring rest template sample");
        return json;
    }
    @RequestMapping(value ="/hello")
    public String hello(){
        String url = "http://localhost:8080/json";
        JSONObject json = restTemplate.getForEntity(url, JSONObject.class).getBody();
        return json.toString();
    }
}
