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

        nbrQuestion = 6;
        scoreP1 = 0;
        scoreP2= 0;




    }

    @Override
    protected void onStart() {
        super.onStart();
        BT_Vrai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reponse == 1) {
                    scoreP1 += 1;
                } else {
                    scoreP1 -= 1;
                }
                BT_Vrai1.setEnabled(false);
                String score = "Score : " + scoreP1.toString();
                TV_Score1.setText(score);
            }
        });
        BT_Vrai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reponse == 1) {
                    scoreP2 += 1;
                } else {
                    scoreP2 -= 1;
                }
                BT_Vrai2.setEnabled(false);
                String score = "Score : " + scoreP2.toString();
                TV_Score2.setText(score);
            }
        });
        game();

    }
    private void game() {
        Handler handler = new Handler();
        questionRunnable = new Runnable() {

            @Override
            public void run() {
                if (manager.listSize() == 0) {
                    if (scoreP1 > scoreP2) {
                        TV_Question_P1.setText("Gagner !");
                        TV_Question_P2.setText("Perdu !");
                    } else if (scoreP2 > scoreP1) {
                        TV_Question_P1.setText("Perdu !");
                        TV_Question_P2.setText("Gagner !");
                    }
                    handler.removeCallbacks(this);
                } else {
                    BT_Vrai1.setEnabled(true);
                    BT_Vrai2.setEnabled(true);
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
}