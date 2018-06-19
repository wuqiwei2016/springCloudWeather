package com.htht.weather.springCloudWeather.service;

import com.htht.weather.springCloudWeather.vo.WeatherResponse;

/**
 * Created by wuqiw on 2018/6/19.
 */
public interface WeatherDataService {
    WeatherResponse getDataByCityId(String cityId);

    WeatherResponse getWeatherByCityName(String cityName);
}
