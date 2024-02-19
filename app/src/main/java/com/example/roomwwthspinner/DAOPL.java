package com.example.roomwwthspinner;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.Set;

@Dao
public interface DAOPL {
    //inserer un nouveau point de location
    @Insert
    void addPL(PointLocation... pl);
    //supprimer un point de location
    @Delete
    void deletePL(PointLocation pl);
    @Update
    void updatePL(PointLocation pl);
    @Query("SELECT * FROM PointLocation WHERE nom= :nom")
    PointLocation getPL(String nom);
    @Query("SELECT * FROM PointLocation WHERE nom= :id")
    PointLocation getPLbyid(int id);
    @Query("SELECT * FROM PointLocation")
    Cursor getPLS();
    @Query("SELECT ville FROM PointLocation")
    List<String> getVilles();

    @Query("SELECT * FROM PointLocation WHERE   ville = :ville")
    List<PointLocation> getPLByVill(String ville);
}