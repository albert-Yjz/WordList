package com.example.wordlist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class WordViewModle extends AndroidViewModel {
    private WordRepository wordRepository;
    public WordViewModle(@NonNull Application application) {
        super(application);
        wordRepository=new WordRepository(application);
    }

    //创建接口就可以在main中调用了
    void insertWords(Word... words){
        wordRepository.insertWords(words);
    }
    void updateWords(Word... words){
        wordRepository.updateWords(words);
    }
    void deleteWords(Word... words){
        wordRepository.deleteWords(words);
    }
    void deleteAllWords(){
        wordRepository.deleteAllWords();
    }
    LiveData<List<Word>> getAllWordsLive() {
        return wordRepository.getAllWordsLive();
    }
}
