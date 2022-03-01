package com.example.quiz.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizSQLiteOpenHelper extends SQLiteOpenHelper{

    static String DB_NAME="SpeedQuiz.db";
    static int DB_VERSION=1;
    public QuizSQLiteOpenHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDatatableQuiz = "CREATE TABLE quiz(idQuiz INTEGER PRIMARY KEY,question TEXT,reponse INTEGER);";
        db.execSQL(sqlCreateDatatableQuiz);
        db.execSQL("INSERT INTO quiz VALUES(1,\"Volvo est une marque Écossaise\",0)");
        db.execSQL("INSERT INTO quiz VALUES(2,\"BMW est une marque Allemande\",0)");
        db.execSQL("INSERT INTO quiz VALUES(3,\"Ford est une marque Américaine\",0)");
        db.execSQL("INSERT INTO quiz VALUES(4,\"Mazda est une marque Allemande\",1)");
        db.execSQL("INSERT INTO quiz VALUES(5,\"Mazerati à été fonder avant Porsche\",1)");
        db.execSQL("INSERT INTO quiz VALUES(6,\"Honda est une marque chinoise\",1)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
