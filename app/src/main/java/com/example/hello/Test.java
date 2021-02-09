package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Test extends AppCompatActivity {

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button upload = (Button) findViewById(R.id.save_btn);
        EditText editt = (EditText) findViewById(R.id.post);
        upload.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String edit = editt.getText().toString();
                databaseReference.child("message").push().setValue(edit);
            }
        });
    }


}