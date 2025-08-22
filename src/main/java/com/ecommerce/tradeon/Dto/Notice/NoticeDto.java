package com.ecommerce.tradeon.Dto.Notice;

import com.ecommerce.tradeon.Entity.Notice.Notice;
import com.ecommerce.tradeon.Enums.NoticeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDto {

    private Long id;
    private String title;
    private String content;
    private LocalDate createdAt;
    private NoticeStatus state;

    public static NoticeDto setForm(Notice notice) {
        NoticeDto dto = new NoticeDto();

        dto.setId(notice.getId());
        dto.setTitle(notice.getTitle());
        dto.setContent(notice.getContent());
        dto.setCreatedAt(notice.getCreatedAt());
        dto.setState(notice.getState());

        return dto;
    }
}
