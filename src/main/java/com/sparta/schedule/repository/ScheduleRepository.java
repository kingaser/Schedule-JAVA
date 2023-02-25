package com.sparta.schedule.repository;

import com.sparta.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByIdAndDateAndUserId(Long id, String date, Long userId);

    Schedule deleteByIdAndDateAndUserId(Long id, String date, Long userId);
}
