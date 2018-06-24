package com.htht.weather.springCloudWeather.controller;

import com.htht.weather.springCloudWeather.service.CityDataService;
import com.htht.weather.springCloudWeather.service.WeatherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Weather Report Controller
 */
@RestController
@RequestMapping("/report")
public class WeatherReportController {

    @Autowired
    private CityDataService cityDataService;
    @Autowired
    private WeatherReportService weatherReportService;

    @GetMapping("/cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable String cityId, Model model) throws Exception {
        model.addAttribute("title","吴起伟的天气预报");
        model.addAttribute("cityId",cityId);
        model.addAttribute("cityList",cityDataService.listCity());
        model.addAttribute("report",weatherReportService.getDataByCityId(cityId));
        return new ModelAndView("weather/report","reportModel",model);
    }
}
