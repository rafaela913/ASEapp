package com.example.proiect;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FacultateActivity extends AppCompatActivity {

    private RecyclerView facultateRecyclerView;
    private FacultateAdapter facultateAdapter;
    private int universitateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultate);

        universitateId = getIntent().getIntExtra("universitateId", -1);

        facultateRecyclerView = findViewById(R.id.recyclerViewFacultate);
        facultateRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        new LoadFacultatiTask(this).execute(universitateId);
    }

    private class LoadFacultatiTask extends AsyncTask<Integer, Void, List<Facultate>> {
        private Context context;

        public LoadFacultatiTask(Context context) {
            this.context = context;
        }

        @Override
        protected List<Facultate> doInBackground(Integer... params) {
            int universitateId = params[0];
            AppDatabase db = AppDatabase.getInstance(context);
            return db.facultateDao().getFacultatiByUniversitateId(universitateId);
        }

        @Override
        protected void onPostExecute(List<Facultate> facultati) {
            if (facultati != null) {
                facultateAdapter = new FacultateAdapter(facultati);
                facultateRecyclerView.setAdapter(facultateAdapter);
            }
        }
    }
}
