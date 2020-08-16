package com.example.wordlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    WordViewModle wordViewModle;
    RecyclerView recyclerView;
    Switch aSwitch;
    Button buttonInsert,buttonClear;
    MyAdapter myAdapter1,myAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        myAdapter1=new MyAdapter(false);
        myAdapter2=new MyAdapter(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  //layoutmanager共有两种布局，一种是线性，一种是行列
        recyclerView.setAdapter(myAdapter1);
        aSwitch=findViewById(R.id.switch1);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    recyclerView.setAdapter(myAdapter2);
                } else {
                    recyclerView.setAdapter(myAdapter1);
                }
            }
        });
        wordViewModle = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(WordViewModle.class);
        wordViewModle.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                myAdapter1.setAllWords(words);
                myAdapter2.setAllWords(words);
                myAdapter1.notifyDataSetChanged();
                myAdapter2.notifyDataSetChanged();
            }
        });

        buttonClear=findViewById(R.id.button2);
        buttonInsert=findViewById(R.id.button1);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] english={
                        "hello","hi","valse","good","master"
                };
                String[] chinese={
                        "你好","woqu","圣诞节","怎么说","大师"
                };
                for(int i=0;i<english.length;i++){
                    wordViewModle.insertWords(new Word(english[i],chinese[i]));
                }
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordViewModle.deleteAllWords();
            }
        });
    }
}