package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "departament", foreignKeys = @ForeignKey(entity = Facultate.class,
        parentColumns = "id",
        childColumns = "facultateId",
        onDelete = ForeignKey.CASCADE))
public class Departament {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "facultateId")
    public int facultateId;

    @ColumnInfo(name = "nume")
    public String nume;

    public Departament() {
    }

    public Departament(int id, int facultateId, String nume) {
        this.id = id;
        this.facultateId = facultateId;
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFacultateId() {
        return facultateId;
    }

    public void setFacultateId(int facultateId) {
        this.facultateId = facultateId;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Departament {" +
                "\n  id = " + id + "," +
                "\n  facultateId = " + facultateId + "," +
                "\n  nume = '" + nume + '\'' +
                "\n}";

    }
}
