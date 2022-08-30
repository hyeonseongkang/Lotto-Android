package com.mirror.lotto_android.classes;

public class UserLotto {
    private String drwtNo1;
    private String drwtNo2;
    private String drwtNo3;
    private String drwtNo4;
    private String drwtNo5;
    private String drwtNo6;

    private int drwtNo1_background;
    private int drwtNo2_background;
    private int drwtNo3_background;
    private int drwtNo4_background;
    private int drwtNo5_background;
    private int drwtNo6_background;

    public UserLotto(String drwtNo1, String drwtNo2, String drwtNo3, String drwtNo4, String drwtNo5, String drwtNo6, int drwtNo1_background, int drwtNo2_background, int drwtNo3_background, int drwtNo4_background, int drwtNo5_background, int drwtNo6_background) {
        this.drwtNo1 = drwtNo1;
        this.drwtNo2 = drwtNo2;
        this.drwtNo3 = drwtNo3;
        this.drwtNo4 = drwtNo4;
        this.drwtNo5 = drwtNo5;
        this.drwtNo6 = drwtNo6;
        this.drwtNo1_background = drwtNo1_background;
        this.drwtNo2_background = drwtNo2_background;
        this.drwtNo3_background = drwtNo3_background;
        this.drwtNo4_background = drwtNo4_background;
        this.drwtNo5_background = drwtNo5_background;
        this.drwtNo6_background = drwtNo6_background;
    }

    public String getDrwtNo1() {
        return drwtNo1;
    }

    public String getDrwtNo2() {
        return drwtNo2;
    }

    public String getDrwtNo3() {
        return drwtNo3;
    }

    public String getDrwtNo4() {
        return drwtNo4;
    }

    public String getDrwtNo5() {
        return drwtNo5;
    }

    public String getDrwtNo6() {
        return drwtNo6;
    }

    public int getDrwtNo1_background() {
        return drwtNo1_background;
    }

    public int getDrwtNo2_background() {
        return drwtNo2_background;
    }

    public int getDrwtNo3_background() {
        return drwtNo3_background;
    }

    public int getDrwtNo4_background() {
        return drwtNo4_background;
    }

    public int getDrwtNo5_background() {
        return drwtNo5_background;
    }

    public int getDrwtNo6_background() {
        return drwtNo6_background;
    }
}
