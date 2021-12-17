package ru.myitschool.lesson211214;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Scanner;

public class Game extends AppCompatActivity implements View.OnClickListener {
    TextView nameperson, param, storyperson;
    Button[] btngame = new Button[3];
    String name;
    public int curent = 1;
    public static Character manager;
    public static Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        nameperson = findViewById(R.id.name);
        param = findViewById(R.id.param);
        storyperson = findViewById(R.id.story);
        btngame[0] = findViewById(R.id.bt1);
        btngame[1] = findViewById(R.id.bt2);
        btngame[2] = findViewById(R.id.bt3);
        btngame[0].setOnClickListener(this);
        btngame[1].setOnClickListener(this);
        btngame[2].setOnClickListener(this);

        name = getIntent().getStringExtra("name");
        manager = new Character(name);
        nameperson.setText(manager.name);
        param.setText("Карьера:" + manager.K + "\tАктивы:"
                + manager.A + "\tРепутация:" + manager.R);
        //storyperson.setText("");.
        story = new Story();
        storyperson.setText(story.current_situation.subject);
        storyperson.append("\n" + story.current_situation.text);
    }

    @Override
    public void onClick(View view) {
        story = new Story();
        switch (view.getId()) {
            case R.id.bt1:
                game(story, 1);
                curent = 3;
                break;
            case R.id.bt2:
                game(story, 2);
                curent = 1;
                break;
            case R.id.bt3:
                game(story, 3);
                curent = 2;
                break;
        }
    }

    public void game(Story story, int curent) {


        while (true) {
            manager.A += story.current_situation.dA;
            manager.K += story.current_situation.dK;
            manager.R += story.current_situation.dR;
            param.setText("Карьера:" + manager.K + "\tАктивы:"
                    + manager.A + "\tРепутация:" + manager.R + "\n=====");
            storyperson.setText(story.current_situation.subject);
            storyperson.append("\n" + story.current_situation.text);
            if (story.isEnd()) {

                return;
            }
            story.go(curent);
        }

    }


}
