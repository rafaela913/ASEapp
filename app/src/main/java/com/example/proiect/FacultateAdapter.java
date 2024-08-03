package com.example.proiect;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FacultateAdapter extends RecyclerView.Adapter<FacultateAdapter.FacultateViewHolder> {

    private List<Facultate> facultati;

    public FacultateAdapter(List<Facultate> facultati) {
        this.facultati = facultati;
    }

    @NonNull
    @Override
    public FacultateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_facultate, parent, false);
        return new FacultateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultateViewHolder holder, int position) {
        Facultate facultate = facultati.get(position);
        holder.facultateName.setText(facultate.getNume());
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(0xFF00D2FF);
        } else {
            holder.itemView.setBackgroundColor(0xFF9874a7);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetaliiActivity.class);
                intent.putExtra(DetaliiActivity.EXTRA_ID, facultate.getId());
                intent.putExtra(DetaliiActivity.EXTRA_TYPE, "facultate");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return facultati.size();
    }

    public static class FacultateViewHolder extends RecyclerView.ViewHolder {
        public TextView facultateName;

        public FacultateViewHolder(View itemView) {
            super(itemView);
            facultateName = itemView.findViewById(R.id.facultate_name);
        }
    }
}

