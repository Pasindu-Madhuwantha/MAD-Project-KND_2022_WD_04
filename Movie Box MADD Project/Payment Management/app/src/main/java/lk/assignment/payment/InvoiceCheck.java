package lk.assignment.payment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import DatabaseHelper.DataBaseHelper;

public class InvoiceCheck extends AppCompatActivity {

    DataBaseHelper myDb;
    TextView getDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_check);

        getDetails = findViewById(R.id.SetseatNo);
        myDb = new DataBaseHelper(this);
        getSeatDetails();
    }

    public void getSeatDetails(){


         Cursor res = myDb.getTicketDetails();
        if(res.getCount() == 0){
            Toast.makeText(InvoiceCheck.this,"Not Saved...",Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Seat Number : "+res.getString(1)+"\n");
            buffer.append("Name : "+res.getString(2)+"\n");
            buffer.append("Mobile Number : "+res.getString(5)+"\n\n");
        }
        getDetails.setText(buffer.toString());
    }
}