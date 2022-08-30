package com.mirror.lotto_android.model;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.google.gson.JsonObject;
import com.mirror.lotto_android.R;
import com.mirror.lotto_android.classes.Lotto;
import com.mirror.lotto_android.classes.TempLotto;
import com.mirror.lotto_android.classes.UserLotto;
import com.mirror.lotto_android.database.LottoDao;
import com.mirror.lotto_android.database.LottoDatabase;
import com.mirror.lotto_android.retrofit.LottoClient;
import com.mirror.lotto_android.retrofit.LottoDataResponse;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class LottoRepository {

    public static final String TAG = "LottoRepository";

    private LottoDao lottoDao;
    private LiveData<List<UserLotto>> allLottos;

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

        LottoDatabase database = LottoDatabase.getInstance(application);
        lottoDao = database.lottoDao();
        allLottos = lottoDao.getAllLottos();

    }

    public void insert() { new InsertLottoAsyncTask(lottoDao).execute(); }

    public void delete(UserLotto userLotto) { new DeleteLottoAsyncTask(lottoDao).execute(userLotto);}

    public void deleteAllLottos() { new DeleteAllLottosAsyncTask(lottoDao).execute();}

    public LiveData<List<UserLotto>> getAllLottos() { return allLottos;}

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
        Call<LottoDataResponse> retrofitCall = LottoClient.getAPIService().getLotto(String.valueOf(num));
        retrofitCall.enqueue(new Callback<LottoDataResponse>() {
            @Override
            public void onResponse(Call<LottoDataResponse> call, retrofit2.Response<LottoDataResponse> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, String.valueOf(response.code()));
                    return;
                }

                LottoDataResponse lottoDataResponse = response.body();

                String temp_totSellamnt = lottoDataResponse.getTotSellamnt();
                String returnValue = lottoDataResponse.getReturnValue();
                String drwNoDate = lottoDataResponse.getDrwNoDate();
                String temp_firstWinamnt =lottoDataResponse.getFirstWinamnt();
                String drwtNo6 = lottoDataResponse.getDrwtNo6();
                String drwtNo4 = lottoDataResponse.getDrwtNo4();
                String firstPrzwnerCo= lottoDataResponse.getFirstPrzwnerCo();
                String drwtNo5 = lottoDataResponse.getDrwtNo5();
                String bnusNo = lottoDataResponse.getBnusNo();
                String temp_firstAccumamnt = lottoDataResponse.getFirstAccumamnt();
                String drwNo = lottoDataResponse.getDrwNo();
                String drwtNo2 = lottoDataResponse.getDrwtNo2();
                String drwtNo3 = lottoDataResponse.getDrwtNo3();
                String drwtNo1 = lottoDataResponse.getDrwtNo1();

                if (returnValue.equals("success")) {
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

            @Override
            public void onFailure(Call<LottoDataResponse> call, Throwable t) {
                    Log.d(TAG, t.getMessage());
            }
        });

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

    public void addLottoBall(int num) {
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

    public void cancelBall() {
        if (lottoNum[0] == 0)
            return;

        int temp;
        OUT:
        for(int i = 5; 5 >= 0; i--){
            if(lottoNum[i] != 0){
                lottoNum[i] = 0;
                switch (i){
                    case 0:
                        createMyNumber[0].setValue(new TempLotto(R.drawable.activity_create_my_lotto_non_ball_layout, ""));
                        break OUT;

                    case 1 :
                        createMyNumber[1].setValue(new TempLotto(R.drawable.activity_create_my_lotto_non_ball_layout, ""));
                        break OUT;

                    case 2 :
                        createMyNumber[2].setValue(new TempLotto(R.drawable.activity_create_my_lotto_non_ball_layout, ""));
                        break OUT;

                    case 3 :
                        createMyNumber[3].setValue(new TempLotto(R.drawable.activity_create_my_lotto_non_ball_layout, ""));
                        break OUT;

                    case 4 :
                        createMyNumber[4].setValue(new TempLotto(R.drawable.activity_create_my_lotto_non_ball_layout, ""));
                        break OUT;

                    case 5 :
                        createMyNumber[5].setValue(new TempLotto(R.drawable.activity_create_my_lotto_non_ball_layout, ""));
                        break OUT;
                }
                return;
            }
        }
    }

    public void randomAddLottoBall() {
        initLottoBall();
        for(int i = 0; i<lottoNum.length; i++){
            lottoNum[i] = (int)(Math.random()*45)+1;

            for(int j = 0; j<i; j++){
                if(lottoNum[i] == lottoNum[j]){
                    i--;
                    break;
                }
            }
        }
        Arrays.sort(lottoNum);

        int temp;
        temp = ballBackground(lottoNum[0]);
        createMyNumber[0].setValue(new TempLotto(temp, String.valueOf(lottoNum[0])));

        temp = ballBackground(lottoNum[1]);
        createMyNumber[1].setValue(new TempLotto(temp, String.valueOf(lottoNum[1])));

        temp = ballBackground(lottoNum[2]);
        createMyNumber[2].setValue(new TempLotto(temp, String.valueOf(lottoNum[2])));

        temp = ballBackground(lottoNum[3]);
        createMyNumber[3].setValue(new TempLotto(temp, String.valueOf(lottoNum[3])));

        temp = ballBackground(lottoNum[4]);
        createMyNumber[4].setValue(new TempLotto(temp, String.valueOf(lottoNum[4])));

        temp = ballBackground(lottoNum[5]);
        createMyNumber[5].setValue(new TempLotto(temp, String.valueOf(lottoNum[5])));

    }

    public void halfRandomAddLottoBall() {
        if(lottoNum[0] == 0) {
            Toast.makeText(application, "최소 한개의 번호를 정해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        int a = 0;

        for(int i = 0; i < lottoNum.length; i++){
            if(lottoNum[i] == 0){
                a = i;
                break;
            }
        }

        for(int i = a; i<lottoNum.length; i++){
            lottoNum[i] = (int)(Math.random()*45)+1;

            for(int j = 0; j<i; j++){
                if(lottoNum[i] == lottoNum[j]){
                    i--;
                    break;
                }
            }
        }

        int temp;
        temp = ballBackground(lottoNum[0]);
        createMyNumber[0].setValue(new TempLotto(temp, String.valueOf(lottoNum[0])));

        temp = ballBackground(lottoNum[1]);
        createMyNumber[1].setValue(new TempLotto(temp, String.valueOf(lottoNum[1])));

        temp = ballBackground(lottoNum[2]);
        createMyNumber[2].setValue(new TempLotto(temp, String.valueOf(lottoNum[2])));

        temp = ballBackground(lottoNum[3]);
        createMyNumber[3].setValue(new TempLotto(temp, String.valueOf(lottoNum[3])));

        temp = ballBackground(lottoNum[4]);
        createMyNumber[4].setValue(new TempLotto(temp, String.valueOf(lottoNum[4])));

        temp = ballBackground(lottoNum[5]);
        createMyNumber[5].setValue(new TempLotto(temp, String.valueOf(lottoNum[5])));
    }

    public void initLottoBall(){
        for(int i = 0; i<lottoNum.length; i++)
            lottoNum[i]  = 0;

        createMyNumber[0].setValue(new TempLotto(R.drawable.activity_create_my_lotto_non_ball_layout, ""));
        createMyNumber[1].setValue(new TempLotto(R.drawable.activity_create_my_lotto_non_ball_layout, ""));
        createMyNumber[2].setValue(new TempLotto(R.drawable.activity_create_my_lotto_non_ball_layout, ""));
        createMyNumber[3].setValue(new TempLotto(R.drawable.activity_create_my_lotto_non_ball_layout, ""));
        createMyNumber[4].setValue(new TempLotto(R.drawable.activity_create_my_lotto_non_ball_layout, ""));
        createMyNumber[5].setValue(new TempLotto(R.drawable.activity_create_my_lotto_non_ball_layout, ""));
    }

    private class InsertLottoAsyncTask extends AsyncTask<Void, Void, Void> {
        private LottoDao lottoDao;

        private InsertLottoAsyncTask(LottoDao lottoDao) { this.lottoDao = lottoDao;}

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i = 0; i < 6; i++){
                if(lottoNum[i] == 0){
                    return null;
                }
            }

            Arrays.sort(lottoNum);

            UserLotto userLotto = new UserLotto(String.valueOf(lottoNum[0]), String.valueOf(lottoNum[1]), String.valueOf(lottoNum[2]), String.valueOf(lottoNum[3]), String.valueOf(lottoNum[4]), String.valueOf(lottoNum[5]),
                    ballBackground(lottoNum[0]), ballBackground(lottoNum[1]), ballBackground(lottoNum[2]), ballBackground(lottoNum[3]), ballBackground(lottoNum[4]), ballBackground(lottoNum[5]));

            lottoDao.insert(userLotto);
            return null;
        }
    }

    private static class DeleteLottoAsyncTask extends AsyncTask<UserLotto, Void, Void> {
        private LottoDao lottoDao;

        private DeleteLottoAsyncTask(LottoDao lottoDao) { this.lottoDao = lottoDao;}

        @Override
        protected Void doInBackground(UserLotto... userLottos) {
            lottoDao.delete(userLottos[0]);
            return null;
        }
    }

    private static class DeleteAllLottosAsyncTask extends AsyncTask<Void, Void, Void> {
        private LottoDao lottoDao;

        private DeleteAllLottosAsyncTask(LottoDao lottoDao) { this.lottoDao = lottoDao;}

        @Override
        protected Void doInBackground(Void... voids) {
            lottoDao.deleteAllLottos();
            return null;
        }
    }

}


