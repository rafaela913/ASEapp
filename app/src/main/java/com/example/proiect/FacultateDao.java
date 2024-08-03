package com.example.proiect;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FacultateDao {

    @Query("SELECT * FROM facultate")
    List<Facultate> getAll();

    @Query("SELECT * FROM facultate WHERE universitateId = :universitateId")
    List<Facultate> getFacultatiByUniversitateId(int universitateId);

    @Query("SELECT * FROM facultate WHERE id = :id")
    Facultate getFacultateById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Facultate facultate);

    @Delete
    int delete(Facultate facultate);
}