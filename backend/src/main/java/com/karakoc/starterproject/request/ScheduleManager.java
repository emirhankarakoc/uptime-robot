package com.karakoc.starterproject.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
@Slf4j
@AllArgsConstructor
public class ScheduleManager implements SchedulerService{
    private final RequestService service;

    @Scheduled(cron = "59 * * * * *", zone = "GMT+3")
    public void scheduledTask() {
        log.info("Calisti.");
        service.requestAll();
    }
    }
