package com.mirror.lotto_android.model;

import android.app.Application;
import android.os.Build;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mirror.lotto_android.R;
import com.mirror.lotto_android.classes.Lotto;
import com.mirror.lotto_android.classes.TempLotto;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LottoRepository {

    public static final String TAG = "LottoRepository";

    Application application;
    private MutableLiveData<Lotto> lottoData;
    private MutableLiveData<TempLotto>[] createMyNumber;

    JsonObject object;

    int weeklyTurn;

    int lottoNum[] = new int[6];

    public LottoRepository(Application application) {
        this.application = application;
        lottoData = new MutableLiveData<>();
        createMyNumber = new MutableLiveData[6];
        for (int i = 0; i < 6; i++) {
            createMyNumber[i] = new MutableLiveData<>();
        }

    }

    public LiveData<Lotto> getLottoData() {return lottoData;}

    public LiveData<TempLotto>[] getCreateMyNumber() { return createMyNumber; }

    // 이번주 로또 데이터 가져오기
    public void getWeeklyLottoData() {
        weeklyTurn = getNextEpisodeBasedonDate();
        requestLottoData(weeklyTurn);
    }

    // 사용자가 요청한 날짜의 로또 데이터 가져오기
    public void setLottoData(int num) {
       requestLottoData(num);
    }

    public void requestLottoData(int num) {
        String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + num;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                object = (JsonObject) JsonParser.parseString(response);
                String returnValue = object.get("returnValue").toString();
                if (returnValue.equals("\"success\"")) {
                    String temp_totSellamnt = String.valueOf(object.get("totSellamnt"));        //총판매액
                    String drwNoDate = String.valueOf(object.get("drwNoDate"));                 //추첨날짜
                    String temp_firstWinamnt = String.valueOf(object.get("firstWinamnt"));        //1등 1명당 당첨 금액
                    String firstPrzwnerCo = String.valueOf(object.get("firstPrzwnerCo"));         //1등 당첨자 수
                    String temp_firstAccumamnt = String.valueOf(object.get("firstAccumamnt"));    //1등 당첨 총 금액
                    String drwNo = String.valueOf(object.get("drwNo"));
                    String drwtNo1 = String.valueOf(object.get("drwtNo1"));
                    String drwtNo2 = String.valueOf(object.get("drwtNo2"));
                    String drwtNo3 = String.valueOf(object.get("drwtNo3"));
                    String drwtNo4 = String.valueOf(object.get("drwtNo4"));
                    String drwtNo5 = String.valueOf(object.get("drwtNo5"));
                    String drwtNo6 = String.valueOf(object.get("drwtNo6"));
                    String bnusNo = String.valueOf(object.get("bnusNo"));

                    long b = Long.parseLong(temp_firstAccumamnt);
                    long d = Long.parseLong(temp_firstWinamnt);
                    long e = Long.parseLong(temp_totSellamnt);
                    DecimalFormat dc = new DecimalFormat("###,###");
                    String firstAccumamnt = dc.format(b)+"원";
                    String firstWinamnt = dc.format(d)+"원";
                    String totSellamnt = dc.format(e)+"원";

                    int drwtNo1_background = ballBackground(Integer.parseInt(drwtNo1));
                    int drwtNo2_background = ballBackground(Integer.parseInt(drwtNo2));
                    int drwtNo3_background = ballBackground(Integer.parseInt(drwtNo3));
                    int drwtNo4_background = ballBackground(Integer.parseInt(drwtNo4));
                    int drwtNo5_background = ballBackground(Integer.parseInt(drwtNo5));
                    int drwtNo6_background = ballBackground(Integer.parseInt(drwtNo6));
                    int bnusNo_background = ballBackground(Integer.parseInt(bnusNo));

                    Lotto lotto = new Lotto(drwNo, totSellamnt, drwNoDate, firstWinamnt, firstPrzwnerCo, firstAccumamnt,
                            drwtNo1, drwtNo2, drwtNo3, drwtNo4, drwtNo5, drwtNo6, bnusNo,
                            drwtNo1_background, drwtNo2_background, drwtNo3_background, drwtNo4_background, drwtNo5_background, drwtNo6_background, bnusNo_background);

                    lottoData.setValue(lotto);
                } else {
                    weeklyTurn -= 1;
                    requestLottoData(weeklyTurn);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                return params;
            }
        };
        request.setShouldCache(false);
        RequestQueue requestQueue = Volley.newRequestQueue(application.getApplicationContext());
        requestQueue.add(request);
    }

    public int getNextEpisodeBasedonDate() {
        String startDate = "2002-12-07 23:59:59";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date cDate = new Date();
        Date sDate = null;
        try {
            sDate = dateFormat.parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = cDate.getTime() - sDate.getTime();

        long nextEpi = (diff / (86400 * 1000 * 7))+2;
        return (int) nextEpi;
    }

    public int ballBackground(int ballNum){
        int resource = 0;
        if(ballNum == 1 || ballNum<11){
            resource = R.drawable.first_ball;
        }else if(ballNum == 11 || ballNum<21){
            resource =R.drawable.second_ball;
        }else if(ballNum == 21 || ballNum<31){
            resource = R.drawable.third_ball;
        }else if(ballNum == 31 || ballNum<41){
            resource = R.drawable.fourth_ball;
        }else{
            resource = R.drawable.fifth_ball;
        }
        return resource;
    }

    public void addLottoNumber(int num) {
        if (lottoNum[5] != 0) {
            Toast.makeText(application, "더 이상 번호를 입력할 수 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i : lottoNum) {
            if (i == num) {
                Toast.makeText(application, "중복되는 번호 입니다.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        int temp;
        OUT:
        for (int i = 0; i < lottoNum.length; i++) {
            if (lottoNum[i] == 0) {
                lottoNum[i] = num;
                switch (i) {
                    case 0:
                        temp = ballBackground(lottoNum[i]);
                        createMyNumber[0].setValue(new TempLotto(temp, String.valueOf(lottoNum[i])));
                        break OUT;

                    case 1:
                        temp = ballBackground(lottoNum[i]);
                        createMyNumber[1].setValue(new TempLotto(temp, String.valueOf(lottoNum[i])));
                        break OUT;

                    case 2:
                        temp = ballBackground(lottoNum[i]);
                        createMyNumber[2].setValue(new TempLotto(temp, String.valueOf(lottoNum[i])));
                        break OUT;

                    case 3:
                        temp = ballBackground(lottoNum[i]);
                        createMyNumber[3].setValue(new TempLotto(temp, String.valueOf(lottoNum[i])));
                        break OUT;

                    case 4:
                        temp = ballBackground(lottoNum[i]);
                        createMyNumber[4].setValue(new TempLotto(temp, String.valueOf(lottoNum[i])));
                        break OUT;

                    case 5:
                        temp = ballBackground(lottoNum[i]);
                        createMyNumber[5].setValue(new TempLotto(temp, String.valueOf(lottoNum[i])));
                        break OUT;
                }
            }
        }

    }

}


