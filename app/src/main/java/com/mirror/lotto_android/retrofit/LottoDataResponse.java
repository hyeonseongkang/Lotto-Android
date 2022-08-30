package com.mirror.lotto_android.retrofit;

import com.google.gson.annotations.SerializedName;

public class LottoDataResponse {
    /*
    @SerializedName("key name")
    위 어노테이션의 역할은 Gson이 JSON 객체의 키들을 Java 클래스 필드(변수)에 매핑하는 것을 도와준다.(클래스 필드와 JSON 객체의 키들의 이름이 동일하면 동작하는 데는 문제 없음 but 권장)
    @SerializedName("key name")을 사용하면 JSON 객체의 키와 Java 클래스 필드 변수명이 달라도 매핑 된다. 예를 들어
    @SerializedName("totSellamnt")
    String totalSellamnt;
    해당 코드는 JSON 객체의 totSellamnt key를 자바 클래스 필드 totalSellamnt에 매핑시켜 사용할 수 있다.
     */
    @SerializedName("totSellamnt")
    String totSellamnt;

    @SerializedName("returnValue")
    String returnValue;

    @SerializedName("drwNoDate")
    String drwNoDate;

    @SerializedName("firstWinamnt")
    String firstWinamnt;

    @SerializedName("drwtNo6")
    String drwtNo6;

    @SerializedName("drwtNo4")
    String drwtNo4;

    @SerializedName("firstPrzwnerCo")
    String firstPrzwnerCo;

    @SerializedName("drwtNo5")
    String drwtNo5;

    @SerializedName("bnusNo")
    String bnusNo;

    @SerializedName("firstAccumamnt")
    String firstAccumamnt;

    @SerializedName("drwNo")
    String drwNo;

    @SerializedName("drwtNo2")
    String drwtNo2;

    @SerializedName("drwtNo3")
    String drwtNo3;

    @SerializedName("drwtNo1")
    String drwtNo1;

    public String getTotSellamnt() {
        return totSellamnt;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public String getDrwNoDate() {
        return drwNoDate;
    }

    public String getFirstWinamnt() {
        return firstWinamnt;
    }

    public String getDrwtNo6() {
        return drwtNo6;
    }

    public String getDrwtNo4() {
        return drwtNo4;
    }

    public String getFirstPrzwnerCo() {
        return firstPrzwnerCo;
    }

    public String getDrwtNo5() {
        return drwtNo5;
    }

    public String getBnusNo() {
        return bnusNo;
    }

    public String getFirstAccumamnt() {
        return firstAccumamnt;
    }

    public String getDrwNo() {
        return drwNo;
    }

    public String getDrwtNo2() {
        return drwtNo2;
    }

    public String getDrwtNo3() {
        return drwtNo3;
    }

    public String getDrwtNo1() {
        return drwtNo1;
    }
}
