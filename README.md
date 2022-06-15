# 프로젝트명
![image](https://user-images.githubusercontent.com/101301507/173730399-66881274-6fc2-4062-807b-2fe65164d7a2.png)
App을 활용한 원격제어 스마트 애견호텔 ‘Pettelier’
# 제안 배경
![image](https://user-images.githubusercontent.com/101301507/173731230-7f5832f1-7695-474f-9bd0-674e8c8a254e.png)
![image](https://user-images.githubusercontent.com/101301507/173731287-8e7760e8-c1bc-438d-a95e-4a67ba684390.png)
![image](https://user-images.githubusercontent.com/101301507/173731302-c2aac359-174a-4bfa-99ff-2ffa3aca91aa.png)

# 개발 목표
![image](https://user-images.githubusercontent.com/101301507/173731324-9272725e-c5ff-4a27-9204-6bee88a1a2d9.png)
- 반려견을 안심하고 맡길 수 있는 안전하고 신뢰성 있는 애견 호텔
- 애견 위탁 업체 관리자에게 용이한 관리 방안을 제공
# 개발 내용
- 실시간으로 서버와 통신하는 스마트 애견호텔
- 각종 센싱 데이터 값을 통한 호텔의 환경 변화를 실시간으로 감지
- Actuator: 환풍기, 먹이배급기
- 배변상황 감지 시, 자동으로 환풍기 운용
- 사용자간 커뮤니티 제공
# 어플리케이션 기능
- 가스센서를 활용한 반려동물의 배변상황 Push 알림 기능
- 카메라를 통한 실시간 모니터링 
- 커뮤니티 기능을 활용한 반려동물에 대한 정보 나눔
- 고객센터를 통한 문의 
- 원격제어를 통한 환풍기 및 먹이 배급

# 기존 애견 호텔과의 차별점
- 현재 애견호텔의 케이지는 온도, 조명, 환풍기를 관리자가 직접 조작 해야함
- 위탁하는 견주는 실시간 모니터링 불가능
![image](https://user-images.githubusercontent.com/101301507/173731340-b124487c-2e71-4123-8168-7d7a7a5bd353.png)

# 유스케이스 / ERD
![image](https://user-images.githubusercontent.com/101301507/173731354-459e8027-696f-4312-ae52-095705730bd0.png)
# IoT 부품
![image](https://user-images.githubusercontent.com/101301507/173731376-6233fbaf-3182-44be-aa6e-2595da85ddac.png)

# 회로도
![image](https://user-images.githubusercontent.com/101301507/173731684-d337c37c-84b1-4e2c-aa91-5d0a3143d6ef.png)
# 어플리케이션 화면 
## 일반 유저 화면
![image](https://user-images.githubusercontent.com/101301507/173731796-97e16ef2-7d1b-4c8c-bea4-38835b5081c7.png)
## 관리자 화면
![image](https://user-images.githubusercontent.com/101301507/173731895-1863d3ef-59ab-4251-abfc-dcf0f58e02f1.png)
![image](https://user-images.githubusercontent.com/101301507/173731561-aa8a0faa-ed44-42ce-8e83-7aee7b11a870.png)
![image](https://user-images.githubusercontent.com/101301507/173731584-986cdd4d-42ca-48fb-adac-6c1d67d560a5.png)

# 개발 도구
![image](https://user-images.githubusercontent.com/101301507/173731911-7ba35029-69a9-40b3-80f8-f80f93bbe1ff.png)

# 기대효과
- 반려견 위탁 관리자가 원격으로 케이지 관리가 가능 -> 인력의 효율적 활용과 함께 인건비 지출 또한 줄일 수 있을 것으로 기대
- 원격제어를 통한 관리로 돌봄에 대한 안정성이 높아짐 -> 서비스에 대한 질이 높아질 것
- 관리자의 수익 창출 및 서비스 질이 높아지며 고객들에게도 기존보다 저렴한 가격을 제공 가능 
- 고객들의 실시간 모니터링 지원으로 관리자와 사용자 간의 신뢰감 형성, 기존에 애견호텔의 문제점으로 지적되었던 ‘신뢰성’의 문제를 해결할 수 있을 것으로 기대된다.
# 발전 방향
- 펫케어 시장의 유망한 성장 가능성을 보여줌
- 다양한 분야와 접목 가능성 :  배변상태 분석 후 강아지의 건강상태 정보를 제공해 헬스케어와 접목하여 향후 동물의료 분야에 적용을 기대함
# 팀원 역할
|이름 | 역할 |
|---|---|
|박진수|프로젝트 기획 / 총괄, 온습도, 가스 센싱 데이터 서버 전송,환풍기 제어, 먹이 배급기 제어 기능 구현|
|김시윤|안드로이드/스프링 서브, 아이디/비밀번호 찾기 기능,회원가입, 반려견 정보 수정 페이지UI/UX|
|김준범|DB 설계/구축,안드로이드 푸쉬 메시지,프레그먼트,반려견 정보,케이지 매칭 등록 기능 구현|
|윤진관|안드로이드 UI/UX 설계 / 구현, 메인 홈 구현,협업툴 관리,산출문서 작성|
|최고은|스프링 서버 구축 / 관리,안드로이드 Volley 라이브러리 연동,로그인, 회원가입, 커뮤니티 기능 구현|

