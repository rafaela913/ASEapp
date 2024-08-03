package com.example.proiect;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DepartamentActivity extends AppCompatActivity {

    private RecyclerView departamentRecyclerView;
    private DepartamentAdapter departamentAdapter;
    private int facultateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departament);

        facultateId = getIntent().getIntExtra("facultateId", -1);

        departamentRecyclerView = findViewById(R.id.recyclerViewDepartament);
        departamentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        new LoadDepartamenteTask(this).execute(facultateId);
    }

    private class LoadDepartamenteTask extends AsyncTask<Integer, Void, List<Departament>> {
        private Context context;

        public LoadDepartamenteTask(Context context) {
            this.context = context;
        }

        @Override
        protected List<Departament> doInBackground(Integer... params) {
            int facultateId = params[0];
            AppDatabase db = AppDatabase.getInstance(context);
            return db.departamentDao().getDepartamenteByFacultateId(facultateId);
        }

        @Override
        protected void onPostExecute(List<Departament> departamente) {
            if (departamente != null) {
                departamentAdapter = new DepartamentAdapter(departamente);
                departamentRecyclerView.setAdapter(departamentAdapter);
            }
        }
    }
}
