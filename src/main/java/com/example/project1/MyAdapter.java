package com.example.project1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    ArrayList<Event> eventArrayList;
    Context context;
    View view1;
    ViewHolder viewHolder1;
    RecyclerView recyclerView;
    TextView textView;
    //x

   public MyAdapter(Context context1, ArrayList<Event> SubjectValues1){

        eventArrayList = SubjectValues1;
        context = context1;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewID;
        public TextView textViewEvent;
        public TextView textViewYear;
        public TextView textViewMonth;
        public TextView textViewDay;
        public TextView textViewHour;
        public TextView textViewMinute;
        public RecyclerView recyclerView;
        public LinearLayout linear;


        public ViewHolder(View v){

            super(v);

            textViewID = (TextView)v.findViewById(R.id.textViewRecyclerItemID);
            textViewEvent = (TextView)v.findViewById(R.id.textViewRecyclerEvent);
            textViewYear = (TextView)v.findViewById(R.id.textViewRecyclerYear);
            textViewMonth = (TextView)v.findViewById(R.id.textViewRecyclerMonth);
            textViewDay = (TextView)v.findViewById(R.id.textViewRecyclerDay);
            textViewHour = (TextView)v.findViewById(R.id.textViewRecyclerHour);
            textViewMinute = (TextView)v.findViewById(R.id.textViewRecyclerMinute);
            linear = (LinearLayout)v.findViewById(R.id.linearLayoutRecyler);

        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_layout,parent,false);
        viewHolder1 = new ViewHolder(view1);
        // set size of items here
        viewHolder1.itemView.getLayoutParams().height=600;

        return viewHolder1;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position){

        holder.textViewID.setText(String.valueOf(eventArrayList.get(position).getId_()));
        holder.textViewID.setTextSize(20);
        holder.textViewID.setTypeface(null,Typeface.BOLD_ITALIC);
        holder.textViewEvent.setText("Event " + eventArrayList.get(position).getsEvent_());
        holder.textViewYear.setText("Year " + eventArrayList.get(position).getsYear_());
        holder.textViewMonth.setText("Month " + eventArrayList.get(position).getsMonth_());
        holder.textViewDay.setText("Day " + eventArrayList.get(position).getsDay_());
        holder.textViewHour.setText("Hour " + eventArrayList.get(position).getsHour_());
        holder.textViewMinute.setText("Minute " + eventArrayList.get(position).getsMinute_());

        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "OnClick Called on " + position,Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount(){

        return eventArrayList.size();
    }


}
