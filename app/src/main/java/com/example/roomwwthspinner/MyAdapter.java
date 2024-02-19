package com.example.roomwwthspinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<PointLocation> locationList ;
    public Context context ;
    public MyAdapter(Context context, List<PointLocation> locationList) {
        this.locationList = locationList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return locationList.size();
    }

    @Override
    public Object getItem(int position) {
        return locationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.pls_view, parent, false);
        }

        PointLocation pointLocation = locationList.get(position);

        TextView txtNom = convertView.findViewById(R.id.txtNom);
        TextView txtAdresse = convertView.findViewById(R.id.txtAdresse);
        TextView txtTel = convertView.findViewById(R.id.txtTel);

        txtNom.setText(pointLocation.getNom());
        txtAdresse.setText(pointLocation.getAdresse());
        txtTel.setText(pointLocation.getTel());

        return convertView;
    }

}
