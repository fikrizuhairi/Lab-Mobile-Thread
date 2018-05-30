package com.example.fikrizuhairi.bitp3453labs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class FirstActivity extends AppCompatActivity{

    TextView textViewAge;
    EditText editName, editYear;
    Button buttonClick, buttonNewActivity;
    ImageView imageFromSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        textViewAge = findViewById(R.id.textViewAge);
        editName = findViewById(R.id.editTextName);
        editYear = findViewById(R.id.editYear);
        buttonClick = findViewById(R.id.buttonMe);
        buttonNewActivity = findViewById(R.id.buttonSecondActivity);
        imageFromSecond = findViewById(R.id.imageViewFromSecond);

        buttonClick.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                greet(view);
            }
        });

        buttonNewActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                threadActivity(view);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("profileImage");
        imageFromSecond.setImageBitmap(bitmap);

    }

    public void greet(View view){
        String name = editName.getText().toString();

        Calendar calendar = Calendar.getInstance();
        int thisYear = calendar.get(Calendar.YEAR);
        int birthYear = Integer.parseInt(editYear.getText().toString());
        int age = thisYear-birthYear;

        textViewAge.setText("Hello and welcome " + name + ".You are " + age + " years old." );
    }

    public void threadActivity(View view){
        Intent intent = new Intent(this, ThreadedActivity.class);
        String message = editName.getText().toString();
        intent.putExtra("messageToSend", message);
        startActivity(intent);
    }

}
