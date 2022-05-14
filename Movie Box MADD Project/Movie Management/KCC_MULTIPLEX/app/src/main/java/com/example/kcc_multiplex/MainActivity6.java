package com.example.kcc_multiplex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity6 extends AppCompatActivity {

    Button add,mAdd,cAdd,mList;
    EditText moviename,movietype,moviehours,moviestatus,moviedescription;
    ImageView movieimage,coverimage;
    ActivityResultLauncher<String> mGetcontent;
    ActivityResultLauncher<String> mGetcontent1;

    final int REQUEST_CODE_GALLERY=999;


    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        init();
        sqLiteHelper = new SQLiteHelper(this,"MOVIEBOX.sqlite",null,1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS MOVIES (id INTEGER PRIMARY KEY AUTOINCREMENT,MovieName VARCHAR,MovieType VARCHAR,MovieHours VARCHAR,MoiveStatus VARCHAR,MovieImage BLOB,CoverImage BLOB,Description VARCHAR)");




        //Get the movie image
        mGetcontent =registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {

                        try {
                            InputStream inputStream = getContentResolver().openInputStream(result);

                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            movieimage.setImageBitmap(bitmap);


                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }

                });

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mGetcontent.launch("image/*");

            }
        });

        //get the cover image

        mGetcontent1 =registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {

                try {
                    InputStream inputStream = getContentResolver().openInputStream(result);

                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    coverimage.setImageBitmap(bitmap);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        });

        cAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetcontent1.launch("image/*");

            }
        });

    //Data pass on add button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    sqLiteHelper.insertData(

                            moviename.getText().toString().trim(),
                            movietype.getText().toString().trim(),
                            moviehours.getText().toString().trim(),
                            moviestatus.getText().toString().trim(),
                            movieimageToByte(movieimage),
                            movieimageToByte(coverimage),
                            moviedescription.getText().toString().trim()
                    );

                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    moviename.setText("");
                    movietype.setText("");
                    moviehours.setText("");
                    moviestatus.setText("");
                    movieimage.setImageResource(R.mipmap.ic_launcher);
                    coverimage.setImageResource(R.mipmap.ic_launcher);
                    moviedescription.setText("");
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        //movie list
        mList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity6.this,MovieList.class);
                startActivity(intent);
            }
        });



    }

        //convert to byte
    public static byte[] movieimageToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    private void init(){
        add=findViewById(R.id.buttonUpdate);
        mAdd=findViewById(R.id.mAdd);
        cAdd=findViewById(R.id.cAdd);
        moviename=findViewById(R.id.moviename);
        movietype=findViewById(R.id.movietype);
        moviehours=findViewById(R.id.moviehours);
        moviestatus=findViewById(R.id.moviestatus);
        moviedescription=findViewById(R.id.moviedescription);
        movieimage=findViewById(R.id.movieimage1);
        coverimage=findViewById(R.id.coverimage1);
        mList=findViewById(R.id.MovieList);



    }





}