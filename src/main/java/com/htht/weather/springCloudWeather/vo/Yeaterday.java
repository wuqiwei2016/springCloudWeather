package com.htht.weather.springCloudWeather.vo;

import lombok.Data;

import java.io.Serializable;

/**昨日天气
 * Created by wuqiw on 2018/6/19.
 */
@Data
public class Yeaterday implements Serializable {
    private static final long serialVersionUID = -5497708218229644468L;
    private String date;
    private String high;
    private String fx;
    private String low;
    private String fl;
    private String type;
}
