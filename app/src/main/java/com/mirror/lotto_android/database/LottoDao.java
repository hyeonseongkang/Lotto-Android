package com.mirror.lotto_android.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mirror.lotto_android.classes.UserLotto;

import java.util.List;

@Dao
public interface LottoDao {

    @Insert
    void insert(UserLotto lotto);

    @Delete
    void delete(UserLotto lotto);

    @Query("DELETE FROM lotto_table")
    void deleteAllLottos();

    @Query("SELECT * FROM lotto_table")
    LiveData<List<UserLotto>> getAllLottos();

}
