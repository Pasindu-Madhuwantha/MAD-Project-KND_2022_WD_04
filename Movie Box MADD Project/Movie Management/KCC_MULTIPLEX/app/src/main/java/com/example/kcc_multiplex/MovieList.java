package com.example.kcc_multiplex;

import static com.example.kcc_multiplex.MainActivity6.movieimageToByte;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MovieList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Movies> list;
    MoiveListAdapter adapter=null;

    ActivityResultLauncher<String> mGetcontent3;
    ActivityResultLauncher<String> mGetcontent4;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);


        //Get the movie image
        mGetcontent3 =registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {

                try {
                    InputStream inputStream = getContentResolver().openInputStream(result);

                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    movieimage1.setImageBitmap(bitmap);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        });

        //get the cover image

        mGetcontent4 =registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {

                try {
                    InputStream inputStream = getContentResolver().openInputStream(result);

                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    coverimage1.setImageBitmap(bitmap);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        });


        gridView = (GridView) findViewById(R.id.gridView);
        list =new ArrayList<>();
        adapter= new MoiveListAdapter(this,R.layout.moiveview,list);
        gridView.setAdapter(adapter);

        // get data from sqlite

        Cursor cursor =MainActivity6.sqLiteHelper.getData("SELECT id,MovieName,MovieImage FROM MOVIES");
        list.clear();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String moviename=cursor.getString(1);
            byte[] movieimage=cursor.getBlob(2);

            list.add(new Movies(id,moviename,movieimage));

        }

        adapter.notifyDataSetChanged();

        //delete or update toast
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MovieList.this, "Long Click On Item To Update or Delete", Toast.LENGTH_SHORT).show();
            }
        });


        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position , long l) {

                CharSequence[] items={"Update","Delete"};
                AlertDialog.Builder dialog=new AlertDialog.Builder(MovieList.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {


                        if(item==0){
                            //update
                            Cursor c= MainActivity6.sqLiteHelper.getData("SELECT id FROM MOVIES");
                            ArrayList<Integer> arrID= new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));

                            }
                            //dialog update
                            showDialogUpdate(MovieList.this, arrID.get(position));


                        }

                        else{
                            //delete
                            Cursor c= MainActivity6.sqLiteHelper.getData("SELECT id FROM MOVIES");
                            ArrayList<Integer> arrID= new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));

                            }

                            showDialogDelete(arrID.get(position));

                        }
                    }
                });
                dialog.show();
                return true;
            }
        });


    }

    ImageView movieimage1;
    ImageView coverimage1;
    String moviename1;


    private  void showDialogUpdate(Activity activity,int position){
        Dialog dialog =new Dialog(activity);
        dialog.setContentView(R.layout.update_movies_activity);
        dialog.setTitle("Update");



         EditText moviename=(EditText) dialog.findViewById(R.id.moviename);
        final EditText movietype=(EditText) dialog.findViewById(R.id.movietype);
        final EditText moviehours=(EditText) dialog.findViewById(R.id.moviehours);
        final EditText moviestatus=(EditText) dialog.findViewById(R.id.moviestatus);
        movieimage1=(ImageView) dialog.findViewById(R.id.movieimage1);
        coverimage1=(ImageView) dialog.findViewById(R.id.coverimage1);
        final EditText description=(EditText) dialog.findViewById(R.id.moviedescription);
        Button btnUpdate=(Button) dialog.findViewById(R.id.buttonUpdate);


        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 1);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.8);
        dialog.getWindow().setLayout(width, height);
        dialog.show();




        movieimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetcontent3.launch("image/*");

            }
        });




        coverimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetcontent4.launch("image/*");

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    MainActivity6.sqLiteHelper.updateData(
                            moviename.getText().toString().trim(),
                            movietype.getText().toString().trim(),
                            moviehours.getText().toString().trim(),
                            moviestatus.getText().toString().trim(),
                            movieimageToByte(movieimage1),
                            movieimageToByte(coverimage1),
                            description.getText().toString().trim(),
                            position


                    );
                    dialog.dismiss();
                    Toast.makeText(activity, "Update Successfully!!!", Toast.LENGTH_SHORT).show();

                }

                catch(Exception error){
                    Log.e("Update Error",error.getMessage());
                }
                updateMovieList();

            }
        });

    }


    private void showDialogDelete(final int idMovies){
        AlertDialog.Builder dialogDelete =new AlertDialog.Builder(MovieList.this);

        dialogDelete.setTitle("Warning!");
        dialogDelete.setMessage("Are You Sure want to delete this?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    MainActivity6.sqLiteHelper.Deletedata(idMovies);
                    Toast.makeText(MovieList.this, "Deleted Successfully!!!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Log.e("error",e.getMessage());
                }
                updateMovieList();


            }
        });
        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();

            }
        });
        dialogDelete.show();
    }




    private  void updateMovieList(){
        // get data from sqlite

        Cursor cursor =MainActivity6.sqLiteHelper.getData("SELECT id,MovieName,MovieImage FROM MOVIES");
        list.clear();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String moviename=cursor.getString(1);
            byte[] movieimage=cursor.getBlob(2);

            list.add(new Movies(id,moviename,movieimage));

        }

        adapter.notifyDataSetChanged();


    }


}
