package com.example.vacationapp.main.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vacationapp.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import entities.Vacation;

public class VacationAdapter extends RecyclerView.Adapter<VacationAdapter.VacationViewHolder> {

    private final LayoutInflater mInflater;
    private final Context context;
    private List<Vacation> vacationList;

    class VacationViewHolder extends RecyclerView.ViewHolder {

        private final TextView vacationListItem;

        private VacationViewHolder(View itemView) {
            super(itemView);
            vacationListItem = itemView.findViewById(R.id.vacation_list_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                    String dateFormat = "MM/dd/yy"; //In which you need put here
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

                    int position = getAdapterPosition();
                    final Vacation current = vacationList.get(position);

                    Intent intent = new Intent(context, VacationDetails.class);

                    intent.putExtra("vacationId", current.getVacationId());
                    intent.putExtra("vacationTitle", current.getVacationTitle());
                    intent.putExtra("vacationLodging", current.getVacationLodging());
                    intent.putExtra("vacationStartDate", current.getVacationStartDate());
                    intent.putExtra("vacationEndDate", current.getVacationEndDate());
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }

            });
        }
    }

    public VacationAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public VacationAdapter.VacationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.activity_vacation_list_item, parent,false);
        return new VacationViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull VacationAdapter.VacationViewHolder holder,int position) {

        if(vacationList != null) {
            Vacation current = vacationList.get(position);
            String title =  current.getVacationTitle();
            holder.vacationListItem.setText(title);

        }else{
            holder.vacationListItem.setText("No Title");
        }
    }

    @Override
    public int getItemCount(){
        if(vacationList != null)
            return vacationList.size();
        else return 0;
    }

    public void setVacations(List<Vacation> vacations){
        vacationList = vacations;
        notifyDataSetChanged();
    }
}
