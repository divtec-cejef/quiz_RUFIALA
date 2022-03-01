package com.example.quiz.Models;

import android.database.Cursor;

public class Question {

    private String question;
    private int reponse;

    public Question(Cursor cursor){
        question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
        reponse = cursor.getInt(cursor.getColumnIndexOrThrow("reponse"));
    }
    // Définit la question
}
