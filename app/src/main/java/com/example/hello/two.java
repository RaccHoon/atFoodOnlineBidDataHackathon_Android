package com.example.hello;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class two extends AppCompatActivity {
    ImageButton myPageButton;
    TextView ingredient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedthree);
        myPageButton = (ImageButton)findViewById(R.id.mypage_bt);
        myPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(two.this, Mypage.class);
                startActivity(intent);
            }
        });
        ingredient = (TextView)findViewById(R.id.ingredient2);
        ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(two.this, Price.class);
                startActivity(intent);
            }
        });
    }
}