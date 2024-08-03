package com.example.proiect;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UniversitateDao {

    @Query("SELECT * FROM universitate")
    List<Universitate> getAll();

    @Query("SELECT * FROM universitate WHERE id = :id")
    Universitate getUniversitateById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Universitate universitate);

    @Delete
    int delete(Universitate universitate);
}
