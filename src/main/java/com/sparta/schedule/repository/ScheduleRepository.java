package com.sparta.schedule.repository;

import com.sparta.schedule.entity.CalendarDate;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByCalendarDate(CalendarDate calendarDate);
//    Optional<Schedule> findByIdAndDate(Long id, String date);
    Optional<Schedule> findByIdAndUser(Long id, User user);

    Optional<Schedule> findByComplete(boolean complete);
}
