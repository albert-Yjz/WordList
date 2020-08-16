package com.example.wordlist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void insertWords (Word... words);                      //...表示多个参数
    @Update
    void updataWords(Word... words);
    @Delete
    void deleteWords(Word... words);
    @Query("DELETE FROM WORD")
    void deleteAllWords();
    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    LiveData<List<Word>> getAllWordsLive();              //因为返回livedata，直接在子线程中调用，所以不用在AsyncTask中调用
}
