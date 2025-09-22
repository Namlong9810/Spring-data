package com.example.QueryDataExample.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ScheduleJobTest {

    @Scheduled(initialDelay = 0)
    public void responseMessage() {
        log.info("Check first time at: " + LocalDateTime.now());
    }

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayJob() {
//        log.info("Schedule job delay at: " + LocalDateTime.now());
//    }

//    @Scheduled(fixedRate = 5000, initialDelay = 2000)
//    public void initialDelay() {
//        log.info("Schedule initial at: " + LocalDateTime.now());
//    }


    @Scheduled(cron = "0 */2 * * * ?")
    public void scheduleCron() {
        log.info("----  Schedule job every 5m: " + LocalDateTime.now());
    }


    @Scheduled(cron = "0 0 11 * * ?", zone = "Asia/Ho_Chi_Minh")
    public void scheduleCronWithTimeZone() {
        log.info("-------- Schedule job at 11 A.M. every day with zoneDateTime");
    }
}
