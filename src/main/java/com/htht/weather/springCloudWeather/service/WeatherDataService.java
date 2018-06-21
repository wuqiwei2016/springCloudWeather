package com.htht.weather.springCloudWeather.service;

import com.htht.weather.springCloudWeather.vo.WeatherResponse;

/**
 * Created by wuqiw on 2018/6/19.
 */
public interface WeatherDataService {
    /**
     * 根据城市ID查询天气数据
     *
     * @param cityId
     * @return
     */
    WeatherResponse getDataByCityId(String cityId);


    /**
     * 根据城市名称查询天气数据
     *
     * @return
     */
    WeatherResponse getWeatherByCityName(String cityName);

    /**
     * 根据城市id来同步天气
     * @param cityId
     */
    void syncDateByCityId(String cityId);
}
