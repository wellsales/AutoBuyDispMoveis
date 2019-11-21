package com.example.autobuydm;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.autobuydm.R;

import java.util.ArrayList;

public class CarrosAdapter extends ArrayAdapter <Carros> {

    private final  Context context;
    private final ArrayList<Carros> elementos;



        public CarrosAdapter(Context context, ArrayList<Carros> elementos){
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;
        }
@Override
    public View getView(int position, View convertView, ViewGroup parent){
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.linha, parent, false);

    TextView nomeCarro = (TextView) rowView.findViewById(R.id.nome);
    TextView valorCarro = (TextView) rowView.findViewById(R.id.valor);
    ImageView imagem1 = (ImageView) rowView.findViewById(R.id.imagem1);

    nomeCarro.setText((elementos.get(position).getNome()));
    valorCarro.setText(elementos.get(position).getValor());

    byte[] imgByte = elementos.get(position).getImagem1();
    Bitmap bt = BitmapFactory.decodeByteArray(imgByte, 0 , imgByte.length);
    imagem1.setImageBitmap(bt);

    return rowView;
    }



}
