package com.example.proiect;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMain;
    private MainAdapter adapter;
    private List<String> obiective = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerViewMain = findViewById(R.id.recyclerViewMain);
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MainAdapter();
        recyclerViewMain.setAdapter(adapter);

        new LoadDataTask().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_option1) {
            openNotesActivity();
            return true;
        } else if (itemId == R.id.menu_option2) {
            openViewNotesActivity();
            return true;
        } else if (itemId == R.id.menu_option3) {
            showAboutDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void openNotesActivity() {
        Intent intent = new Intent(MainActivity.this, NotesActivity.class);
        intent.putStringArrayListExtra("obiective", new ArrayList<>(obiective));
        startActivity(intent);
    }

    private void openViewNotesActivity() {
        Intent intent = new Intent(MainActivity.this, ViewNotesActivity.class);
        startActivity(intent);
    }

    private void showAboutDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inflater.inflate(R.layout.dialog_about, null))
                .setPositiveButton("OK", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void populateDatabase(List<Universitate> universitati) {
        AppDatabase db = AppDatabase.getInstance(this);
        for (Universitate universitate : universitati) {
            db.universitateDao().insert(universitate);
            for (Facultate facultate : universitate.getFacultati()) {
                db.facultateDao().insert(facultate);
                for (Departament departament : facultate.getDepartamente()) {
                    db.departamentDao().insert(departament);
                }
            }
        }
    }



    private class LoadDataTask extends AsyncTask<Void, Void, List<Object>> {

       @Override
       protected List<Object> doInBackground(Void... voids) {
           String jsonContent = JsonUtil.getJsonFromResources(MainActivity.this, R.raw.data);
           List<Universitate> universitati = JsonUtil.parseJsonContent(jsonContent);

           populateDatabase(universitati);

           AppDatabase db = AppDatabase.getInstance(MainActivity.this);
           List<Object> items = new ArrayList<>();
           List<Universitate> dbUniversitati = db.universitateDao().getAll();
           for (Universitate universitate : dbUniversitati) {
               items.add(universitate);
               obiective.add(universitate.nume);
               List<Facultate> facultati = db.facultateDao().getFacultatiByUniversitateId(universitate.getId());
               for (Facultate facultate : facultati) {
                   items.add(facultate);
                   obiective.add(facultate.getNume());
                   List<Departament> departamente = db.departamentDao().getDepartamenteByFacultateId(facultate.getId());
                   for (Departament departament : departamente) {
                       items.add(departament);
                       obiective.add(departament.getNume());
                   }
               }
           }
           return items;


       }

        @Override
        protected void onPostExecute(List<Object> items) {
            adapter.setItems(items);
        }
    }
}
