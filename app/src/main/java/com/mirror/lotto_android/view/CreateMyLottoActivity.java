package com.mirror.lotto_android.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.mirror.lotto_android.R;
import com.mirror.lotto_android.classes.TempLotto;
import com.mirror.lotto_android.databinding.ActivityCreateMyLottoBinding;
import com.mirror.lotto_android.viewmodel.LottoViewModel;

public class CreateMyLottoActivity extends AppCompatActivity {

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

        binding.n1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ lottoViewModel.addLottoNumber(1);
            }
        });

        binding.n2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lottoViewModel.addLottoNumber(2);
            }
        });

        binding.n3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(3);
            }
        });

        binding.n4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(4);
            }
        });

        binding.n5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(5);
            }
        });

        binding.n6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(6);
            }
        });

        binding.n7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(7);
            }
        });

        binding.n8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(8);
            }
        });

        binding.n9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(9);
            }
        });

        binding.n10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(10);
            }
        });

        binding.n11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(11);
            }
        });

        binding.n12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(12);
            }
        });

        binding.n13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(13);
            }
        });

        binding.n14.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(14);
            }
        });

        binding.n15.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(15);
            }
        });

        binding.n16.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(16);
            }
        });

        binding.n17.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(17);
            }
        });

        binding.n18.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(18);
            }
        });

        binding.n19.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(19);
            }
        });

        binding.n20.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(20);
            }
        });

        binding.n21.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(21);
            }
        });

        binding.n22.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(22);
            }
        });

        binding.n23.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(23);
            }
        });

        binding.n24.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(24);
            }
        });

        binding.n25.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(25);
            }
        });

        binding.n26.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(26);
            }
        });

        binding.n27.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(27);
            }
        });

        binding.n28.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(28);
            }
        });

        binding.n29.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(29);
            }
        });

        binding.n30.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(30);
            }
        });

        binding.n31.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(31);
            }
        });

        binding.n32.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(32);
            }
        });

        binding.n33.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(33);
            }
        });

        binding.n34.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(34);
            }
        });

        binding.n35.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(35);
            }
        });

        binding.n36.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(36);
            }
        });

        binding.n37.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(37);
            }
        });

        binding.n38.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(38);
            }
        });

        binding.n39.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(39);
            }
        });

        binding.n40.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(40);
            }
        });

        binding.n41.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(41);
            }
        });

        binding.n42.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(42);
            }
        });

        binding.n43.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(43);
            }
        });

        binding.n44.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(44);
            }
        });

        binding.n45.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                lottoViewModel.addLottoNumber(45);
            }
        });


    }
}