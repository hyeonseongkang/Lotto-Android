package com.mirror.lotto_android.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mirror.lotto_android.classes.MyLotto;

@Database(entities = {MyLotto.class}, version = 2)
public abstract class LottoDatabase  extends RoomDatabase {

    private static LottoDatabase instance;

    public abstract LottoDao lottoDao();

    public static synchronized LottoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LottoDatabase.class, "lotto_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
