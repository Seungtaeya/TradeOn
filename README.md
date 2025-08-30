# springBoot-TradeOn
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
  -db 다이어그램

  <img width="1281" height="742" alt="db_Diagram" src="https://github.com/user-attachments/assets/461ac30c-9299-4661-83b4-b52c50c7cd86" />

  
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
    - 상품 구매후 배송 받았을 시 리뷰 작성 가능
    - 사용자가 작성한 리뷰 정보를 비동기로 가져오도록 구현
<img width="1046" height="593" alt="스크린샷 2025-08-23 오후 10 51 52" src="https://github.com/user-attachments/assets/3cccc822-38bf-4c3b-a548-0c7998e97dfb" />
<img width="1046" height="593" alt="스크린샷 2025-08-23 오후 10 50 34" src="https://github.com/user-attachments/assets/562d2d58-f1bb-4f42-9491-36a9b1141c19" />

- 마이 페이지 -> qna
  - 사용자가 작성한 qna를 비동기로 가져오도록 구현
<img width="1046" height="593" alt="스크린샷 2025-08-23 오후 10 56 48" src="https://github.com/user-attachments/assets/88d0fdac-9738-420d-914c-8a5222e95269" />

- 상품 등록 페이지
  - 상품 상태는 일반 회원일 경우 중고만 올리게 프론트에서 설정 ( 기업일 경우 신상품 등록 가능 )
<img width="682" height="1226" alt="스크린샷 2025-08-23 오후 11 00 50" src="https://github.com/user-attachments/assets/3f23b889-156a-4b9e-a25e-1b2ef2221a15" />
<img width="684" height="969" alt="스크린샷 2025-08-23 오후 11 21 36" src="https://github.com/user-attachments/assets/c0f77be9-144d-42b3-a79c-3ff7db71ac2b" />

- 상품 페이지
  - 장바구니 담거나 찜 목록에 추가 기능
  - qna 등록 기능
<img width="1047" height="1507" alt="스크린샷 2025-08-23 오후 11 02 09" src="https://github.com/user-attachments/assets/2e69ba43-de97-4560-aa39-64a50b6e0854" />
<img width="1047" height="1611" alt="스크린샷 2025-08-23 오후 11 04 55" src="https://github.com/user-attachments/assets/c21b4bfa-5507-413a-9c6d-fde6689ef78e" />

- 상품 페이지 -> 장바구니
  - 장바구니에 담긴 상품 삭제 기능, 총 금액
<img width="1050" height="622" alt="스크린샷 2025-08-23 오후 11 06 27" src="https://github.com/user-attachments/assets/54f7394b-0529-429f-a129-33f30994b541" />

- 상품 페이지 -> 찜 목록
  - 비동기로 찜 목록 추가
<img width="507" height="492" alt="스크린샷 2025-08-23 오후 11 07 08" src="https://github.com/user-attachments/assets/76cca8e1-cb4c-43e4-900e-4e6b5341ec75" />

- 검색
  - 상품 이름 , 중고거래시 지역 이름 검색 기능
<img width="598" height="537" alt="스크린샷 2025-08-23 오후 11 12 55" src="https://github.com/user-attachments/assets/cca640fa-07ca-4b29-a985-bfa5a4002191" />
<img width="1161" height="540" alt="스크린샷 2025-08-23 오후 11 13 21" src="https://github.com/user-attachments/assets/92c81d8d-34be-4713-adb9-d16af9195caf" />

- 카테고리
  - 홈 화면서 카테고리 클릭시 최상위 카테고리면 최상위 포함 최상위 상속 받는 하위 카테고리 까지 출력 ( 페이징 처리 )
  - 하위 카테고리일 시 하위 카테고리만 출력  ( 페이징 처리 )
<img width="237" height="473" alt="스크린샷 2025-08-23 오후 11 19 55" src="https://github.com/user-attachments/assets/cd60c17c-645d-4952-ac97-63006a617db1" />

  - 1번 페이지

<img width="897" height="535" alt="스크린샷 2025-08-23 오후 11 17 37" src="https://github.com/user-attachments/assets/bb0debd9-41ce-4ccf-8e14-e6855d85f165" />

  - 2번 페이지

<img width="897" height="535" alt="스크린샷 2025-08-23 오후 11 17 42" src="https://github.com/user-attachments/assets/54279ce2-8051-4b0c-8680-eeccf772c668" />

  - 고양이 -> 랙돌 카테고리로 이동


<img width="897" height="535" alt="스크린샷 2025-08-23 오후 11 17 58" src="https://github.com/user-attachments/assets/92ed0d90-e6ad-4134-bf54-29009ccd5399" />

- 상품 판매자 주문 관리
  - 판매된 상품의 주문 상태 수정
<img width="967" height="1031" alt="스크린샷 2025-08-23 오후 11 23 03" src="https://github.com/user-attachments/assets/4b647614-5d7f-4d20-ab97-e5959f063c83" />

- 상품 문의 관리
  - 판매자가 올린 상품의 qna가 등록 됐을 경우 답변 가능
<img width="1414" height="347" alt="스크린샷 2025-08-23 오후 11 24 11" src="https://github.com/user-attachments/assets/bc91fc70-713e-4a36-a383-cf62244191cd" />
<img width="1414" height="347" alt="스크린샷 2025-08-23 오후 11 25 15" src="https://github.com/user-attachments/assets/3411d9dc-a6b2-432f-a7aa-b0e1f615f988" />
<img width="1020" height="258" alt="스크린샷 2025-08-23 오후 11 25 38" src="https://github.com/user-attachments/assets/14bb3c8c-e37f-44dc-9c76-7466436341bb" />

- 관리자 페이지
  - 회원, 공지사항, 배너, 카테고리 관리 페이지
<img width="1236" height="268" alt="스크린샷 2025-08-30 오후 8 58 39" src="https://github.com/user-attachments/assets/d786ee0a-a711-4a84-afb5-56e9eb500a15" />
  
- 회원 관리 페이지
  - 회원 상세정보, 회원 추방 기능, 회원 직위 변경
  
<img width="1421" height="450" alt="스크린샷 2025-08-30 오후 9 00 33" src="https://github.com/user-attachments/assets/0bb1d208-9e91-489a-a721-495722cd14ba" />
<img width="1432" height="476" alt="스크린샷 2025-08-30 오후 9 00 43" src="https://github.com/user-attachments/assets/2e4a15ee-7c33-4981-9d47-740e0b3402e0" />

- 공지사항
  - 작성, 삭제 기능

<img width="1440" height="475" alt="스크린샷 2025-08-30 오후 9 07 45" src="https://github.com/user-attachments/assets/15b856b1-be0f-4e02-af78-ba8c8600efe2" />
<img width="1440" height="705" alt="스크린샷 2025-08-30 오후 9 07 51" src="https://github.com/user-attachments/assets/eeb03fe0-879d-4f13-b023-29187bb3b724" />

- 배너 등록 페이지
  - 배너 등록 삭제 기능

<img width="1440" height="705" alt="스크린샷 2025-08-30 오후 9 09 52" src="https://github.com/user-attachments/assets/28c94bff-094a-418b-909d-56b1af1df3f5" />

- 카테고리 등록 페이지
  - 카테고리 등록, 수정, 삭제 기능

<img width="1440" height="880" alt="스크린샷 2025-08-30 오후 9 10 28" src="https://github.com/user-attachments/assets/f41e2a04-d825-4435-97d4-94560a839e21" />
