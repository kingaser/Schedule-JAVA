package com.sparta.schedule.repository;

import com.sparta.schedule.entity.CalendarDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarDateRepository extends JpaRepository<CalendarDate, String> {

    CalendarDate findByDate(String date);

}
