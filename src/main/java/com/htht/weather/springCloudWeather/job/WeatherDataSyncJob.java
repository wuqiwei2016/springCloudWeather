package com.htht.weather.springCloudWeather.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/** 定时获取天气数据
 * Created by wuqiw on 2018/6/19.
 */
@Slf4j
public class WeatherDataSyncJob extends QuartzJobBean{
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Hello Wuqiwei");
    }
}
