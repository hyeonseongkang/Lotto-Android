package com.mirror.lotto_android.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mirror.lotto_android.R;
import com.mirror.lotto_android.adapter.MyLottoAdapter;
import com.mirror.lotto_android.classes.UserLotto;
import com.mirror.lotto_android.databinding.ActivityMyLottoBinding;
import com.mirror.lotto_android.viewmodel.LottoViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyLottoActivity extends AppCompatActivity {

    private static final String TAG =  "MyNumberActivity";

    ActivityMyLottoBinding binding;
    LottoViewModel lottoViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyLottoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);

        MyLottoAdapter adapter = new MyLottoAdapter();
        binding.recyclerView.setAdapter(adapter);

        lottoViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(LottoViewModel.class);
        lottoViewModel.getAllLottos().observe(this, new Observer<List<UserLotto>>() {
            @Override
            public void onChanged(List<UserLotto> userLottos) {
                binding.progress.setVisibility(View.GONE);
                adapter.setLottos(userLottos);
                for (UserLotto userLotto: userLottos) {
                    Log.d(TAG, userLotto.getDrwtNo1() + " " + userLotto.getDrwtNo2() + " " + userLotto.getDrwtNo3()
                            + " " + userLotto.getDrwtNo4() + " " + userLotto.getDrwtNo5() + " " + userLotto.getDrwtNo6());
                }
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                 ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                lottoViewModel.delete(adapter.getLottoAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(binding.recyclerView);

        binding.closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}