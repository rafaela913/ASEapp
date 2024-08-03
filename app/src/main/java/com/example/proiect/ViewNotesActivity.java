package com.example.proiect;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ViewNotesActivity extends AppCompatActivity {

    private ListView listViewNotes;
    private List<String> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        listViewNotes = findViewById(R.id.listViewNotes);
        noteList = new ArrayList<>();

        incarcaNotite();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteList);
        listViewNotes.setAdapter(adapter);
    }

    private void incarcaNotite() {
        String fileName = "notite.txt";
        try (FileInputStream fis = openFileInput(fileName);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            noteList.add(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



