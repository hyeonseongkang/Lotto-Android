package com.mirror.lotto_android.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mirror.lotto_android.R;
import com.mirror.lotto_android.classes.Lotto;
import com.mirror.lotto_android.databinding.ActivityMainBinding;
import com.mirror.lotto_android.viewmodel.LottoViewModel;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    ActivityMainBinding binding;

    private LottoViewModel lottoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
            }
        });

        lottoViewModel.getWeeklyLottoData();

        binding.createLotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateMyLottoActivity.class);
                startActivity(intent);
            }
        });

        binding.myBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.qrCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}