package com.sparta.schedule.service;

import com.sparta.schedule.dto.request.MemoRequestDto;
import com.sparta.schedule.dto.response.MemoResponseDto;
import com.sparta.schedule.dto.response.MessageResponseDto;
import com.sparta.schedule.entity.Memo;
import com.sparta.schedule.repository.MemoRepository;
import com.sparta.schedule.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        public ResponseEntity<List<MemoResponseDto>> memos(){
            List<Memo> memos = memoRepository.findAll();
            List<MemoResponseDto> responseDtos = new ArrayList<>();
            for (Memo memo : memos) {
                responseDtos.add(new MemoResponseDto(memo));
            }
            return ResponseEntity.ok().body(responseDtos);
        }

        // 메모 생성
        public ResponseEntity<MemoResponseDto> createMemo(MemoRequestDto memoRequestDto, UserDetailsImpl userDetails){
            Memo memo =  memoRepository.save(Memo.builder()
                    .memoRequestDto(memoRequestDto)
                    .user(userDetails.getUser())
                    .build());

            return ResponseEntity.ok(new MemoResponseDto(memo));
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

        public ResponseEntity<MessageResponseDto> deleteMemo(Long id, UserDetailsImpl userDetails){
            Memo memo = memoRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("메모가 존재하지 않습니다.")
            );
            // 메모 수정하기
            memoRepository.deleteById(id); // 수정된 메모 저장
            return ResponseEntity.ok().body(MessageResponseDto.User_ServiceCode(HttpStatus.OK, "삭제 완료"));
        }
}
