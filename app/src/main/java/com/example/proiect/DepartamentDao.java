package com.example.proiect;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DepartamentDao {

    @Query("SELECT * FROM departament")
    List<Departament> getAll();

    @Query("SELECT * FROM departament WHERE facultateId = :facultateId")
    List<Departament> getDepartamenteByFacultateId(int facultateId);

    @Query("SELECT * FROM departament WHERE id = :id")
    Departament getDepartamentById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Departament departament);

    @Delete
    int delete(Departament departament);
}