package com.htht.weather.springCloudWeather;

import com.htht.weather.springCloudWeather.service.impl.RunService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;

/**
 * Created by wuqiw on 2018/6/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RunServiceTest {
    @Autowired
    RunService runService;

    @Test
    public void getTest() throws URISyntaxException {
       // runService.getTestGet();
       runService.getTestPost();
        //runService.getTestPostParam();
       // runService.getTestPut();
       // runService.getTestDel();
    }
}
