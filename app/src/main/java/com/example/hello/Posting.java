package com.example.hello;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Posting extends AppCompatActivity {

    private Spinner spinner;
    private Spinner spinner2;
    private TextView tv_result;

    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    EditText editText;
    public EditText post_story;
    ImageButton imageupload;
    private ImageButton upload;
    public String foodName;
    public String foodAmount;
    public boolean isFoodNameSelected = false;
    public boolean isFoodAmountSelected = false;
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = firebaseDatabase.getReference();
    public ImageView imageView;
    ImageButton myPageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);
        myPageButton = (ImageButton)findViewById(R.id.imageButton7);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        tv_result = (TextView) findViewById(R.id.edittxt);
        imageView = findViewById(R.id.imageView15);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 0);
            }
        });

        myPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Posting.this, Mypage.class);
                startActivity(intent);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                foodName = parent.getItemAtPosition(position).toString();
                isFoodNameSelected = true;

                if (isFoodNameSelected || isFoodAmountSelected) {
                    tv_result.setText(foodName + "/" + foodAmount);
                    isFoodNameSelected = false;
                    isFoodAmountSelected = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        imageupload = (ImageButton) findViewById(R.id.imgupload);
        imageupload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, 1000);
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                foodAmount = parent.getItemAtPosition(position).toString();
                isFoodAmountSelected = true;

                if (isFoodNameSelected || isFoodAmountSelected) {
                    tv_result.setText(foodName + "/" + foodAmount);
                    isFoodNameSelected = false;
                    isFoodAmountSelected = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listView = (ListView) findViewById(R.id.listView);
        String[] android_flavours = {};
        String[] foodNameArr = {};
        arrayList = new ArrayList<>(Arrays.asList(android_flavours));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        upload = (ImageButton) findViewById(R.id.upload_bt);
        post_story = (EditText) findViewById(R.id.post_edit);
        editText = (EditText) findViewById(R.id.edittxt);

        upload.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String post_body = post_story.getText().toString();
                databaseReference.child("Postbody").push().setValue(post_body);
                Intent intent = new Intent(Posting.this, Feed2.class);
                startActivity(intent);
            }
        });

        ImageButton btnAdd = (ImageButton) findViewById(R.id.additems);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String additem = editText.getText().toString();
                databaseReference.child("food").push().setValue(additem);
                arrayList.add(additem);
                adapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), tv_result.getText() + " is added in List", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            InputStream in = getContentResolver().openInputStream(data.getData());
            Bitmap img = BitmapFactory.decodeStream(in);
            imageView.setImageBitmap(img);
            Toast.makeText(Posting.this,"사진이 업로드 되었습니다",Toast.LENGTH_SHORT).show();
            in.close();
        }catch(Exception e) {

        }

    }
}