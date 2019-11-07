package net.iesseveroochoa.gabrielvidal.practica4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String STATE_LISTA_CIUDADES = "net.iessochoa.gabrielvidal.practica4.MainActivity.lista_ciudades";
    private final static int NUEVA = 0;
    private final static int ACTUALIZA = 1;

    ListView lvListaCiudades;
    ArrayList<Poblacion> al_datos;
    PoblacionesAdapter adaptadorLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvListaCiudades=findViewById(R.id.lv_Ciudades);

        al_datos=new ArrayList<Poblacion>();
        adaptadorLista=new PoblacionesAdapter(this,0, al_datos);
        lvListaCiudades.setAdapter(adaptadorLista);


        if (savedInstanceState==null){
            crearDatos();
        } else{
            al_datos=savedInstanceState.getParcelableArrayList(STATE_LISTA_CIUDADES);
        }

        adaptadorLista=new PoblacionesAdapter(this,R.layout.item_poblacion,al_datos);
        lvListaCiudades.setAdapter(adaptadorLista);

        lvListaCiudades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this, PoblacionActivity.class);
                intent.putExtra(PoblacionActivity.EDITA,al_datos.get(i));
                startActivityForResult(intent,ACTUALIZA);

            }
        });
        lvListaCiudades.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                adaptadorLista.delPoblacion(i);

                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_anyade:
                Intent intent=new Intent(MainActivity.this, PoblacionActivity.class);
                startActivityForResult(intent,NUEVA);
                return true;
            case R.id.action_ordenar:
                Toast.makeText(this, R.string.ordenar,Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_acercade:
                AlertDialog.Builder dialogo=new AlertDialog.Builder(MainActivity.this);
                dialogo.setTitle("Acerca de...");

                dialogo.setMessage("Práctica 4 \n Gabriel Vidal Fuente \n Licencia cc(Creative Commons)");
                dialogo.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialogo.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


            if (resultCode == RESULT_OK) {
                switch (requestCode){
                    case NUEVA:
                        Poblacion p=data.getParcelableExtra(PoblacionActivity.GUARDA);
                        anyadirPoblacion(p);
                        break;

                    case ACTUALIZA:
                        Poblacion pEditada=data.getParcelableExtra(PoblacionActivity.EDITA);
                        MainActivity.this.modificarPoblacion(pEditada);
                        break;

                }
            }
        }

    private void crearDatos() {
        String localidad = "Localidad";
        String provincia = "Provincia";
        String descripcion = "Descripción";
        for (int i = 0; i <= 3; i++) {
            al_datos.add(new Poblacion(localidad + i, provincia + i, 3,descripcion + i));
        }
    }

    private void anyadirPoblacion(Poblacion p) {
        if (al_datos.contains(p)) {
            al_datos.remove(p);
        }
        al_datos.add(p);
        adaptadorLista.notifyDataSetChanged();
    }

    private void modificarPoblacion(Poblacion p) {
        al_datos.get(al_datos.indexOf(p)).setValoracion(p.getValoracion());
        al_datos.get(al_datos.indexOf(p)).setComentarios(p.getComentarios());
        adaptadorLista.notifyDataSetChanged();
    }
}
