# springboot-tradeOn
미니 쇼핑몰 개인 프로젝트입니다.

# 사용한 기술 스택
  - java17
  - springboot
  - spring data jpa
  - querydsl
  - mysql

# 핵심 구현 기능
  - 상품
    - 상품 등록/수정/삭제, 이미지 업로드
    - 옵션 관리, 옵션 선택 주문
    - 최신/인기/중고/많이 본 상품 노출

  - 검색
    - querydsl 동적 검색(상품이름, 지역 이름)
    - 최상위 카테고리 선택 시 하위 카테고리 상품까지 노출
   
  - 장바구니/주문
    - 장바구니 담기/삭제/ 주문

  - 고객 커뮤니케이션
    - 리뷰/별점, Q&A 기능
    - 공지사항(목록/상세) 홈 화면 배너 노출

  # 이미지
  -홈 화면
    - 카테고리, 배너, 최신/인기/많이 본 상품/중고/신상품 순으로 출력
  <img width="1436" height="2248" alt="스크린샷 2025-08-23 오후 10 20 31" src="https://github.com/user-attachments/assets/7b89f3eb-9792-4fe7-b613-71228848c4c4" />

  - 마이 페이지
    - 간단한 회원정보 기능
    - 회원이 등록한 상품 
  <img width="1888" height="862" alt="스크린샷 2025-08-23 오후 10 32 12" src="https://github.com/user-attachments/assets/0d23c6e7-bda9-4489-ad19-fc6de23c06d7" />

  - 마이 페이지 -> 주문 내역
    - 주문 번호와 주문한 상품의 정보를 출력
    - 주문 상태 표시
    - 상세보기 클릭시 주문 상품 정보 리뷰 등록 가능
  <img width="1503" height="866" alt="스크린샷 2025-08-23 오후 10 37 39" src="https://github.com/user-attachments/assets/c9d8e215-a5d5-487b-83e6-34a40fa3b56c" />
  <img width="1046" height="593" alt="스크린샷 2025-08-23 오후 10 47 35" src="https://github.com/user-attachments/assets/df54dc98-bfd9-4797-9ef4-5565c796d692" />
  
  - 마이 페이지 -> 찜 목록
    - 사용자가 원하는 이름으로 찜 목록 생성
    - 목록 클릭시 상품 이미지 비동기로 가져오도록 구현
<img width="1046" height="593" alt="스크린샷 2025-08-23 오후 10 41 55" src="https://github.com/user-attachments/assets/c847bc29-4935-40f4-95b7-3ea7a2d28dea" />
<img width="1046" height="593" alt="스크린샷 2025-08-23 오후 10 42 00" src="https://github.com/user-attachments/assets/dc93ee3d-9a0a-40c3-bd1c-ea0bda892432" />

  - 마이 페이지 -> 리뷰
    - 사용자가 작성한 리뷰 정보를 가져오도록 구현(수정/삭제)
<img width="1046" height="593" alt="스크린샷 2025-08-23 오후 10 51 52" src="https://github.com/user-attachments/assets/3cccc822-38bf-4c3b-a548-0c7998e97dfb" />
<img width="1046" height="593" alt="스크린샷 2025-08-23 오후 10 50 34" src="https://github.com/user-attachments/assets/562d2d58-f1bb-4f42-9491-36a9b1141c19" />
