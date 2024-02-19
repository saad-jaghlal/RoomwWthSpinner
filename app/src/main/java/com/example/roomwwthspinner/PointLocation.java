package com.example.roomwwthspinner;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//@Entity(primaryKeys = {"_id", "adresse"})
@Entity
public class PointLocation {
//    @PrimaryKey(autoGenerate = true)
//    private int _id;
    @NonNull
    @ColumnInfo(name="nom")
    @PrimaryKey
    private String nom;
    @ColumnInfo(name="ville")
    private String ville;
    @ColumnInfo(name="adresse")
    private String adresse;
    @ColumnInfo(name="tel")
    private String tel;
    public PointLocation(String nom, String ville,  String adresse, String tel) {
        this.nom = nom;
        this.ville = ville;
        this.adresse = adresse;
        this.tel = tel;
    }
//    public void set_id(int _id) {
//        this._id = _id;
//    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setVille(String ville) {

        this.ville = ville;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
//    public int get_id() {
//        return _id;
//    }
    public String getNom() {
        return nom;
    }
    public String getVille() {
        return ville;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getTel() {
        return tel;
    }
}