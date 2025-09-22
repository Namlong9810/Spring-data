package com.example.QueryDataExample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
@Slf4j
public class ZoneList {
    @Scheduled(initialDelay = 0)
    public void printZoneList() {
        ZoneId.getAvailableZoneIds()
                .stream()
                .sorted()
                .forEach(zone -> log.info("Zone {}", zone));
    }
}
