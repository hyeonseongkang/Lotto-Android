package com.mirror.lotto_android.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mirror.lotto_android.classes.Lotto;
import com.mirror.lotto_android.model.LottoRepository;

public class LottoViewModel extends AndroidViewModel {
    private LottoRepository repository;
    private LiveData<Lotto> lottoData;

    public LottoViewModel(@NonNull Application application) {
        super(application);
        repository = new LottoRepository(application);
        lottoData = repository.getLottoData();
    }

    public LiveData<Lotto> getLottoData() {
        return lottoData;
    };

    public void setLottoData(int num) {
        repository.setLottoData(num);
    }

    public void getWeeklyLottoData() {
        repository.getWeeklyLottoData();
    }
}
