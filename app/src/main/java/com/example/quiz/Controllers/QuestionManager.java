package com.example.quiz.Controllers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quiz.MainActivity;
import com.example.quiz.Models.Question;
import com.example.quiz.Models.QuizSQLiteOpenHelper;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Random;



public class QuestionManager {
    ArrayList<Question> questionList;
  //  ArrayList<String> question = new ArrayList<String>();
    // Gère une liste question
   // public void listeQuestion() {
      //  question.add("Volvo est une marque Écossaise"); // Faux
      //  question.add("BMW est une marque Allemande"); // Vrai
      //  question.add("Ford est une marque Américaine"); // Vrai
      //  question.add("Mazda est une marque Allemande"); // Faux
      //  question.add("Mazerati à été fonder avant Porsche"); //Vrai
      // question.add("Honda est une marque chinoise "); // Faux
   // }

    public String getQuestion() {

        Random random = new Random();
        int pos = (int) (Math.random() * questionList.size());
        String randomQuestion = questionList.get(pos).getQuestion();

        return randomQuestion;
    }

    /**
     * Constructeur de classe qui initialise une nouvelle liste de question
     * @param context Contexte d'application
     */
    public QuestionManager(Context context)
    {
        questionList = initQuestionList(context);
    }

    /**
     * Charge une liste de question depuis la DB.
     * @param context Le contexte de l'application pour passer la query
     * @return Une arraylist charger de Question
     */
    private ArrayList<Question> initQuestionList(Context context){
        ArrayList<Question> listQuestion = new ArrayList<>();
        QuizSQLiteOpenHelper helper = new QuizSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(true,"quiz",new String[]{"idQuiz","question","reponse"},null,null,null,null,"idquiz",null);
        while(cursor.moveToNext()){
            listQuestion.add(new Question(cursor));
        }
        cursor.close();
        db.close();
        return listQuestion;
    }
}
