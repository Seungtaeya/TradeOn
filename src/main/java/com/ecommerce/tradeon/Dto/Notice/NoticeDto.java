package com.ecommerce.tradeon.Dto.Notice;

import com.ecommerce.tradeon.Entity.Notice.Notice;
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
    private LocalDate createdAt;
    private boolean state;

    public static NoticeDto setForm(Notice notice) {
        NoticeDto dto = new NoticeDto();

        dto.setId(notice.getId());
        dto.setTitle(notice.getTitle());
        dto.setCreatedAt(notice.getCreatedAt());
        dto.setState(notice.isState());

        return dto;
    }
}
