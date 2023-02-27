package com.sparta.schedule.repository;

import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByUser(User user);
    Optional<Schedule> findByIdAndDate(Long id, String date);
    Optional<Schedule> findByIdAndUser(Long id, User user);
}
