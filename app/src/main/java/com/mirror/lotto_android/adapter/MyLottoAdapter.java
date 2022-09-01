package com.mirror.lotto_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mirror.lotto_android.R;
import com.mirror.lotto_android.classes.MyLotto;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyLottoAdapter extends RecyclerView.Adapter<MyLottoAdapter.LottoHolder> {

    private List<MyLotto> lottos = new ArrayList<>();

    @NonNull
    @NotNull
    @Override
    public LottoHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_lotto_item, parent, false);

        return new LottoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LottoHolder holder, int position) {
        MyLotto userLotto = lottos.get(position);
        holder.back1.setBackgroundResource(userLotto.getDrwtNo1_background());
        holder.back2.setBackgroundResource(userLotto.getDrwtNo2_background());
        holder.back3.setBackgroundResource(userLotto.getDrwtNo3_background());
        holder.back4.setBackgroundResource(userLotto.getDrwtNo4_background());
        holder.back5.setBackgroundResource(userLotto.getDrwtNo5_background());
        holder.back6.setBackgroundResource(userLotto.getDrwtNo6_background());

        holder.num1.setText(userLotto.getDrwtNo1());
        holder.num2.setText(userLotto.getDrwtNo2());
        holder.num3.setText(userLotto.getDrwtNo3());
        holder.num4.setText(userLotto.getDrwtNo4());
        holder.num5.setText(userLotto.getDrwtNo5());
        holder.num6.setText(userLotto.getDrwtNo6());

        holder.create_date.setText(userLotto.getCreate_date());
    }

    @Override
    public int getItemCount() {
        return lottos == null ? 0 : lottos.size();
    }

    public void setLottos(List<MyLotto> lottos) {
        this.lottos = lottos;
        notifyDataSetChanged();
    }

    public MyLotto getLottoAt(int position) { return lottos.get(position);}

    class LottoHolder extends RecyclerView.ViewHolder {
        private RelativeLayout back1;
        private RelativeLayout back2;
        private RelativeLayout back3;
        private RelativeLayout back4;
        private RelativeLayout back5;
        private RelativeLayout back6;

        private TextView num1;
        private TextView num2;
        private TextView num3;
        private TextView num4;
        private TextView num5;
        private TextView num6;

        private TextView create_date;

        public LottoHolder(View itemView) {
            super(itemView);
            back1 = itemView.findViewById(R.id.back1);
            back2 = itemView.findViewById(R.id.back2);
            back3 = itemView.findViewById(R.id.back3);
            back4 = itemView.findViewById(R.id.back4);
            back5 = itemView.findViewById(R.id.back5);
            back6 = itemView.findViewById(R.id.back6);

            num1 = itemView.findViewById(R.id.num1);
            num2 = itemView.findViewById(R.id.num2);
            num3 = itemView.findViewById(R.id.num3);
            num4 = itemView.findViewById(R.id.num4);
            num5 = itemView.findViewById(R.id.num5);
            num6 = itemView.findViewById(R.id.num6);

            create_date = itemView.findViewById(R.id.create_date);

        }

    }



}
