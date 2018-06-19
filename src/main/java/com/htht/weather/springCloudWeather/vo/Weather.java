package com.htht.weather.springCloudWeather.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/** 天气信息
 * Created by wuqiw on 2018/6/19.
 */
@Data
public class Weather implements Serializable {
    private static final long serialVersionUID = -6708454704979535880L;
    private String city;
    private String aqi;
    private String ganmao;
    private Yeaterday yesterday;
    private List<Forecast> forecast;
}
