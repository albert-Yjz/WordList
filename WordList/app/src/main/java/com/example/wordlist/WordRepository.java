package com.example.wordlist;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

//数据的获取和存储交给仓库来实现，viewmodel调用即可
public class WordRepository {
    private LiveData<List<Word>>allWordsLive;
    private WordDao wordDao;

    WordRepository(Context context) {
        WordDatabase wordDatabase=WordDatabase.getDatabase(context);
        wordDao=wordDatabase.getWordDao();
        allWordsLive=wordDao.getAllWordsLive();
    }
    //创建接口就可以在wordviewmodel中调用了
    void insertWords(Word... words){
        new InsertAsyncTask(wordDao).execute(words);
    }
    void updateWords(Word... words){
        new UpdateAsyncTask(wordDao).execute(words);
    }
    void deleteWords(Word... words){
        new DeleteAsyncTask(wordDao).execute(words);
    }
    void deleteAllWords(){
        new DeleteAllAsyncTask(wordDao).execute();
    }
    LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }

    //Asynctask一般用内部类实现
    static class InsertAsyncTask extends AsyncTask<Word,Void,Void> {//后面参数是报告进度和结果，这里都不用
        private WordDao wordDao;
        //用generate生成
        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override                                                   //这个函数用来实现工作线程上的操作
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }
    }
    static class DeleteAsyncTask extends AsyncTask<Word,Void,Void>{//后面参数是报告进度和结果，这里都不用
        private WordDao wordDao;
        //用generate生成
        public DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override                                                   //这个函数用来实现工作线程上的操作
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }
    }
    static class UpdateAsyncTask extends AsyncTask<Word,Void,Void>{//后面参数是报告进度和结果，这里都不用
        private WordDao wordDao;
        //用generate生成
        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override                                                   //这个函数用来实现工作线程上的操作
        protected Void doInBackground(Word... words) {
            wordDao.updataWords(words);
            return null;
        }
    }
    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{//后面参数是报告进度和结果，这里都不用
        private WordDao wordDao;
        //用generate生成
        public DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override                                                   //这个函数用来实现工作线程上的操作
        protected Void doInBackground(Void... Void) {
            wordDao.deleteAllWords();
            return null;
        }
    }
}
