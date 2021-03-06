package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quiz.Controllers.QuestionManager;
import com.example.quiz.Models.Question;
import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.TimeUnit;


public class GameActivity extends AppCompatActivity {
    private String player1_name;
    private String player2_name;
    private String question;

    private Question myQuestion;

    private Integer nbrQuestion;
    private Integer reponse;
    private Integer scoreP1;
    private Integer scoreP2;

    private TextView TV_player1;
    private TextView TV_player2;
    private TextView TV_Question_P1;
    private TextView TV_Question_P2;
    private TextView TV_Score1;
    private TextView TV_Score2;

    private Button BT_Vrai1;
    private Button BT_Vrai2;

    Runnable questionRunnable = null;
    private QuestionManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent ret = getIntent();
        //Crée un nouveau manager
        manager = new QuestionManager(GameActivity.this);

        player1_name = ret.getStringExtra("Player1");
        player2_name = ret.getStringExtra("Player2");

        //Initialiser les textView
        TV_player1 = findViewById(R.id.game_name_p1);
        TV_player2 = findViewById(R.id.game_name_p2);
        TV_Question_P1 = findViewById(R.id.game_question_p1);
        TV_Question_P2 = findViewById(R.id.game_question_p2);
        TV_Score1 = findViewById(R.id.game_score_p1);
        TV_Score2 = findViewById(R.id.game_score_p2);

        //Initialise les buttons
        BT_Vrai1 = findViewById(R.id.game_vrai_button_player1);
        BT_Vrai2 = findViewById(R.id.game_vrai_button_player2);

        //Définit le nom des joueurs
        TV_player1.setText(player1_name);
        TV_player2.setText(player2_name);

        //Initialise les variables de jeux
        nbrQuestion = 6;
        scoreP1 = 0;
        scoreP2= 0;

        // Désactive les bouttons
        unableBT();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Si le joueur 1 clic, change son score en fonction de sa réponse
        BT_Vrai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reponse == 1) {
                    scoreP1 += 1;
                } else {
                    scoreP1 -= 1;
                }
                unableBT();
                String score = "Score : " + scoreP1.toString();
                TV_Score1.setText(score);
            }
        });

        // Si le joueur 2 clic, change son score en fonction de sa réponse
        BT_Vrai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reponse == 1) {
                    scoreP2 += 1;
                } else {
                    scoreP2 -= 1;
                }
                unableBT();
                String score = "Score : " + scoreP2.toString();
                TV_Score2.setText(score);
            }
        });
        game();
    }

    //Réalise le jeux (moteur du jeux)
    private void game() {
        Handler handler = new Handler();
        questionRunnable = new Runnable() {

            @Override
            public void run() {
                if (manager.listSize() == 0) {
                    resultat();
                    handler.removeCallbacks(this);
                } else {
                    activateBT();
                    myQuestion = manager.getQuestion();
                    question = myQuestion.getQuestion();
                    TV_Question_P1.setText(question);
                    TV_Question_P2.setText(question);
                    reponse = myQuestion.getReponse();
                    handler.postDelayed(this, 3000);
                }

            }

        };
        handler.postDelayed(questionRunnable, 3000);
    }

    // Affiche les résultats du jeux
    private void resultat() {
        if (scoreP1 > scoreP2) {
            TV_Question_P1.setText("Gagner !");
            TV_Question_P2.setText("Perdu !");
        } else if (scoreP2 > scoreP1) {
            TV_Question_P1.setText("Perdu !");
            TV_Question_P2.setText("Gagner !");
        } else {
            TV_Question_P1.setText("Egalité !");
            TV_Question_P2.setText("Egalité !");
        }
    }

    // Désactive les boutons
    private void unableBT() {
        BT_Vrai2.setEnabled(false);
        BT_Vrai1.setEnabled(false);
    }

    // Active les boutons
    private void activateBT() {
        BT_Vrai1.setEnabled(true);
        BT_Vrai2.setEnabled(true);
    }
}