# Pureum-FE
푸름팀 안드로이드(프론트) 레포지토리 입니다. 


규약 만들기

아이콘 이름 규칙

ic_기능_모양
화살표 같은 경우는 ic_arrow_방향 ex) ic_back, ic_like
선택 여부가 있는 아이콘은 ic_기능_모양과 ic_기능_모양_selected 로 구분
색은 안넣어도 될 거 같습니다 왜냐 코드에서 tint로 변경 가능 각자 변경해서 쓰기 ....

layout 파일명 규칙

activity/fragment_기능 ex) activity_login, fragment_home

layout id 규칙

xml이름_세부기능_view이름 ex) home_story_rv, shop_brandid_tv
세부 기능에는 _ 더 사용해도 괜찮아요

더미데이터 이름 규칙

recyclerview 이름_숫자 ex)post_1, post_2

commit 규칙

type: [파일명] 수정한 내용
type
add : 새로운 기능을 추가할 때
modify : 기존 기능을 수정할 때
test : 테스트 코드를 올릴 때
ex) add: [LoginActivity] 로그인 레이아웃 추가
add: [MainActivity] 바텀 네비게이션 구현
modify : [LoginActivity] 로그인 레이아웃 수정

코드 주석 규칙 (kt 파일에 사용)

코드 위에 어떤 기능인지 설명 ex) 파이어베이스 연결, 좋아요 기능
공통적으로 사용하는 변수를 제외한 애들은 선언 옆에 // 이 주석을 사용해서 설명해주기
화면 전환 시 어느 화면에서 어느 화면으로 넘어가는지 설명
자세하게 써주기
