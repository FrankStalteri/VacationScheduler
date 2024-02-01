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
import entities.Excursion;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ExcursionAdapter extends RecyclerView.Adapter<ExcursionAdapter.ExcursionViewHolder> {
    class ExcursionViewHolder extends RecyclerView.ViewHolder{
        private final TextView excursionListItem;

        private ExcursionViewHolder(View itemView) {
            super(itemView);
            excursionListItem = itemView.findViewById(R.id.excursion_list_item);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String dateFormat = "MM/dd/yy";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);

                    int position = getAdapterPosition();
                    final Excursion excursion = excursionList.get(position);

                    Intent intent = new Intent(context, ExcursionDetails.class);

                    intent.putExtra("vacationId", excursion.getVacationId());
                    intent.putExtra("excursionId", excursion.getExcursionId());
                    intent.putExtra("excursionTitle", excursion.getExcursionTitle());
                    intent.putExtra("excursionStartDate", excursion.getExcursionStartDate());
                    context.startActivity(intent);
                }

            });
        }
    }

    private final LayoutInflater inflater;

    private final Context context;

    private List<Excursion> excursionList;

    public ExcursionAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ExcursionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.activity_excursion_list_item, parent,false);
        return new ExcursionViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull ExcursionViewHolder holder,int position) {

        if(excursionList != null) {
            Excursion current = excursionList.get(position);
            String title =  current.getExcursionTitle();
            holder.excursionListItem.setText(title);

        }else{
            holder.excursionListItem.setText("No Title");
        }
    }
    @Override
    public int getItemCount(){
        if(excursionList != null)
            return excursionList.size();
        else return 0;
    }

    public void setExcursions (List<Excursion> excursions){
        excursionList = excursions;
        notifyDataSetChanged();
    }
}
