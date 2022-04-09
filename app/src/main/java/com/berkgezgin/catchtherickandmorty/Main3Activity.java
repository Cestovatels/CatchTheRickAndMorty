package com.berkgezgin.catchtherickandmorty;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class Main3Activity extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    int score;
    GifImageView image1;
    GifImageView image2;
    GifImageView image3;
    GifImageView image4;
    GifImageView image5;
    GifImageView image6;
    GifImageView image7;
    GifImageView image8;
    GifImageView image9;

    GifImageView[] imageArry;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        timeText=(TextView) findViewById(R.id.timeText);
        scoreText=(TextView) findViewById(R.id.scoreText);


        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        image3=findViewById(R.id.image3);
        image4=findViewById(R.id.image4);
        image5=findViewById(R.id.image5);
        image6=findViewById(R.id.image6);
        image7=findViewById(R.id.image7);
        image8=findViewById(R.id.image8);
        image9=findViewById(R.id.image9);

        imageArry=new GifImageView[]{image1,image2,image3,image4,image5,image6,image7,image8,image9};
        hideImages();

        score=0;
        new CountDownTimer(15000,1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("Time:"+l/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time off");
                handler.removeCallbacks(runnable);
                for(GifImageView image : imageArry){
                    image.setVisibility(View.INVISIBLE);}
                AlertDialog.Builder alert=new AlertDialog.Builder(Main3Activity.this);
                alert.setTitle("Restrat?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Main3Activity.this,"Back to MainMenu",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                });
                alert.show();

            }
        }.start();

    }
    public void increaseScore(View view){


        score++;

        scoreText.setText("Score:"+score);

    }

    private void hideImages() {
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(GifImageView gifImageView : imageArry){
                    gifImageView.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);
                imageArry[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);

            }
        };
        handler.post(runnable);
    }
}