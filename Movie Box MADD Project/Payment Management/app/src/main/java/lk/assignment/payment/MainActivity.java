package lk.assignment.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DatabaseHelper.DataBaseHelper;

public class MainActivity extends AppCompatActivity{
    DataBaseHelper myDb;
    EditText seatNo, ticketFull, ticketHalf;
    TextView totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DataBaseHelper(this);

        seatNo = findViewById(R.id.seatNo);
        ticketFull = findViewById(R.id.ticketFull);
        ticketHalf = findViewById(R.id.ticketHalf);
        totalAmount = findViewById(R.id.totalLbl);

    }

    public void btngetTotal(View v){
        int fullTicketPrice = 200;
        int halfTicketPrice = 100;

        int fullTicketCount = Integer.parseInt(ticketFull.getText().toString());
        int halfTicketCount = Integer.parseInt(ticketHalf.getText().toString());

        int total = (fullTicketCount*fullTicketPrice)+(halfTicketCount*halfTicketPrice);
        totalAmount.setText(String.valueOf(total));

    }

    public void btnSave(View v){
        int movieID = 1;
        String seatNumber = seatNo.getText().toString();
        int fullTicketCount = Integer.parseInt(ticketFull.getText().toString());
        int halfTicketCount = Integer.parseInt(ticketHalf.getText().toString());
        int total = Integer.parseInt(totalAmount.getText().toString());

        boolean inserted = myDb.insertPaymentDetails(movieID, seatNumber, fullTicketCount, halfTicketCount, total);
            if(inserted==true)
                Toast.makeText(MainActivity.this,"Saved...",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this,"Not Saved...",Toast.LENGTH_LONG).show();

         Intent i = new Intent(this, PaymentDetails.class);
         i.putExtra("seatNo",seatNumber);
         startActivity(i);
    }

}