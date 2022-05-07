package lk.assignment.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DatabaseHelper.DataBaseHelper;

public class PaymentDetails extends AppCompatActivity {

    String seatNumber="";
    DataBaseHelper myDb;
    EditText getUserName, getUserAddress, getUserEmail, getUserMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            seatNumber = extras.getString("seatNo");
        }

        myDb = new DataBaseHelper(this);
        getUserName = findViewById(R.id.userName);
        getUserAddress = findViewById(R.id.userAddress);
        getUserEmail = findViewById(R.id.userEmail);
        getUserMobile = findViewById(R.id.userMobile);
    }

    public void btnSave(View v){

        String uName = getUserName.getText().toString();
        String uAddress = getUserAddress.getText().toString();
        String uEmail = getUserEmail.getText().toString();
        String uMobile = getUserMobile.getText().toString();

        boolean inserted = myDb.insertUserDetails(seatNumber, uName, uAddress, uEmail, uMobile);
        if(inserted==true)
            Toast.makeText(PaymentDetails.this,"Saved...",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(PaymentDetails.this,"Not Saved...",Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, CardDetails.class);
        i.putExtra("seatNo",seatNumber);
        startActivity(i);
    }
}