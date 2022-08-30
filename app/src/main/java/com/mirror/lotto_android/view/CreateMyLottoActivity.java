package com.mirror.lotto_android.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mirror.lotto_android.R;
import com.mirror.lotto_android.classes.TempLotto;
import com.mirror.lotto_android.classes.UserLotto;
import com.mirror.lotto_android.databinding.ActivityCreateMyLottoBinding;
import com.mirror.lotto_android.viewmodel.LottoViewModel;

import java.util.List;

public class CreateMyLottoActivity extends AppCompatActivity {
    // 전체 바꾸기 control + shift + R

    public static final String TAG = "CreateMyLottoActivity";

    ActivityCreateMyLottoBinding binding;
    LottoViewModel lottoViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateMyLottoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        lottoViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(LottoViewModel.class);


        lottoViewModel.getCreateMyNumber()[0].observe(this, new Observer<TempLotto>() {
            @Override
            public void onChanged(TempLotto tempLotto) {
                binding.s11.setBackgroundResource(tempLotto.getBackgroundResource());
                binding.sn1.setText(tempLotto.getNumber());
            }
        });

        lottoViewModel.getCreateMyNumber()[1].observe(this, new Observer<TempLotto>() {
            @Override
            public void onChanged(TempLotto tempLotto) {
                binding.s2.setBackgroundResource(tempLotto.getBackgroundResource());
                binding.sn2.setText(tempLotto.getNumber());
            }
        });

        lottoViewModel.getCreateMyNumber()[2].observe(this, new Observer<TempLotto>() {
            @Override
            public void onChanged(TempLotto tempLotto) {
                binding.s3.setBackgroundResource(tempLotto.getBackgroundResource());
                binding.sn3.setText(tempLotto.getNumber());
            }
        });

        lottoViewModel.getCreateMyNumber()[3].observe(this, new Observer<TempLotto>() {
            @Override
            public void onChanged(TempLotto tempLotto) {
                binding.s4.setBackgroundResource(tempLotto.getBackgroundResource());
                binding.sn4.setText(tempLotto.getNumber());
            }
        });

        lottoViewModel.getCreateMyNumber()[4].observe(this, new Observer<TempLotto>() {
            @Override
            public void onChanged(TempLotto tempLotto) {
                binding.s5.setBackgroundResource(tempLotto.getBackgroundResource());
                binding.sn5.setText(tempLotto.getNumber());
            }
        });

        lottoViewModel.getCreateMyNumber()[5].observe(this, new Observer<TempLotto>() {
            @Override
            public void onChanged(TempLotto tempLotto) {
                binding.s6.setBackgroundResource(tempLotto.getBackgroundResource());
                binding.sn6.setText(tempLotto.getNumber());
            }
        });


        binding.closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.cancelBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottoViewModel.cancelBall();
            }
        });

        binding.random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottoViewModel.randomAddLottoBall();
            }
        });

        binding.halfRamdom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottoViewModel.halfRandomAddLottoBall();
            }
        });

        binding.createLotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottoViewModel.insert();
                lottoViewModel.getAllLottos().observe(CreateMyLottoActivity.this, new Observer<List<UserLotto>>() {
                    @Override
                    public void onChanged(List<UserLotto> userLottos) {
                        UserLotto userLotto = userLottos.get(userLottos.size() - 1);
                        lottoViewModel.initLottoBall();

                        binding.lastN1.setText(userLotto.getDrwtNo1());
                        binding.lastN2.setText(userLotto.getDrwtNo2());
                        binding.lastN3.setText(userLotto.getDrwtNo3());
                        binding.lastN4.setText(userLotto.getDrwtNo4());
                        binding.lastN5.setText(userLotto.getDrwtNo5());
                        binding.lastN6.setText(userLotto.getDrwtNo6());

                        binding.lastBack1.setBackgroundResource(userLotto.getDrwtNo1_background());
                        binding.lastBack2.setBackgroundResource(userLotto.getDrwtNo2_background());
                        binding.lastBack3.setBackgroundResource(userLotto.getDrwtNo3_background());
                        binding.lastBack4.setBackgroundResource(userLotto.getDrwtNo4_background());
                        binding.lastBack5.setBackgroundResource(userLotto.getDrwtNo5_background());
                        binding.lastBack6.setBackgroundResource(userLotto.getDrwtNo6_background());
                    }
                });
            }
        });

        binding.n1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ lottoViewModel.addLottoBall(1);
            }
        });

        binding.n2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lottoViewModel.addLottoBall(2);
            }
        });

        binding.n3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(3);
            }
        });

        binding.n4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(4);
            }
        });

        binding.n5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(5);
            }
        });

        binding.n6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(6);
            }
        });

        binding.n7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(7);
            }
        });

        binding.n8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(8);
            }
        });

        binding.n9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(9);
            }
        });

        binding.n10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(10);
            }
        });

        binding.n11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(11);
            }
        });

        binding.n12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(12);
            }
        });

        binding.n13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(13);
            }
        });

        binding.n14.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(14);
            }
        });

        binding.n15.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(15);
            }
        });

        binding.n16.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(16);
            }
        });

        binding.n17.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(17);
            }
        });

        binding.n18.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(18);
            }
        });

        binding.n19.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(19);
            }
        });

        binding.n20.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(20);
            }
        });

        binding.n21.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(21);
            }
        });

        binding.n22.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(22);
            }
        });

        binding.n23.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(23);
            }
        });

        binding.n24.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(24);
            }
        });

        binding.n25.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(25);
            }
        });

        binding.n26.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(26);
            }
        });

        binding.n27.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(27);
            }
        });

        binding.n28.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(28);
            }
        });

        binding.n29.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(29);
            }
        });

        binding.n30.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(30);
            }
        });

        binding.n31.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(31);
            }
        });

        binding.n32.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(32);
            }
        });

        binding.n33.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(33);
            }
        });

        binding.n34.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(34);
            }
        });

        binding.n35.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(35);
            }
        });

        binding.n36.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(36);
            }
        });

        binding.n37.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(37);
            }
        });

        binding.n38.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(38);
            }
        });

        binding.n39.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(39);
            }
        });

        binding.n40.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(40);
            }
        });

        binding.n41.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(41);
            }
        });

        binding.n42.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(42);
            }
        });

        binding.n43.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(43);
            }
        });

        binding.n44.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(44);
            }
        });

        binding.n45.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoBall(45);
            }
        });
    }
}