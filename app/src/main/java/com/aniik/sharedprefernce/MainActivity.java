package com.aniik.sharedprefernce;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnPlay,btnReset;
    TextView txtHighScore,txtScore;


    //Declare shared preference
    SharedPreferences preferences;
    SharedPreferences.Editor  editor;
    public  String Pref_Game="com.aniik.sharedprefernce.highscore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay=findViewById(R.id.btn_play);
        btnReset=findViewById(R.id.btn_reset);

        txtScore=findViewById(R.id.txt_score);
        txtHighScore=findViewById(R.id.txt_high_score);

        preferences=getSharedPreferences(Pref_Game,MODE_PRIVATE);

        editor=preferences.edit();

        final int highScore=preferences.getInt("high_score",0);
        txtHighScore.setText("High Score: "+highScore);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random=new Random();
                int score=random.nextInt(2000);
                txtScore.setText(String.valueOf(score));

                int getSaveScore=preferences.getInt("high_score",0);

                if (score>getSaveScore)
                {
                    txtHighScore.setText("High Score: "+score);
                    editor.putInt("high_score",score);
                    editor.commit(); //for save value in shared preference file
                }
            }
        });



        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putInt("high_score",0);
                editor.commit();

                txtHighScore.setText("High Score: "+0);
                txtScore.setText("0");

            }
        });


    }
}
