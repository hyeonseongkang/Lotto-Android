package com.mirror.lotto_android.classes;

public class TempLotto {
    private int backgroundResource;
    private String number;

    public TempLotto(int backgroundResource, String number) {
        this.backgroundResource = backgroundResource;
        this.number = number;
    }

    public int getBackgroundResource() {
        return backgroundResource;
    }

    public String getNumber() {
        return number;
    }
}
