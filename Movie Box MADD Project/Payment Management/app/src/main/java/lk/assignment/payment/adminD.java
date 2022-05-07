package lk.assignment.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import DatabaseHelper.DataBaseHelper;

public class adminD extends AppCompatActivity {

    DataBaseHelper myDb;
    EditText seatNo, ticketFull, ticketHalf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_d);
        myDb = new DataBaseHelper(this);

        seatNo = findViewById(R.id.seatNo);
        ticketFull = findViewById(R.id.ticketFull);
        ticketHalf = findViewById(R.id.ticketHalf);
    }

    public void update(View v){

        int fullTicketPrice = 200;
        int halfTicketPrice = 100;

        int fullTicketCount = Integer.parseInt(ticketFull.getText().toString());
        int halfTicketCount = Integer.parseInt(ticketHalf.getText().toString());
        int total = (fullTicketCount*fullTicketPrice)+(halfTicketCount*halfTicketPrice);
        String setTotal = String.valueOf(total);

        boolean isUpdate = myDb.updateData(seatNo.getText().toString(), ticketFull.getText().toString(), ticketHalf.getText().toString(), setTotal);
        if (isUpdate == true)
            Toast.makeText(adminD.this,"Updated",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(adminD.this,"Not Updated",Toast.LENGTH_LONG).show();
    }

    public void delete(View v){

        Integer isDeleted = myDb.deleteData(seatNo.getText().toString());

        if (isDeleted > 0)
            Toast.makeText(adminD.this,"Deleted",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(adminD.this,"Not Deleted",Toast.LENGTH_LONG).show();
    }
}