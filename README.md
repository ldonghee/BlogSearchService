# 블로그 검색 서비스

## 구현 내용
### 1. 블로그 검색 기능 구현
  - 카카오 API 정상 응답 못받을 경우, 네이버 API 호출하여 결과 제공 
  - 추가 확장성을 위해 SearchEngineManager 클래스에서 검색 대상 API 관리하도록 구현
  - 검색 대상 API 별 엔진(연결정보 관리), 파라미터, 결과 추가 필요
  - 트래픽이 많은 상황을 대비해 블로그 검색 API는 Embedded Redis를 활용하여 로컬 캐시 적용하여 요청을 최소화

 
#### Request
```bash
curl -X GET http://localhost:8080/search/blog?query=테스트&page=1&size=3&sort=accuracy
```

#### Response
```json
{
  "statusCode": 200,
  "statusMessage": "OK",
  "result": [
    {
      "title": "NestJS — <b>Test</b> Driven Development (1)",
      "url": "http://dev-whoan.xyz/102",
      "contents": "이전에 쓰던 To Do List를 폐기하고, NestJS MVC 환경에서 TDD를 수행하는 법을 작성하려 한다. 크게 Unit <b>Test</b>와 Integration <b>Test</b>로 나누어서 연재할 예정이다. 간략한 MVC 흔히 서비스의 프론트엔드에서 발생하는 요청을 처리하기 위해 우리는 백엔드의 시스템을 MVC 디자인 패턴을 이용해 설계하곤 한다. MVC 패턴을...",
      "blogName": "짧은머리 개발자",
      "datetime": "2023-03-10T21:59:55.000+09:00"
    },
    {
      "title": "만주사화 백업 임베드 <b>TEST</b>",
      "url": "http://dururungbox.tistory.com/pages/만주사화-백업-임베드-TEST",
      "contents": "<b>test</b> 드르렁:65 행운 go. 나 약간 범죄자 인상인데 ㅋ 운 기준치: 65/32/13 굴림: 19 판정결과: 어려운 성공 히읗:93 ? dksl 아니 잘못굴렷다 넘 오랜마에 왓셔 히읗:히히!!! 45 드르렁:킄킄 히읗:사담사담 호오 호오오~ 좋군뇨 호오옹 드르렁 (GM):굿 사실 이걸 우리..별로 안 쓸것같지만 그래도 ㅋㅋ 히읗:그냥 오너캐...",
      "blogName": "저장고",
      "datetime": "2023-03-20T20:30:22.000+09:00"
    },
    {
      "title": "[Nest.js] Jest e2e <b>test</b> (app.e2e-spec.ts)",
      "url": "http://4sii.tistory.com/455",
      "contents": "개요 오늘도 헛소리 하는 다른 블로그들이 답답해서 내가 해결방법 찾아 정리해본다. 기본 세팅 app.e2e-spec.ts import { <b>Test</b>, TestingModule } from &#39;@nestjs/testing&#39;; import { INestApplication } from &#39;@nestjs/common&#39;; import * as request from &#39;supertest&#39;; import { AppModule } from &#39;./../src/app.module...",
      "blogName": "포시코딩",
      "datetime": "2023-03-06T15:52:14.000+09:00"
    }
  ]
}
```
<br/>

### 2. 인기 검색어 목록 제공
- h2 인메모리 데이터 베이스에 JPA 이용하여 기능 구현
- Redis와 Schedule을 이용한 동시성 제어 
  - Redis RedisTemplate 사용한 Hash Key/Value 적용
    - RedisTemplate increment 메서드를 사용해 Key 에 대한 Value 증가
    - Key : 검색어, Value : 검색한 횟수
  - Embedded Schedule 사용
    - 5분에 한번 Redis -> DB로 배치 수행
    
#### Request
```bash
curl -X GET http://localhost:8080/rank/keywordList
```

#### Response
```json
{
    "statusCode": 200,
    "statusMessage": "OK",
    "result": [
        {
            "id": 1,
            "query": "테스트",
            "count": 6
        },
        {
            "id": 2,
            "query": "test",
            "count": 3
        }
    ]
}
```


## jar 파일 다운로드 링크

- **경로: /jars/blog-search-0.0.1-SNAPSHOT.jar**
  https://github.com/ldonghee/BlogSearchService/tree/main/jars/blog-search-0.0.1-SNAPSHOT.jar
- PORT : 8080


  ```bash
  java -jar blog-search-0.0.1-SNAPSHOT
  ```