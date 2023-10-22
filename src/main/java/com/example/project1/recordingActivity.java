package com.example.project1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.media.metrics.Event;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ArrayList;
import android.os.Handler;

public class recordingActivity extends AppCompatActivity {
    FloatingActionButton floatingActionBabyButton;

    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewManager;

ArrayList<com.example.project1.Event> eventArrayList;
  //  ArrayList<Event> eventArrayList;

    String event;
    int year;
    int month;
    int day;
    int hour;
    int minute;
    String modification;
    private DBAdapter db;

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        floatingActionBabyButton = findViewById(R.id.fabButton);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recyclerViewManager);
        recyclerView.setHasFixedSize(true);
       recyclerViewAdapter = new MyAdapter(getApplicationContext(),
               eventArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
        eventArrayList = new ArrayList<com.example.project1.Event>();db = new DBAdapter(this);
        // get the existing database file or from the assets folder if doesn't exist
        try
        {
            String destPath = "data/data/"+getPackageName()+"/databases";
            File f = new File(destPath);
            if(!f.exists()){
                f.mkdirs();
                //copy db from assets folder
                CopyDB(getBaseContext().getAssets().open("mybabydatabase.db"),
                        new FileOutputStream(destPath + "/myBaby"));
            }
        }catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        db.open();
        Cursor c;
        c = db.getAllEvents();
        if(c.moveToFirst())
        {
            do{
              /*eventArrayList.add(new Event(c.getInt(0),c.getString(1),c.getString(2),
                        c.getString(3),c.getString(4),c.getString(5),
                        c.getString(6)));*/
                // put database item into an ArrayList for the RecyclerView
            } while(c.moveToNext());
        }
        db.close();

       recyclerViewAdapter = new MyAdapter(getApplicationContext(),eventArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences settings =
                getSharedPreferences("datapersistance", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        modification = settings.getString("modification","No");
        if(modification == "yes")
        {

            int size = eventArrayList.size();
            size++;
            event = settings.getString("event","no");
            year = settings.getInt("year",0);
            month = settings.getInt("month",0);
            day = settings.getInt("day",0);
            hour = settings.getInt("hour",0);
            minute = settings.getInt("minute",0);

            editor.putString("modification","No");
            editor.apply();

          /*Event ev = new Event(size,event,Integer.toString(year),Integer.toString(month),
                    Integer.toString(day),Integer.toString(hour),Integer.toString(minute));*/

        /*  eventArrayList.add(ev);*/
            db.open();
            db.addEvent(size,event,year,month,day,hour,minute);
            Toast.makeText(this,"Event " + event + " Added", Toast.LENGTH_LONG).show();
        }

        recyclerViewAdapter.notifyDataSetChanged();
    }

    //outside of onCreate
    // copy database from assets to phone
    // items in the assets folder preserve filename
    public void CopyDB(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        //Copy one byte at a time
        byte [] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) > 0)
        {
            outputStream.write(buffer,0,length);
        }
        inputStream.close();
        outputStream.close();
    }


    public void onReturnToMain(View view) {
        finish();
    }



    public void onResetDatabase(View view) {

        if(eventArrayList.size()>0) {
            db.open();
            db.deleteAllEvents();
            eventArrayList.clear();
            recyclerViewAdapter.notifyDataSetChanged();
        }

    }

    public void onDelete(View view) {
        if(eventArrayList.size()>0)
        {
            db.open();
            db.deleteEvent(eventArrayList.size());
            eventArrayList.remove(eventArrayList.size()-1);
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

    public void onAddButton(View view) {

        Intent intent = new Intent(this,addActivities.class);
        startActivity(intent);
        recyclerViewAdapter.notifyDataSetChanged();

    }
}