package net.iesseveroochoa.gabrielvidal.practica4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class PoblacionesAdapter extends ArrayAdapter<Poblacion> {

    ArrayList<Poblacion> al_Lista;

    public PoblacionesAdapter(Context context, int resource, ArrayList<Poblacion> poblaciones) {
        super(context,resource,poblaciones);
        al_Lista = poblaciones;
    }

    public ArrayList<Poblacion> getAl_Lista() {
        return al_Lista;
    }

    public void setAl_Lista(ArrayList<Poblacion> al_Lista) {
        this.al_Lista = al_Lista;
    }

    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent){
        View objeto = convertView;

        if (objeto==null){
            LayoutInflater inflater=LayoutInflater.from(getContext());
            objeto=inflater.inflate(R.layout.item_poblacion,null);
        }

        Poblacion poblacion=al_Lista.get(position);

        TextView tvCiudad=(TextView)objeto.findViewById(R.id.tv_Ciudad);
        tvCiudad.setText(poblacion.getLocalidad());

        TextView tvProvincia=(TextView)objeto.findViewById(R.id.tv_Provincia);
        tvProvincia.setText(poblacion.getProvincia());


        TextView tvDescripcion=(TextView)objeto.findViewById(R.id.tv_Descripcion);
        tvDescripcion.setText(poblacion.getComentarios());

        RatingBar rbPuntuacion=(RatingBar) objeto.findViewById(R.id.rb_Puntuacion);
        tvDescripcion.setText(poblacion.getComentarios());

        return (objeto);
    }

    public void addPoblacion(Poblacion p){
        al_Lista.add(p);

        this.notifyDataSetChanged();
    }

    public void delPoblacion(int index){
        al_Lista.remove(index);
        this.notifyDataSetChanged();
    }
}
