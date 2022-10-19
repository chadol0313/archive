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
<summary>npm run dev 실행 오류</summary>
<div markdown="1">

- Webpack-dev-server 버전을 3.0.0으로 다운그레이드로 해결
- `$ npm install —save-dev webpack-dev-server@3.0.0`

</div>
</details>

<details>
<summary>vue-devtools 크롬익스텐션 인식 오류 문제</summary>
<div markdown="1">
  
  - main.js 파일에 `Vue.config.devtools = true` 추가로 해결
  - [https://github.com/vuejs/vue-devtools/issues/190](https://github.com/vuejs/vue-devtools/issues/190)
  
</div>
</details>

<details>
<summary>ElementUI input 박스에서 `v-on:keyup.enter="메소드명"`이 정상 작동 안하는 문제</summary>
<div markdown="1">
  
  - `v-on:keyup.enter.native=""` 와 같이 .native 추가로 해결
  
</div>
</details>

<details>
<summary> Post 목록 출력시에 Member 객체 출력 에러 </summary>
<div markdown="1">
  
  - 에러 메세지(500에러)
    - No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationConfig.SerializationFeature.FAIL_ON_EMPTY_BEANS)
  - 해결
    - Post 엔티티에 @ManyToOne 연관관계 매핑을 LAZY 옵션에서 기본(EAGER)옵션으로 수정
  
</div>
</details>
    
<details>
<summary> 프로젝트를 git init으로 생성 후 발생하는 npm run dev/build 오류 문제 </summary>
<div markdown="1">
  
  ```jsx
    $ npm run dev
    npm ERR! path C:\Users\integer\IdeaProjects\pilot\package.json
    npm ERR! code ENOENT
    npm ERR! errno -4058
    npm ERR! syscall open
    npm ERR! enoent ENOENT: no such file or directory, open 'C:\Users\integer\IdeaProjects\pilot\package.json'
    npm ERR! enoent This is related to npm not being able to find a file.
    npm ERR! enoent

    npm ERR! A complete log of this run can be found in:
    npm ERR!     C:\Users\integer\AppData\Roaming\npm-cache\_logs\2019-02-25T01_23_19_131Z-debug.log
  ```
  
  - 단순히 npm run dev/build 명령을 입력한 경로가 문제였다.
   
</div>
</details>    

<details>
<summary> 태그 선택후 등록하기 누를 때 `object references an unsaved transient instance - save the transient instance before flushing` 오류</summary>
<div markdown="1">
  
  - Post 엔티티의 @ManyToMany에 영속성 전이(cascade=CascadeType.ALL) 추가
    - JPA에서 Entity를 저장할 때 연관된 모든 Entity는 영속상태여야 한다.
    - CascadeType.PERSIST 옵션으로 부모와 자식 Enitity를 한 번에 영속화할 수 있다.
    - 참고
        - [https://stackoverflow.com/questions/2302802/object-references-an-unsaved-transient-instance-save-the-transient-instance-be/10680218](https://stackoverflow.com/questions/2302802/object-references-an-unsaved-transient-instance-save-the-transient-instance-be/10680218)
   
</div>
</details>    

<details>
<summary> JSON: Infinite recursion (StackOverflowError)</summary>
<div markdown="1">
  
  - @JsonIgnoreProperties 사용으로 해결
    - 참고
        - [http://springquay.blogspot.com/2016/01/new-approach-to-solve-json-recursive.html](http://springquay.blogspot.com/2016/01/new-approach-to-solve-json-recursive.html)
        - [https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue](https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue)
        
</div>
</details>  
    
<details>
<summary> H2 접속문제</summary>
<div markdown="1">
  
  - H2의 JDBC URL이 jdbc:h2:~/test 으로 되어있으면 jdbc:h2:mem:testdb 으로 변경해서 접속해야 한다.
        
</div>
</details> 
    
<details>
<summary> 컨텐츠수정 모달창에서 태그 셀렉트박스 드랍다운이 뒤쪽에 보이는 문제</summary>
<div markdown="1">
  
   - ElementUI의 Global Config에 옵션 추가하면 해결
     - main.js 파일에 `Vue.us(ElementUI, { zIndex: 9999 });` 옵션 추가(9999 이하면 안됌)
   - 참고
     - [https://element.eleme.io/#/en-US/component/quickstart#global-config](https://element.eleme.io/#/en-US/component/quickstart#global-config)
        
</div>
</details> 

<details>
<summary> HTTP delete Request시 개발자도구의 XHR(XMLHttpRequest )에서 delete요청이 2번씩 찍히는 이유</summary>
<div markdown="1">
  
  - When you try to send a XMLHttpRequest to a different domain than the page is hosted, you are violating the same-origin policy. However, this situation became somewhat common, many technics are introduced. CORS is one of them.

        In short, server that you are sending the DELETE request allows cross domain requests. In the process, there should be a **preflight** call and that is the **HTTP OPTION** call.

        So, you are having two responses for the **OPTION** and **DELETE** call.

        see [MDN page for CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS).

    - 출처 : [https://stackoverflow.com/questions/35808655/why-do-i-get-back-2-responses-of-200-and-204-when-using-an-ajax-call-to-delete-o](https://stackoverflow.com/questions/35808655/why-do-i-get-back-2-responses-of-200-and-204-when-using-an-ajax-call-to-delete-o)
        
</div>
</details> 

<details>
<summary> 이미지 파싱 시 og:image 경로가 달라서 제대로 파싱이 안되는 경우</summary>
<div markdown="1">
  
  - UserAgent 설정으로 해결
        - [https://www.javacodeexamples.com/jsoup-set-user-agent-example/760](https://www.javacodeexamples.com/jsoup-set-user-agent-example/760)
        - [http://www.useragentstring.com/](http://www.useragentstring.com/)
        
</div>
</details> 
    
<details>
<summary> 구글 로그인으로 로그인한 사용자의 정보를 가져오는 방법이 스프링 2.0대 버전에서 달라진 것</summary>
<div markdown="1">
  
  - 1.5대 버전에서는 Controller의 인자로 Principal을 넘기면 principal.getName(0에서 바로 꺼내서 쓸 수 있었는데, 2.0대 버전에서는 principal.getName()의 경우 principal 객체.toString()을 반환한다.
    - 1.5대 버전에서 principal을 사용하는 경우
    - 아래와 같이 사용했다면,

    ```jsx
    @RequestMapping("/sso/user")
    @SuppressWarnings("unchecked")
    public Map<String, String> user(Principal principal) {
        if (principal != null) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
            Authentication authentication = oAuth2Authentication.getUserAuthentication();
            Map<String, String> details = new LinkedHashMap<>();
            details = (Map<String, String>) authentication.getDetails();
            logger.info("details = " + details);  // id, email, name, link etc.
            Map<String, String> map = new LinkedHashMap<>();
            map.put("email", details.get("email"));
            return map;
        }
        return null;
    }
    ```

    - 2.0대 버전에서는
    - 아래와 같이 principal 객체의 내용을 꺼내 쓸 수 있다.

    ```jsx
    UsernamePasswordAuthenticationToken token =
                    (UsernamePasswordAuthenticationToken) SecurityContextHolder
                            .getContext().getAuthentication();
            Map<String, Object> map = (Map<String, Object>) token.getPrincipal();

            String email = String.valueOf(map.get("email"));
            post.setMember(memberRepository.findByEmail(email));
    ```
        
</div>
</details> 
    
<details>
<summary> 랭킹 동점자 처리 문제</summary>
<div markdown="1">
  
  - PageRequest의 Sort부분에서 properties를 "rankPoint"를 주고 "likeCnt"를 줘서 댓글수보다 좋아요수가 우선순위 갖도록 설정.
  - 좋아요 수도 똑같다면..........
        
</div>
</details> 
    
</br>

## 6. 회고 / 느낀점
>프로젝트 개발 회고 글: https://zuminternet.github.io/ZUM-Pilot-integer/
