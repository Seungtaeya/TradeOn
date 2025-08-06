package com.ecommerce.tradeon.Dto.Qna;

import com.ecommerce.tradeon.Entity.Qna.Qna;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class QnaDto {

    private Long id;
    private Long memberId;
    private String memberName;
    private Long productId;
    private String productTitle;
    private Long productMemberId;
    private Long parentId;
    private String parentContent;
    private String content;
    private List<QnaDto> replies;


    public static QnaDto setForm(Qna qna) {
        QnaDto dto = new QnaDto();

        dto.setId(qna.getId());
        dto.setMemberId(qna.getMember().getId());
        dto.setMemberName(qna.getMember().getUsername());
        dto.setProductId(qna.getProduct().getId());
        dto.setProductTitle(qna.getProduct().getTitle());
        dto.setProductMemberId(qna.getProduct().getSeller_id().getId());
        if(qna.getParent() != null) {
            dto.setParentId(qna.getParent().getId());
            dto.setParentContent(qna.getParent().getContent());
        }
        dto.setReplies(QnaDto.setFormList(qna.getReplies()));
        dto.setContent(qna.getContent());

        return dto;
    }

    public static List<QnaDto> setFormList(List<Qna> qnas) {
        List<QnaDto> list = new ArrayList<>();
        for (Qna qna : qnas) {
            QnaDto dto = new QnaDto();
            dto.setId(qna.getId());
            dto.setMemberId(qna.getMember().getId());
            dto.setMemberName(qna.getMember().getUsername());
            dto.setProductId(qna.getProduct().getId());
            dto.setProductTitle(qna.getProduct().getTitle());
            if(qna.getParent() != null) {
                dto.setParentId(qna.getParent().getId());
                dto.setParentContent(qna.getParent().getContent());
            }
            dto.setContent(qna.getContent());
            list.add(dto);
        }
        return list;
    }
}
