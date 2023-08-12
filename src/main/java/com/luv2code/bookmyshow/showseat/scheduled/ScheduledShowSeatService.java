package com.luv2code.bookmyshow.showseat.scheduled;

import com.luv2code.bookmyshow.showseat.ShowSeatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduledShowSeatService {

    private final ShowSeatRepository showSeatRepository;

    @Scheduled(fixedDelay = 10_000L*60)
    void unblockShowSeatBlockedForMoreThanTenMinutes() {
        int countUnblocked = showSeatRepository.deleteByCreatedTimeBefore(
                LocalDateTime.now().minusMinutes(10));

        log.info("{} show_seats are unblocked", countUnblocked);
    }
}
