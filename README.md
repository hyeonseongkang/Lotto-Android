# Lotto-Android

## 프로젝트 소개
로또 번호를 생성하고 내가 생성한 로또 번호와 실제 당첨번호를 비교하여 결과를 확인해 주는 앱

## 프로젝트 기간
(2022.08.29 ~ 2022.09.02)

## 기능 소개
| 메인 화면 | 번호 생성 | 생성 번호 리스트 |
|:-:|:-:|:-:|
|<img src="https://user-images.githubusercontent.com/68272971/188267890-6d82f110-cefb-4401-b512-38936a6f6c16.png" height=600px>|<img src="https://user-images.githubusercontent.com/68272971/188267892-50c3b49f-21ae-4146-ba68-6dc2e01e17bf.png" height=600px>|<img src="https://user-images.githubusercontent.com/68272971/188267893-27f51d9c-1446-4765-b2b1-f0267a6c65a0.png" height=600px>|

| 당첨 확인 | 
|:-:|
|<img src="https://user-images.githubusercontent.com/68272971/188267895-c6f77ac0-4e5b-4626-8065-8f20d6679691.png" height=600px>

## 기술 스택

#### Android Studio(Java)
#### Room Database


## 디자인 패턴

#### MVVM Pattern

## 사용 라이브러리
#### design pattern
- androidx.lifecycle:lifecycle-viewmodel:$2.5.0
- androidx.lifecycle:lifecycle-livedata:$2.5.0

#### 내부 database
- androidx.room:room-runtime:2.2.5
- androidx.room:room-compiler:2.2.5

#### retrofit -> http 통신
- com.squareup.retrofit2:retrofit:2.9.0
- com.squareup.retrofit2:converter-gson:2.9.0

#### qr코드
- com.journeyapps:zxing-android-embedded:4.1.0
- com.google.zxing:core:3.4.1


## 새롭게 알게 된 점
- [Retrofit](https://velog.io/@hyeonseongkang/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-Retrofit)
- Room database
