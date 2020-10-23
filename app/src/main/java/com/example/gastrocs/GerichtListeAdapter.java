package com.example.gastrocs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GerichtListeAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Gericht> gerichtListe;

    public GerichtListeAdapter(Context context, int layout, ArrayList<Gericht> gerichtListe) {
        this.context = context;
        this.layout = layout;
        this.gerichtListe = gerichtListe;
    }


    @Override
    public int getCount() {
        return gerichtListe.size();
    }

    @Override
    public Object getItem(int position) {
        return gerichtListe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }
    private class ViewHolder{
        ImageView imgView;
        TextView preisTxt, nameTxt;
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View  row=convertView;
        ViewHolder holder=new ViewHolder();
        if(row==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(layout,null);
            holder.nameTxt= (TextView) row.findViewById(R.id.gerichtName);
            holder.preisTxt=(TextView) row.findViewById(R.id.gerichtPreis);
            holder.imgView=(ImageView) row.findViewById(R.id.imgFoodElemente);
            row.setTag(holder);
        }
        else{
            holder=(ViewHolder) row.getTag();
        }
        Gericht gericht=gerichtListe.get(position);
        holder.nameTxt.setText(gericht.getName());
        holder.preisTxt.setText(gericht.getPreis());
        byte[] gerichtFoto=gericht.getImg();
        Bitmap bitmap= BitmapFactory.decodeByteArray(gerichtFoto,0,gerichtFoto.length);
        holder.imgView.setImageBitmap(bitmap);
        return row;
    }
}

