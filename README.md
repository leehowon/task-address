# 주소 확인 프로그램
사용자가 입력한 도로명 주소가 실제 주소인지 판단하여 도로명을 확인합니다.

## 예시화면
```
_______________________________________________________________

          도 로 명 주 소 확 인 프 로 그 램 (v1.0.0)

_______________________________________________________________
[19:05:40.643] 고객의 도로명 주소를 입력하세요. ( 종료하시려면 'Q' 를 입력하세요. )
> 서울특별시 서초구 신반포로 270
[19:05:55.523] 주소: 서울특별시 서초구 신반포로
[19:05:55.523] 고객의 도로명 주소를 입력하세요. ( 종료하시려면 'Q' 를 입력하세요. )
> q
프로그램을 종료합니다...
```
## 실행방법
### Java 17 사용
`address-1.0.0.jar` 파일이 위치한 디렉토리에서 아래 명령어를 실행합니다.
```
java -jar address-1.0.0.jar
```
### mvnw 사용
```
git clone https://github.com/leehowon/task-address.git
cd task-address
./mvnw spring-boot:run
```