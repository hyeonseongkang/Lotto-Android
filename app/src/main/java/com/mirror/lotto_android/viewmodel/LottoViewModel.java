package com.mirror.lotto_android.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mirror.lotto_android.classes.CheckMyLotto;
import com.mirror.lotto_android.classes.Lotto;
import com.mirror.lotto_android.classes.MyLotto;
import com.mirror.lotto_android.classes.TempLotto;
import com.mirror.lotto_android.model.LottoRepository;

import java.util.List;

public class LottoViewModel extends AndroidViewModel {
    private LottoRepository repository;
    private LiveData<Lotto> lottoData;
    private LiveData<TempLotto>[] createMyNumber;
    private LiveData<List<MyLotto>> allLottos;
    private LiveData<List<CheckMyLotto>> allCheckLottos;

    public LottoViewModel(@NonNull Application application) {
        super(application);
        repository = new LottoRepository(application);
        lottoData = repository.getLottoData();
        createMyNumber = repository.getCreateMyNumber();
        allLottos = repository.getAllLottos();
        allCheckLottos = repository.getAllCheckLottos();
    }

    public LiveData<List<MyLotto>> getAllLottos() { return allLottos; }

    public LiveData<List<CheckMyLotto>> getAllCheckLottos() { return allCheckLottos; }

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

    public void insert() { repository.insert();}

    public void delete(MyLotto userLotto) { repository.delete(userLotto);}

    public void deleteAllLottos() { repository.deleteAllLottos();}

    public void initLottoBall() { repository.initLottoBall(); }

    public void checkMyLotto(Lotto lotto) { repository.checkMyLotto(lotto);}
}
