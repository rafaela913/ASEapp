package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.List;

@Entity(tableName = "facultate", foreignKeys = @ForeignKey(entity = Universitate.class,
        parentColumns = "id",
        childColumns = "universitateId",
        onDelete = ForeignKey.CASCADE))
public class Facultate {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "universitateId")
    public int universitateId;

    @ColumnInfo(name = "nume")
    public String nume;

    @Ignore
    private List<Departament> departamente;

    public Facultate() {
    }

    public Facultate(int id, int universitateId, String nume, List<Departament> departamente) {
        this.id = id;
        this.universitateId = universitateId;
        this.nume = nume;
        this.departamente = departamente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUniversitateId() {
        return universitateId;
    }

    public void setUniversitateId(int universitateId) {
        this.universitateId = universitateId;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<Departament> getDepartamente() {
        return departamente;
    }

    public void setDepartamente(List<Departament> departamente) {
        this.departamente = departamente;
    }

    @Override
    public String toString() {
        return "Facultate {" +
                "\n  id = " + id + "," +
                "\n  universitateId = " + universitateId + "," +
                "\n  nume = '" + nume + '\'' + "," +
                "\n  departamente = " + (departamente != null ? departamente : "[]") +
                "\n}";
    }
}