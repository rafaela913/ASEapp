package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.List;

@Entity(tableName = "universitate")
public class Universitate {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "nume")
    public String nume;

    @Ignore
    private List<Facultate> facultati;

    public Universitate() {
    }

    public Universitate(int id, String nume, List<Facultate> facultati) {
        this.id = id;
        this.nume = nume;
        this.facultati = facultati;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<Facultate> getFacultati() {
        return facultati;
    }

    public void setFacultati(List<Facultate> facultati) {
        this.facultati = facultati;
    }

    @Override
    public String toString() {
        return "Universitate {" +
                "\n  id = " + id + "," +
                "\n  nume = '" + nume + '\'' + "," +
                "\n  facultati = " + (facultati != null ? facultati : "[]") +
                "\n}";
    }
}

