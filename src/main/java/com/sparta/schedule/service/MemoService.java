package com.sparta.schedule.service;


import com.sparta.schedule.dto.MemoRequestDto;
import com.sparta.schedule.dto.MemoResponseDto;
import com.sparta.schedule.entity.Memo;
import com.sparta.schedule.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
public class MemoService {

        private final MemoRepository memoRepository;

        // 잔체 메모 조회
        @Transactional(readOnly = true)
        public List<MemoResponseDto> memos(){
            List<Memo> memos = memoRepository.findAll();
            List<MemoResponseDto> responseDtos = new ArrayList<>();
            for (Memo memo : memos) {
                responseDtos.add(new MemoResponseDto(memo));
            }
            return responseDtos;
        }

        // 메모 생성
        public MemoResponseDto createMemo(MemoRequestDto memoRequestDto){
            Memo memo = memoRepository.save(new Memo(memoRequestDto));
            return new MemoResponseDto(memo);
        }

        // 메모 수정
        public MemoResponseDto updateMemo(Long id, MemoRequestDto memoRequestDto){
            Memo memo = memoRepository.findById(id).orElseThrow(
                    () -> new NullPointerException("메모가 존재하지 않습니다.")
            );

            // 메모 수정하기
            memo.update(memoRequestDto);
            return new MemoResponseDto(memo);
        }

}
