package com.example.roomwwthspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    Spinner sp;
    ListView lv;
    DBPointLocation mabase;
    DAOPL dao;
    List<String> villeslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = findViewById(R.id.spinner);
        lv = findViewById(R.id.lv);
//        villeslist = new ArrayList<>();
//                new Thread(() -> {
//                    accessDB();
//                    remplissage();
//                    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dao.getVilles());
//                    runOnUiThread(() -> {
//                        sp.setAdapter(adapter);
//                    });
//                }).start();
        accessDB();
        remplissage();
        new AsyncTask<Void, Void, List<String>>() {
            @Override
            protected List<String> doInBackground(Void... voids) {
                return dao.getVilles();
            }

            @Override
            protected void onPostExecute(List<String> result) {
              List<String> uniqueCities = removeDuplicates(result);
                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>((Context) MainActivity.this, android.R.layout.simple_spinner_item, uniqueCities);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp.setAdapter(spinnerAdapter);
            }
        }.execute();

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String Ville = sp.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, sp.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                new Thread(() -> {
                    accessDB();
                    List<PointLocation> locationList = dao.getPLByVill(Ville);

                    runOnUiThread(() -> {
                        if (!locationList.isEmpty()) {
                            Toast.makeText(MainActivity.this, locationList.get(0).getAdresse(), Toast.LENGTH_SHORT).show();
                            MyAdapter myAdapter = new MyAdapter(getApplicationContext(), locationList);
                            lv.setAdapter(myAdapter);
                        } else {

                        }
                    });
                }).start();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public static List<String> removeDuplicates(List<String> list) {
        Set<String> set = new LinkedHashSet<>(list);
        return new ArrayList<>(set);
    }

    private void accessDB() {
        mabase = DBPointLocation.getDB(this);
        dao = mabase.getDao();
    }

    @SuppressLint("StaticFieldLeak")
    private void remplissage() {
        PointLocation p4 = new PointLocation("Ain diab", "Casa", "adr1", "11111");
        PointLocation p1 = new PointLocation("Bab CHaaba", "Safi", "adr1", "11111");
        PointLocation p2 = new PointLocation("My Youssef", "Safi", "adr1", "11111");
        PointLocation p3 = new PointLocation("Menara", "Marrakech", "adr1", "11111");
        PointLocation p5 = new PointLocation("Menara", "Marrakech", "adr1", "11111");
//                dao.addPL(p1,p2,p3,p4,p5);
        new AsyncTask<PointLocation, Void, Void>() {
            @Override
            protected Void doInBackground(PointLocation... points) {
                mabase = DBPointLocation.getDB(getApplicationContext());
                dao = mabase.getDao();
                for (PointLocation point : points) {
                    if (dao.getPL(point.getNom()) == null) {
                        dao.addPL(point);
                    }
                }
                return null;
            }
        }.execute(p1, p2, p3, p4,p5);
    }
}