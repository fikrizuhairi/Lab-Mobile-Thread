package com.example.fikrizuhairi.bitp3453labs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ThreadedActivity extends AppCompatActivity {

    ImageView imageViewProfile;
    TextView textViewHello;
    Button buttonTakePicture, buttonPassImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threaded);

        imageViewProfile = findViewById(R.id.imageViewProfile);
        textViewHello = findViewById(R.id.textViewHello);
        buttonTakePicture = findViewById(R.id.buttonTakePicture);
        buttonPassImage = findViewById(R.id.buttonPassImage);


        Intent intent = getIntent();
        String message = intent.getStringExtra("messageToSend");
        textViewHello.setText("welcome to second activity " + message);

        buttonTakePicture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                takePicture(view);
            }
        });

        buttonPassImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                toFirstActivity(view);
            }
        });

    }

    public void takePicture(View view){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewHello.setText("This is your pic");
                    }
                });
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void toFirstActivity(View view){
        Intent intent = new Intent(this, FirstActivity.class);

        BitmapDrawable drawable = (BitmapDrawable) imageViewProfile.getDrawable();
        Bitmap profileImage = drawable.getBitmap();

        intent.putExtra("profileImage", profileImage);
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imageViewProfile.setImageBitmap(bitmap);

    }
}
