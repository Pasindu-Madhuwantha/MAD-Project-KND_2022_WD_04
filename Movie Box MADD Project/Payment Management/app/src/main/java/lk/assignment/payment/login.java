package lk.assignment.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void user(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void admin(View v){
        Intent i = new Intent(this, adminD.class);
        startActivity(i);
    }
}