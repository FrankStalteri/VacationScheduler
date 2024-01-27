package com.example.vacationapp.main.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vacationapp.R;
import java.util.ArrayList;
import entities.Vacation;

public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.MyViewHolder> {

    Context context;
    ArrayList<Vacation> vacationList;

    public VacationAdapter(Context context, ArrayList<Vacation> vacationList) {
        this.context = context;
        this.vacationList = vacationList;
    }

    @NonNull
    @Override
    public VacationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout recycler_view_row and give the look to the rows

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_view_row, parent, false);

        return new VacationAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VacationAdapter.MyViewHolder holder, int position) {
        // Assign values to the views

        holder.vacationTxt.setText(vacationList.get(position).getVacationTitle());
    }

    @Override
    public int getItemCount() {
        // How many items i have in the list of vacations

        return vacationList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // Grab the views from the recycler_view_row layout and add them here

        TextView vacationTxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            vacationTxt = itemView.findViewById(R.id.vacationListTxt);
        }
    }
}
