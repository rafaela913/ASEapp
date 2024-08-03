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

public class UniversitateAdapter extends RecyclerView.Adapter<UniversitateAdapter.UniversitateViewHolder> {

    private List<Universitate> universitati;

    public UniversitateAdapter(List<Universitate> universitati) {
        this.universitati = universitati;
    }

    @NonNull
    @Override
    public UniversitateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_universitate, parent, false);
        return new UniversitateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UniversitateViewHolder holder, int position) {
        Universitate universitate = universitati.get(position);
        holder.universitateName.setText(universitate.getNume());
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
                intent.putExtra(DetaliiActivity.EXTRA_ID, universitate.getId());
                intent.putExtra(DetaliiActivity.EXTRA_TYPE, "universitate");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return universitati.size();
    }

    public static class UniversitateViewHolder extends RecyclerView.ViewHolder {
        public TextView universitateName;

        public UniversitateViewHolder(View itemView) {
            super(itemView);
            universitateName = itemView.findViewById(R.id.universitate_name);
        }
    }
}
