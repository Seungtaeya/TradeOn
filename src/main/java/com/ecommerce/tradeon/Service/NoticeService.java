package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Notice.NoticeDto;
import com.ecommerce.tradeon.Entity.Notice.Notice;
import com.ecommerce.tradeon.Repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional
    public void saveNotice(NoticeDto noticeDto) {
        Notice notice = new Notice(noticeDto.getTitle(), noticeDto.getCreatedAt(), noticeDto.getContent(), noticeDto.getState());

        noticeRepository.save(notice);
    }

    public List<NoticeDto> getNotices() {
        return noticeRepository.findAll().stream().map(NoticeDto::setForm).toList();
    }

    public NoticeDto findNoticeOneById(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지사항 입니다."));

        return NoticeDto.setForm(notice);
    }

    @Transactional
    public void DeleteNotice(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 공지사항 입니다."));
        noticeRepository.delete(notice);
    }
}
