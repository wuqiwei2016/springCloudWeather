package com.htht.weather.springCloudWeather.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** 未来天气
 * Created by wuqiw on 2018/6/19.
 */
@Data
public class Forecast implements Serializable{
    private static final long serialVersionUID = -6913355958645182009L;
    private String date;
    private String high;
    private String fengli;
    private String low;
    private String fengxiang;
    private String type;

}
