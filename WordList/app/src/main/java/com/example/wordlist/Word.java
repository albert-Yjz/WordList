package com.example.wordlist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)                                              //添加主键，自动增长
    private int id;
    @ColumnInfo(name = "english_word")                                            //便于记忆的名字，不写就用变量
    private String word;
    @ColumnInfo(name = "chinese_meaning")
    private String meaning;

    public Word(String word,String meaning){
        this.word=word;
        this.meaning=meaning;
    }
    //用generate 构造器自动生成
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
