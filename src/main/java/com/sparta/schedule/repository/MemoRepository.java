package com.sparta.schedule.repository;

import com.sparta.schedule.entity.Memo;
import com.sparta.schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
