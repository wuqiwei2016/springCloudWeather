package com.htht.weather.springCloudWeather.service;

import com.htht.weather.springCloudWeather.vo.Weather;

public interface WeatherReportService {


    /**
     * 根据城市ID查询天气信息
     * @param cityId
     * @return
     */
    Weather getDataByCityId(String cityId);
}
