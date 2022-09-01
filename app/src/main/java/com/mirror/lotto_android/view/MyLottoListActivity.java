package com.mirror.lotto_android.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.mirror.lotto_android.R;
import com.mirror.lotto_android.adapter.MyLottoAdapter;
import com.mirror.lotto_android.classes.MyLotto;
import com.mirror.lotto_android.databinding.ActivityMyLottoListBinding;
import com.mirror.lotto_android.viewmodel.LottoViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyLottoListActivity extends AppCompatActivity {

    private static final String TAG =  "MyNumberActivity";

    ActivityMyLottoListBinding binding;
    LottoViewModel lottoViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyLottoListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        overridePendingTransition(R.anim.fadein_left, R.anim.none);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);

        MyLottoAdapter adapter = new MyLottoAdapter();
        binding.recyclerView.setAdapter(adapter);

        lottoViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(LottoViewModel.class);
        lottoViewModel.getAllLottos().observe(this, new Observer<List<MyLotto>>() {
            @Override
            public void onChanged(List<MyLotto> userLottos) {
                binding.progress.setVisibility(View.GONE);
                adapter.setLottos(userLottos);
//                for (UserLotto userLotto: userLottos) {
//                    Log.d(TAG, userLotto.getDrwtNo1() + " " + userLotto.getDrwtNo2() + " " + userLotto.getDrwtNo3()
//                            + " " + userLotto.getDrwtNo4() + " " + userLotto.getDrwtNo5() + " " + userLotto.getDrwtNo6());
//                }
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
                overridePendingTransition(R.anim.none, R.anim.fadeout_left);
            }
        });
    }
}