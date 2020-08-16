package com.example.wordlist;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//先构造MyViewHolder，再在MyAdapter<>中写这个MyViewHolder
//这个Adapter主要处理recycleView的逻辑
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<Word> allWords=new ArrayList<>();
    boolean useCardView;                                     //控制版本号

    MyAdapter(boolean useCardView) {
        this.useCardView = useCardView;
    }

    void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView;
        if(useCardView){
            itemView= layoutInflater.inflate(R.layout.cell_card,parent,false);
        }else{
            itemView= layoutInflater.inflate(R.layout.cell_normal,parent,false);
        }
        return new MyViewHolder(itemView);
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Word word=allWords.get(position);
        holder.textViewNumber.setText(String.valueOf(position +1));         //position是当前列表中位置
        holder.textViewEnglish.setText(word.getWord());
        holder.textViewChinese.setText(word.getMeaning());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("https://www.youdao.com/w/eng/"+holder.textViewEnglish.getText());
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNumber,textViewEnglish,textViewChinese;
         public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber=itemView.findViewById(R.id.textViewNumber);
            textViewEnglish=itemView.findViewById(R.id.textViewEnglish);
            textViewChinese=itemView.findViewById(R.id.textViewChinese);
        }
    }
}
