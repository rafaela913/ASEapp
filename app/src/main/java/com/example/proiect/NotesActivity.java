package com.example.proiect;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private Spinner spinnerObiectiv;
    private EditText editTextNotite;
    private Button buttonSalveaza;
    private List<String> obiective;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        spinnerObiectiv = findViewById(R.id.spinnerObiectiv);
        editTextNotite = findViewById(R.id.editTextNotite);
        buttonSalveaza = findViewById(R.id.buttonSalveaza);

        obiective = getIntent().getStringArrayListExtra("obiective");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, obiective);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerObiectiv.setAdapter(adapter);

        buttonSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String obiectiv = spinnerObiectiv.getSelectedItem().toString();
                String notite = editTextNotite.getText().toString();
                saveNotesToFile(obiectiv, notite);
            }
        });
    }

    private void saveNotesToFile(String obiectiv, String notite) {
        String fileName = "notite.txt";
        String content = "Obiectiv: " + obiectiv + "\nNotita: " + notite + "\n\n";

        try (FileOutputStream fos = openFileOutput(fileName, MODE_APPEND)) {
            fos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
