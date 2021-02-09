package com.example.hello;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Signup extends AppCompatActivity {

    private EditText mEmailText, mPasswordText, mName;
    private ImageButton mregisterBtn;
    private FirebaseAuth firebaseAuth;
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //파이어베이스 접근 설정
        mEmailText = (EditText) findViewById(R.id.editText_email);
        mPasswordText = (EditText) findViewById(R.id.editText_passWord);
        mregisterBtn = (ImageButton) findViewById(R.id.btn_join);
        mName = (EditText) findViewById(R.id.editText_name);

        firebaseAuth = FirebaseAuth.getInstance();
        //가입버튼 클릭리스너   -->  firebase에 데이터를 저장한다.

        mregisterBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //가입 정보 가져오기
                final String email = mEmailText.getText().toString();
                final String pwd = mPasswordText.getText().toString();
                final String name = mName.getText().toString();
                //파이어베이스에 신규계정 등록하기
                firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //가입 성공시
                                if (task.isSuccessful()) {
                                    databaseReference.child("User").push().setValue(name);
                                    Intent intent = new Intent(Signup.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Signup.this, "이미 존재하는 아이디 입니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                            }
                        });
            }
        });
    }
}
