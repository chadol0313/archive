# :pushpin: 모임(MOIM)
>스터디, 취미등의 모임 공유 게시판  
>[요약 PDF](https://drive.google.com/file/d/1whriLreibG0C8HqN9YESodWJNzL4TBEi/view?usp=sharing)

</br>

## 1. 제작 기간 & 참여 인원
- 2022.9.23 ~ 2022.10.15
- 개인 프로젝트

</br>

## 2. 사용 기술
#### `Back-end`
  - Java 8
  - Spring Boot 2.7.4
  - Gradle
  - Mybatis
  - Spring tool suite 3.9.17
  - Apache Tomcat 9
  - MySQL 5.7
  - Oracle 11g
#### `Front-end`
  - HTML5 & CSS3
  - JavaScript

</br>

## 3. 기능설명
해당 게시판의 주요 기능은 검색 기능입니다.  
사용자는 자신이 찾고자하는 지역, 주제 카테고리를 설정하고  
검색어를 입력하여 검색할 수 있습니다  


<details>
<summary><b>주요 기능 설명 펼치기</b></summary>
<div markdown="1">

### 3.1. 전체 흐름
![](https://user-images.githubusercontent.com/115128823/196615661-92f8c33a-8876-47f5-8125-3f6b665e73c6.png)

### 3.2. Controller

- **요청 처리** :pushpin: [코드 확인](https://github.com/chadol0313/archive/blob/ed3ad4bf88edfc0d688e7191011dd5146e17ac91/Moim_Project/src/main/java/com/study/moim/controller/BoardController.java#L190)
  - Controller에서는 요청을 화면단에서 넘어온 요청을 받고, Service 계층에 로직 처리를 위임합니다.
  - View로부터 전달받은 지역,주제,검색키워드,페이지를 매개변수로 하여 Service에서 실행한 메서드 값을 받아옵니다.
  - 받은 결과값은 HashMap에 저장하도록 했습니다.

- **결과 처리** :pushpin: [코드 확인](https://github.com/chadol0313/archive/blob/ed3ad4bf88edfc0d688e7191011dd5146e17ac91/Moim_Project/src/main/java/com/study/moim/controller/BoardController.java#L212)
  - Service 계층에서 넘어온 로직 처리 결과(메세지)를 화면단에 응답해줍니다.
  - Service 계층에서 넘어온 로직 처리 결과를 model에 넣어줍니다.
  - 지역,주제,검색키워드는 검색결과 페이지에서 검색값을 유지하기위해 각각 따로 model에 넣어주었습니다.

### 3.3. Service

- **페이징 및 검색 결과값 처리** :pushpin: [코드 확인](https://github.com/chadol0313/archive/blob/ed3ad4bf88edfc0d688e7191011dd5146e17ac91/Moim_Project/src/main/java/com/study/moim/service/BoardService.java#L96)
  - 최종 결과를 담을 해쉬맵 객체를 생성합니다.
  - 페이징 객체를 생성하고 검색에 걸린 총 게시물 갯수를 카운트합니다
  - 페이지 시작번호, 끝번호, 지역, 주제, 검색키워드를 매개변수로 dao에서 넘어온 로직 결과를 List에 담습니다.
  - 페이징과 위의 결과값을 담은 List를 해쉬맵에 담았습니다

### 3.4. Mapper

![](https://user-images.githubusercontent.com/115128823/196623593-79182ce3-fdae-4e4e-bfa2-760e10c0def0.png)

- **검색 키워드 쿼리문** :pushpin: [코드 확인](https://github.com/chadol0313/archive/blob/ed3ad4bf88edfc0d688e7191011dd5146e17ac91/Moim_Project/src/main/resources/mybatis/mapper/Board.xml#L68)
  - 매개변수 지역과 주제가 일치하며 타이틀에 검색 키워드가 속한 게시글을 검색합니다.
 
### 3.5. View

![](https://user-images.githubusercontent.com/115128823/196624209-7c08f101-44a7-4381-93a1-4f837df471e0.png)

- **검색 값 유지** :pushpin: [코드 확인](https://github.com/chadol0313/archive/blob/ed3ad4bf88edfc0d688e7191011dd5146e17ac91/Moim_Project/src/main/webapp/WEB-INF/views/board/searchPage.jsp#L19)
  - JSTL문법을 이용하여 검색 결과페이지에서도 검색설정 값을 그대로 유지했습니다.
</div>
</details>

</br>

## 5. 트러블 슈팅
<details>
<summary> 마이페이지 클릭시 url에 id값이 1F8F50F044D3A1FDC5480A046435CC4D 식으로 뜨는 경우 </summary>
<div markdown="1">
 
  - 마이페이지 메뉴는 헤더에 있으므로 헤더 문제라고 생각
  - 헤더 작업시 아이디 출력 로그를 찍어보았으나 이상 無
  - 다시 생각해보니 로그인 페이지에서 main으로 attribute를 보내는 것이지 헤더로 보내는 것은 아님
  - 컨트롤러에서 헤더에 사용자 정보를 보내는가? X
  - 헤더 마이페이지 파라미터에 id값을 지우고 컨트롤러에서 사용자 값을 MemberDto에 담음
  - 그리고 getMember 메서드에 들어갔던 id값은 본래 getParameter로 얻었으나,
  - MemberDto에서 꺼내쓰는 것으로 수정
   
</div>
</details>  

<details>
<summary>java.sql.SQLException: ORA-28040</summary>
<div markdown="1">

- ojdbc6.jar 파일을 ojdbc8.jar로 변경

</div>
</details>

<details>
<summary>Dao 인식문제</summary>
<div markdown="1">
  
  - @Mapper 어노테이션 누락으로 추가하여 해결
  
</div>
</details>

<details>
<summary>게시글작성(Board 테이블에 DB추가)</summary>
<div markdown="1">
  
  - 각각의 파라미터를 @RequestParam 으로 받았었으나 오류
  - Dto 객체를 생성하고 아래와같이 request.getParameter로 수정
  </div>
</details>

<details>
<summary> 게시물 상세페이지 출력 오류 </summary>
<div markdown="1">
  
  - @RequestParam 으로 받은 게시글번호를 int 타입에서 String으로 변경
  
</div>
</details>
    
<details>
<summary>댓글 추가시 ORA-00984: 열을 사용할 수 없습니다 오류 </summary>
<div markdown="1">
  
  - 시퀀스명 오입력
   
</div>
</details>    

<details>
<summary> 댓글추가 + 댓글리스트 출력</summary>
<div markdown="1">
  
  - forEach문에 넣은 item이 리스트형이 아니어서 발생하는 오류
        
</div>
</details>  
    
<details>
<summary> 마이페이지 및 게시판 클릭시 1페이지로 가지 않음</summary>
<div markdown="1">
  
  - 이전 페이지에서 주소에 page값을 1로 고정
        
</div>
</details> 
    
<details>
<summary> 마이페이지내 2개의 페이징이 동시에 움직임</summary>
<div markdown="1">
  
   - Service단에서 page 변수를 2개로 나누었음
        
</div>
</details> 


</br>
