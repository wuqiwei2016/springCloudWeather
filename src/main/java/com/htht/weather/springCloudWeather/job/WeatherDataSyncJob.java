package com.htht.weather.springCloudWeather.job;

import com.htht.weather.springCloudWeather.service.CityDataService;
import com.htht.weather.springCloudWeather.service.WeatherDataService;
import com.htht.weather.springCloudWeather.vo.City;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/** 定时获取天气数据
 * Created by wuqiw on 2018/6/19.
 */
@Slf4j
public class WeatherDataSyncJob extends QuartzJobBean{

    @Autowired
    private CityDataService cityDataService;

    @Autowired
    private WeatherDataService weatherDataService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Weather Data Sync Job. Start！");

        //获取城市列表
        List<City> cityList = null;

        try {
            cityList = cityDataService.listCity();
        }catch (Exception e){
            log.error("Exception!", e);
        }

        //遍历城市id获取天气
        for(City city : cityList){
           String cityId=  city.getCityId();
            log.info("Weather Data Sync Job, cityId:" + cityId);
            weatherDataService.syncDateByCityId(cityId);
        }
        log.info("Weather Data Sync Job. End！");
    }
}
