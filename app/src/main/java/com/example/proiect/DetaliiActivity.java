package com.example.proiect;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetaliiActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.proiect.EXTRA_ID";
    public static final String EXTRA_TYPE = "com.example.proiect.EXTRA_TYPE";

    private TextView textViewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii);

        textViewDetails = findViewById(R.id.textViewDetails);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        String type = getIntent().getStringExtra(EXTRA_TYPE);

        if (id != -1 && type != null) {
            new LoadDetailsTask(this, type, id).execute();
        }
    }

    private static class LoadDetailsTask extends AsyncTask<Void, Void, String> {
        private Context context;
        private String type;
        private int id;

        public LoadDetailsTask(Context context, String type, int id) {
            this.context = context;
            this.type = type;
            this.id = id;
        }


        @Override
        protected String doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(context);

            if ("universitate".equals(type)) {
                Universitate universitate = db.universitateDao().getUniversitateById(id);
                if (universitate != null) {
                    return universitate.toString();
                } else {
                    return "Universitatea nu a fost gasita";
                }
            } else if ("facultate".equals(type)) {
                Facultate facultate = db.facultateDao().getFacultateById(id);
                if (facultate != null) {
                    return facultate.toString();
                } else {
                    return "Facultatea nu a fost gasita";
                }
            } else if ("departament".equals(type)) {
                Departament departament = db.departamentDao().getDepartamentById(id);
                if (departament != null) {
                    return departament.toString();
                } else {
                    return "Departamentul nu a fost gasit";
                }
            } else {
                return "Tip invalid";
            }
        }


        @Override
        protected void onPostExecute(String details) {
            ((DetaliiActivity) context).textViewDetails.setText(details);
        }
    }
}
