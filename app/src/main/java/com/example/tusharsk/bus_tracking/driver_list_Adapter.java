package com.example.tusharsk.bus_tracking;

/**
 * Created by tusharsk on 18/4/18.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;


public class driver_list_Adapter extends RecyclerView.Adapter<driver_list_Adapter.NUMBERVIEWHOLDER>{


    String bus_no[];
    String bus_position[];
    String time[];
    String driver_name[];
    String driver_phone_number[];
    String no_of_students[];
    String teacher_present[];
    private Context mcontext;

    private driver_list_AdapterOnClickHandler mClickHandler;
    public interface driver_list_AdapterOnClickHandler
    {
        void onClick(int x);
    }
    private Context context;
    public driver_list_Adapter(driver_list_AdapterOnClickHandler clickHandler)
    {

        mClickHandler=clickHandler;

    }




    @Override
    public driver_list_Adapter.NUMBERVIEWHOLDER onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_driver_list,parent,false);
        return new driver_list_Adapter.NUMBERVIEWHOLDER(view);

    }

    @Override
    public void onBindViewHolder(driver_list_Adapter.NUMBERVIEWHOLDER holder, int position) {



        holder.name.setText(driver_name[position]);
        holder.phone.setText(driver_phone_number[position]);
        holder.cab_n.setText(bus_no[position]);
        holder.cab_p.setText(bus_position[position]);
        holder.teacher.setText(teacher_present[position]);
        holder.no_of_student.setText(no_of_students[position]);
        holder.time.setText(time[position]);}

    @Override
    public int getItemCount() {

        if(bus_no==null)
            return 0;
        else
            return bus_no.length;
    }



    public class NUMBERVIEWHOLDER extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name;
        TextView phone;
        TextView cab_n;
        TextView cab_p;
        TextView teacher;
        TextView no_of_student;
        TextView time;
        public NUMBERVIEWHOLDER(View view)

        {

            super(view);
            name=(TextView)view.findViewById(R.id.name_12);
            phone=(TextView)view.findViewById(R.id.driver_12);
            cab_n=(TextView) view.findViewById(R.id.bus_no_12);
            cab_p=(TextView)view.findViewById(R.id.position_12);
            teacher=(TextView)view.findViewById(R.id.teacher_12);
            no_of_student=(TextView)view.findViewById(R.id.student_no_12);
            time=(TextView) view.findViewById(R.id.time_12);
           // itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            mClickHandler.onClick(getAdapterPosition());

        }
    }
    public void swapCursor(Context context,String bus_no[],String bus_position[], String time[],String driver_name[],String driver_phone_number[],String no_of_students[],String teacher_present[]) {
        // Always close the previous mCursor first

        if (bus_no != null) {
            // Force the RecyclerView to refresh
            this.bus_no=bus_no;
            this.bus_position=bus_position;
            this.time=time;
            this.driver_name=driver_name;
            this.driver_phone_number=driver_phone_number;
            this.no_of_students=no_of_students;
            this.teacher_present=teacher_present;
            this.context=context;
            this.notifyDataSetChanged();
        }
    }







}