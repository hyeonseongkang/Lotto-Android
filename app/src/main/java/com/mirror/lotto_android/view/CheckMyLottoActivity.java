package com.mirror.lotto_android.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mirror.lotto_android.adapter.CheckMyLottoAdapter;
import com.mirror.lotto_android.classes.CheckMyLotto;
import com.mirror.lotto_android.classes.Lotto;
import com.mirror.lotto_android.classes.MyLotto;
import com.mirror.lotto_android.databinding.ActivityCheckMyLottoBinding;
import com.mirror.lotto_android.viewmodel.LottoViewModel;

import java.util.List;

public class CheckMyLottoActivity extends AppCompatActivity {
    private static final String TAG =  "CheckMyLottoActivity";

    ActivityCheckMyLottoBinding binding;

    private LottoViewModel lottoViewModel;

    Lotto selectedLotto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckMyLottoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);

        CheckMyLottoAdapter adapter = new CheckMyLottoAdapter();
        binding.recyclerView.setAdapter(adapter);

        lottoViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(LottoViewModel.class);
        lottoViewModel.getLottoData().observe(this, new Observer<Lotto>() {
            @Override
            public void onChanged(Lotto lotto) {
                binding.progress.setVisibility(View.GONE);
                binding.drawDate.setText(lotto.getDrwNoDate());
                binding.winner.setText(lotto.getFirstPrzwnerCo() + "명");
                binding.winningAmount.setText(lotto.getFirstWinamnt());
                binding.salesAmount.setText(lotto.getTotSellamnt());
                binding.winningTotalAmount.setText(lotto.getFirstAccumamnt());
                binding.no.setText(lotto.getDrwNo());

                binding.ball1.setText(lotto.getDrwtNo1());
                binding.ball2.setText(lotto.getDrwtNo2());
                binding.ball3.setText(lotto.getDrwtNo3());
                binding.ball4.setText(lotto.getDrwtNo4());
                binding.ball5.setText(lotto.getDrwtNo5());
                binding.ball6.setText(lotto.getDrwtNo6());
                binding.ball7.setText(lotto.getBnusNo());

                binding.border1.setBackgroundResource(lotto.getDrwtNo1_background());
                binding.border2.setBackgroundResource(lotto.getDrwtNo2_background());
                binding.border3.setBackgroundResource(lotto.getDrwtNo3_background());
                binding.border4.setBackgroundResource(lotto.getDrwtNo4_background());
                binding.border5.setBackgroundResource(lotto.getDrwtNo5_background());
                binding.border6.setBackgroundResource(lotto.getDrwtNo6_background());
                binding.border7.setBackgroundResource(lotto.getBnusNo_background());

                selectedLotto = lotto;
                lottoViewModel.checkMyLotto(lotto);
            }
        });

        lottoViewModel.getWeeklyLottoData();

        lottoViewModel.getAllLottos().observe(this, new Observer<List<MyLotto>>() {
            @Override
            public void onChanged(List<MyLotto> myLottos) {

            }
        });

        lottoViewModel.getAllCheckLottos().observe(this, new Observer<List<CheckMyLotto>>() {
            @Override
            public void onChanged(List<CheckMyLotto> checkUserLottos) {
                adapter.setLottos(checkUserLottos);
            }
        });

        binding.leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottoViewModel.setLottoData(-1);
            }
        });

        binding.rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottoViewModel.setLottoData(1);
            }
        });

        binding.closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}