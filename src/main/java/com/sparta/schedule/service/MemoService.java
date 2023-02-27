package com.sparta.schedule.service;

import com.sparta.schedule.dto.request.MemoRequestDto;
import com.sparta.schedule.dto.response.MemoResponseDto;
import com.sparta.schedule.entity.Memo;
import com.sparta.schedule.repository.MemoRepository;
import com.sparta.schedule.security.UserDetailsImpl;
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
        public MemoResponseDto createMemo(MemoRequestDto memoRequestDto, UserDetailsImpl userDetails){
            Memo memo =  memoRepository.save(Memo.builder()
                    .memoRequestDto(memoRequestDto)
                    .user(userDetails.getUser())
                    .build());

            return new MemoResponseDto(memo);
        }

        // 메모 수정
        public MemoResponseDto updateMemo(Long id, MemoRequestDto memoRequestDto, UserDetailsImpl userDetails){
           Memo memo = memoRepository.findById(id).orElseThrow(
                   () -> new IllegalArgumentException("메모가 존재하지 않습니다.")
           );
            // 메모 수정하기
            memo.update(memoRequestDto, userDetails.getUser());
            memoRepository.saveAndFlush(memo); // 수정된 메모 저장
            return new MemoResponseDto(memo);
        }
}
