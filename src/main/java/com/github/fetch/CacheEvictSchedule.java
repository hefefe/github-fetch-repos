package com.github.fetch;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CacheEvictSchedule {

    @Scheduled(cron = "0 0 1 * * */2")
    @CacheEvict(value = "userRepos", allEntries = true)
    public void evictAllEntriesFromCache() {
    }
}
