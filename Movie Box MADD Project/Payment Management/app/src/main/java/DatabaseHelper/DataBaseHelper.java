package DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "payment.db";

    public static final String TABLE_NAME = "payment_table";
    public static final String COL_2 = "MOVIE_ID";
    public static final String COL_3 = "SEAT_NUMBER";
    public static final String COL_4 = "TICKET_FULL";
    public static final String COL_5 = "TICKET_HALF";
    public static final String COL_6 = "TOTAL_AMOUNT";

    public static final String TABLE_NAME_TWO = "user_details_table";
    public static final String COL_1_Two = "SEAT_NUMBER";
    public static final String COL_2_Two = "NAME";
    public static final String COL_3_Two = "ADDRESS";
    public static final String COL_4_Two = "EMAIL";
    public static final String COL_5_Two = "MOBILE";

    public static final String TABLE_NAME_THREE = "card_details_table";
    public static final String COL_1_Three = "SEAT_NUMBER";
    public static final String COL_2_Three = "CARD_NUMBER";
    public static final String COL_3_Three = "HOLDER_NAME";
    public static final String COL_4_Three = "EXP_DATE";
    public static final String COL_5_Three = "CVV";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, MOVIE_ID INTEGER, SEAT_NUMBER TEXT, TICKET_FULL INTEGER, TICKET_HALF INTEGER, TOTAL_AMOUNT INTEGER)");
        db.execSQL("Create table "+TABLE_NAME_TWO+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, SEAT_NUMBER TEXT, NAME TEXT, ADDRESS TEXT, EMAIL TEXT, MOBILE TEXT)");
        db.execSQL("Create table "+TABLE_NAME_THREE+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, SEAT_NUMBER TEXT, CARD_NUMBER TEXT, HOLDER_NAME TEXT, EXP_DATE TEXT, CVV TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_TWO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_THREE);
        onCreate(db);
    }

    public boolean insertPaymentDetails(int MovieID, String SeatNumber, int TicketFull, int TicketHalf, int TotalAmount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, MovieID);
        contentValues.put(COL_3, SeatNumber);
        contentValues.put(COL_4, TicketFull);
        contentValues.put(COL_5, TicketHalf);
        contentValues.put(COL_6, TotalAmount);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertUserDetails(String SEAT_NUMBER, String NAME, String ADDRESS, String EMAIL, String MOBILE){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1_Two, SEAT_NUMBER);
        contentValues.put(COL_2_Two, NAME);
        contentValues.put(COL_3_Two, ADDRESS);
        contentValues.put(COL_4_Two, EMAIL);
        contentValues.put(COL_5_Two, MOBILE);
        long result = db.insert(TABLE_NAME_TWO, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean insertCardDetails(String SEAT_NUMBER, String CARD_NUMBER, String HOLDER_NAME, String EXP_DATE, String CVV){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1_Three, SEAT_NUMBER);
        contentValues.put(COL_2_Three, CARD_NUMBER);
        contentValues.put(COL_3_Three, HOLDER_NAME);
        contentValues.put(COL_4_Three, EXP_DATE);
        contentValues.put(COL_5_Three, CVV);
        long result = db.insert(TABLE_NAME_THREE, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getTicketDetails(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME_TWO,null);
        return res;
    }

    public boolean updateData(String seatNo, String fullTicket, String halfTicket, String Total ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_4, fullTicket);
        contentValues.put(COL_5, halfTicket);
        contentValues.put(COL_6, Total);
        db.update(TABLE_NAME, contentValues, "SEAT_NUMBER = ?", new String[]{seatNo});
        return true;
    }

    public Integer deleteData(String seatNo){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "SEAT_NUMBER = ?", new String[]{seatNo});
        db.delete(TABLE_NAME_TWO, "SEAT_NUMBER = ?", new String[]{seatNo});
        db.delete(TABLE_NAME_THREE, "SEAT_NUMBER = ?", new String[]{seatNo});
        return 1;
    }
}
