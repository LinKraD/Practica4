package net.iesseveroochoa.gabrielvidal.practica4;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PoblacionActivity extends AppCompatActivity {

    public static final String GUARDA ="net.iesseveroochoa.gabrielvidal.practica4.guardapoblacion";
    public static final String EDITA ="net.iesseveroochoa.gabrielvidal.practica4.editapoblacion";

    Spinner spnProvincias;
    Spinner spnPoblaciones;
    RatingBar rbValoracion;
    EditText etComentarios;

    TypedArray arrayLocalidades;

    FloatingActionButton fabGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poblacion);

        spnProvincias=findViewById(R.id.spn_Provincias);
        spnPoblaciones=findViewById(R.id.spn_Poblaciones);
        rbValoracion=findViewById(R.id.rb_Valoracion);
        etComentarios=findViewById(R.id.et_Comentarios);

        arrayLocalidades = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);


        fabGuardar=findViewById(R.id.fab_Guardar);


        if (getIntent().getParcelableExtra(EDITA) != null) {
            rellenarDatosPoblacionEnviada();
        } else {
            setTitle(getResources().getString(R.string.nuevaPoblacion));
            spnProvincias.setOnItemSelectedListener(rellenaPoblaciones(null));
        }
        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Poblacion nuevaPoblacion = new Poblacion(spnProvincias.getSelectedItem().toString(), spnPoblaciones.getSelectedItem().toString(), rbValoracion.getRating(), etComentarios.getText().toString());
                Intent resultado = new Intent();
                resultado.putExtra(GUARDA, nuevaPoblacion);
                setResult(RESULT_OK, resultado);
                finish();
            }
        });
    }


    private void rellenarDatosPoblacionEnviada() {
        Poblacion poblacionEnviada = getIntent().getParcelableExtra(EDITA);
        PoblacionActivity.this.setTitle(getResources().getString(R.string.mensajeEditar) + poblacionEnviada.getProvincia() + poblacionEnviada.getLocalidad());
        for (int i = 0; i < spnProvincias.getCount(); i++) {
            if (spnProvincias.getItemAtPosition(i).equals(poblacionEnviada.getProvincia())) {
                spnProvincias.setSelection(i);
                break;
            }
        }
        spnProvincias.setOnItemSelectedListener(rellenaPoblaciones(poblacionEnviada));
        spnPoblaciones.setEnabled(false);
        spnProvincias.setEnabled(false);
        rbValoracion.setRating(poblacionEnviada.getValoracion());
        etComentarios.setText(poblacionEnviada.getComentarios());
    }


    private AdapterView.OnItemSelectedListener rellenaPoblaciones(final Poblacion poblacion) {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter<CharSequence> adaptador = new ArrayAdapter<>(PoblacionActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        arrayLocalidades.getTextArray(i));
                spnPoblaciones.setAdapter(adaptador);
                if (poblacion != null) {
                    spnPoblaciones.setSelection(adaptador.getPosition(poblacion.getLocalidad()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
    }
}