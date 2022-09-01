package com.mirror.lotto_android.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
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
                binding.no.setText(lotto.getDrwNo() + "회차 당첨번호");

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

        binding.myLotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyLottoListActivity.class);
                startActivity(intent);
            }
        });

        binding.qrCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator qrScan = new IntentIntegrator(MainActivity.this);
                qrScan.setOrientationLocked(false); // 휴대폰 방향에 따라 가로, 세로로 자동 변경 (default: 세로)
                qrScan.initiateScan();
            }
        });

        binding.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CheckMyLottoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                // todo
            } else {
                String uri = result.getContents();
                if(uri != null){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                }
                // todo
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}