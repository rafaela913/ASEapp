package com.example.proiect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DepartamentAdapter extends RecyclerView.Adapter<DepartamentAdapter.DepartamentViewHolder> {

    private List<Departament> departamente;

    public DepartamentAdapter(List<Departament> departamente) {
        this.departamente = departamente;
    }

    @NonNull
    @Override
    public DepartamentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_departament, parent, false);
        return new DepartamentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartamentViewHolder holder, int position) {
        Departament departament = departamente.get(position);
        holder.departamentName.setText(departament.getNume());
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(0xFF00D2FF);
        } else {
            holder.itemView.setBackgroundColor(0xFF9874a7);
        }
    }

    @Override
    public int getItemCount() {
        return departamente.size();
    }

    public static class DepartamentViewHolder extends RecyclerView.ViewHolder {
        public TextView departamentName;

        public DepartamentViewHolder(View itemView) {
            super(itemView);
            departamentName = itemView.findViewById(R.id.departament_name);
        }
    }
}