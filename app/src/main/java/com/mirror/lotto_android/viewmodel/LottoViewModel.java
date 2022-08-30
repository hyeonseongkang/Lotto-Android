package com.mirror.lotto_android.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mirror.lotto_android.classes.Lotto;
import com.mirror.lotto_android.classes.TempLotto;
import com.mirror.lotto_android.model.LottoRepository;

public class LottoViewModel extends AndroidViewModel {
    private LottoRepository repository;
    private LiveData<Lotto> lottoData;
    private LiveData<TempLotto>[] createMyNumber;

    public LottoViewModel(@NonNull Application application) {
        super(application);
        repository = new LottoRepository(application);
        lottoData = repository.getLottoData();
        createMyNumber = repository.getCreateMyNumber();
    }

    public LiveData<Lotto> getLottoData() {
        return lottoData;
    };

    public LiveData<TempLotto>[] getCreateMyNumber() { return createMyNumber; }

    public void setLottoData(int num) {
        repository.setLottoData(num);
    }

    public void getWeeklyLottoData() {
        repository.getWeeklyLottoData();
    }

    public void addLottoBall(int num) { repository.addLottoBall(num);}

    public void cancelBall() { repository.cancelBall();}

    public void randomAddLottoBall() {
        repository.randomAddLottoBall();
    }

    public void halfRandomAddLottoBall() {
        repository.halfRandomAddLottoBall();
    }
}
