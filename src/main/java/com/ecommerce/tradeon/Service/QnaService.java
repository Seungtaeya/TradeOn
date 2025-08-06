package com.ecommerce.tradeon.Service;

import com.ecommerce.tradeon.Dto.Qna.QnaDto;
import com.ecommerce.tradeon.Entity.Member.Member;
import com.ecommerce.tradeon.Entity.Qna.Qna;
import com.ecommerce.tradeon.Entity.product.Product;
import com.ecommerce.tradeon.Repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;
    private final MemberService memberService;
    private final ProductService productService;

    @Transactional
    public Qna saveQna(QnaDto qnaDto) {
        Member member = memberService.findByMemberId(qnaDto.getMemberId());
        Product product = productService.getProductEntity(qnaDto.getProductId());
        Qna qna = new Qna(qnaDto.getContent());

        if(qnaDto.getParentId() != null) {
            Qna qnaParent = getQnaParent(qnaDto);

            qna.addParent(qnaParent);
            qna.addMember(member);
            qna.addProduct(qnaParent.getProduct());

            return qnaRepository.save(qna);
        }


        qna.addMember(member);
        qna.addProduct(product);

        return qnaRepository.save(qna);
    }

    public Qna saveReply(QnaDto qnaDto) {
        Qna qnaParent = getQnaParent(qnaDto);
        Qna qna = new Qna(qnaDto.getContent());
        Member member = memberService.findByMemberId(qnaDto.getMemberId());
        Product product = productService.getProductEntity(qnaDto.getProductId());
        qna.addParent(qnaParent);
        qna.addMember(member);
        qna.addProduct(product);

        return qnaRepository.save(qna);
    }

    public List<QnaDto> findAllQna() {
        List<Qna> all = qnaRepository.findAll();

        return all.stream().map(QnaDto::setForm).toList();
    }

    public List<QnaDto> findQnaByProductId(Long productId) {
        return qnaRepository.findByProductId(productId).stream()
                .map(QnaDto::setForm)
                .toList();
    }


    private Qna getQnaParent(QnaDto qnaDto) {
        return qnaRepository.findById(qnaDto.getParentId())
                .orElseThrow(() -> new IllegalArgumentException("선택하신 질문은 존재하지 않습니다."));
    }
}
