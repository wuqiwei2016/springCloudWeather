package com.htht.weather.springCloudWeather.service.impl;

import com.htht.weather.springCloudWeather.service.WeatherDataService;
import com.htht.weather.springCloudWeather.service.WeatherReportService;
import com.htht.weather.springCloudWeather.vo.Weather;
import com.htht.weather.springCloudWeather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private WeatherDataService weatherDataService;
    @Override
    public Weather getDataByCityId(String cityId) {
        WeatherResponse resp = weatherDataService.getDataByCityId(cityId);
        return resp.getData();
    }
}
