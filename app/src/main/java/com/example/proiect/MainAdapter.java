package com.example.proiect;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_UNIVERSITATE = 0;
    private static final int TYPE_FACULTATE = 1;
    private static final int TYPE_DEPARTAMENT = 2;

    private List<Object> items = new ArrayList<>();

    public void setItems(List<Object> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Universitate) {
            return TYPE_UNIVERSITATE;
        } else if (items.get(position) instanceof Facultate) {
            return TYPE_FACULTATE;
        } else if (items.get(position) instanceof Departament) {
            return TYPE_DEPARTAMENT;
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_UNIVERSITATE) {
            View view = inflater.inflate(R.layout.item_universitate, parent, false);
            return new UniversitateViewHolder(view);
        } else if (viewType == TYPE_FACULTATE) {
            View view = inflater.inflate(R.layout.item_facultate, parent, false);
            return new FacultateViewHolder(view);
        } else if (viewType == TYPE_DEPARTAMENT) {
            View view = inflater.inflate(R.layout.item_departament, parent, false);
            return new DepartamentViewHolder(view);
        }
        throw new IllegalArgumentException("Unknown view type: " + viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UniversitateViewHolder) {
            Universitate universitate = (Universitate) items.get(position);
            ((UniversitateViewHolder) holder).bind(universitate);
        } else if (holder instanceof FacultateViewHolder) {
            Facultate facultate = (Facultate) items.get(position);
            ((FacultateViewHolder) holder).bind(facultate);
        } else if (holder instanceof DepartamentViewHolder) {
            Departament departament = (Departament) items.get(position);
            ((DepartamentViewHolder) holder).bind(departament);
        }

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#00D2FF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#9874a7"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, DetaliiActivity.class);
                if (holder instanceof UniversitateViewHolder) {
                    Universitate universitate = (Universitate) items.get(position);
                    intent.putExtra(DetaliiActivity.EXTRA_ID, universitate.getId());
                    intent.putExtra(DetaliiActivity.EXTRA_TYPE, "universitate");
                } else if (holder instanceof FacultateViewHolder) {
                    Facultate facultate = (Facultate) items.get(position);
                    intent.putExtra(DetaliiActivity.EXTRA_ID, facultate.getId());
                    intent.putExtra(DetaliiActivity.EXTRA_TYPE, "facultate");
                } else if (holder instanceof DepartamentViewHolder) {
                    Departament departament = (Departament) items.get(position);
                    intent.putExtra(DetaliiActivity.EXTRA_ID, departament.getId());
                    intent.putExtra(DetaliiActivity.EXTRA_TYPE, "departament");
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class UniversitateViewHolder extends RecyclerView.ViewHolder {
        private TextView universitateName;

        public UniversitateViewHolder(View itemView) {
            super(itemView);
            universitateName = itemView.findViewById(R.id.universitate_name);
        }

        public void bind(Universitate universitate) {
            universitateName.setText(universitate.getNume());
        }
    }

    static class FacultateViewHolder extends RecyclerView.ViewHolder {
        private TextView facultateName;

        public FacultateViewHolder(View itemView) {
            super(itemView);
            facultateName = itemView.findViewById(R.id.facultate_name);
        }

        public void bind(Facultate facultate) {
            facultateName.setText(facultate.getNume());
        }
    }

    static class DepartamentViewHolder extends RecyclerView.ViewHolder {
        private TextView departamentName;

        public DepartamentViewHolder(View itemView) {
            super(itemView);
            departamentName = itemView.findViewById(R.id.departament_name);
        }

        public void bind(Departament departament) {
            departamentName.setText(departament.getNume());
        }
    }
}
