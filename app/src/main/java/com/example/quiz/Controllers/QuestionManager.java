package com.example.quiz.Controllers;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Random;

public class QuestionManager {
    ArrayList<String> question = new ArrayList<String>();
    // Gère une liste question
    public void listeQuestion() {
        question.add("Volvo est une marque Écossaise");
        question.add("BMW est une marque Allemande");
        question.add("Ford est une marque Américaine");
        question.add("Mazda est une marque Allemande");
    }

    public String getQuestion() {

        Random random = new Random();
        int pos = (int) (Math.random() * question.size());
        String randomQuestion = question.get(pos);
        return randomQuestion;
    }
}
