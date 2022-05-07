package lk.assignment.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import DatabaseHelper.DataBaseHelper;

public class CardDetails extends AppCompatActivity {

    String seatNumber="";
    DataBaseHelper myDb;
    EditText getCardNo, getHolderName, getExpDate, getCvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            seatNumber = extras.getString("seatNo");
        }

        myDb = new DataBaseHelper(this);
        getCardNo = findViewById(R.id.cardNumber);
        getHolderName = findViewById(R.id.holderName);
        getExpDate = findViewById(R.id.expDate);
        getCvv = findViewById(R.id.cvv);
    }

    public void btnSave(View v){

        String cardNo = getCardNo.getText().toString();
        String holderName = getHolderName.getText().toString();
        String expDate = getExpDate.getText().toString();
        String cvv = getCvv.getText().toString();

        boolean inserted = myDb.insertCardDetails(seatNumber, cardNo, holderName, expDate, cvv);
        if(inserted==true)
            Toast.makeText(CardDetails.this,"Saved...",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(CardDetails.this,"Not Saved...",Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, InvoiceCheck.class);
        startActivity(i);
    }
}