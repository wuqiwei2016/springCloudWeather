package com.htht.weather.springCloudWeather.controller;

import com.htht.weather.springCloudWeather.service.WeatherDataService;
import com.htht.weather.springCloudWeather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** weather controller
 * Created by wuqiw on 2018/6/19.
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherDataService weatherDataService;

    @GetMapping("/cityId/{cityId}")
    public WeatherResponse getWeatherByCityId(@PathVariable("cityId") String cityId){
        return  weatherDataService.getDataByCityId(cityId);
    }
    @GetMapping("/cityName/{cityName}")
    public WeatherResponse getWeatherByCityName(@PathVariable("cityName") String cityName){
        return  weatherDataService.getWeatherByCityName(cityName);
    }
}
