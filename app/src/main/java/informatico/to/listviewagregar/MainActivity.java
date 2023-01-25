package informatico.to.listviewagregar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //creamos las objeto de lasa clases
    EditText etnumero;
    Button btncrear,btnborrar;
    ListView listatabla;

    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.LARGE_BANNER);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        /*Instanciamos los objeto de las
        clases y lo instanciamos con el id de la vista*/
        etnumero=(EditText)findViewById(R.id.editTextNumber);
        btncrear=(Button) findViewById(R.id.btncrear);
        btnborrar=(Button) findViewById(R.id.btnlimpiar);
        listatabla=(ListView)findViewById(R.id.lisviewtabla);
        /*Creamos los arreglos de la lista y del adaptador de la lista*/
        final ArrayList<String> alista=new ArrayList<String>();
        final ArrayAdapter<String> aa=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alista);
        /*Agregamos al adaptador del arreglo*/
        listatabla.setAdapter(aa);
        /*creamos el metodo del boton crear*/
        btncrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etnumero.getText().toString().trim().equalsIgnoreCase("")){
                    etnumero.setError("Ingrese un valor mayor a 0");
                }
                //creamos una variable entero
                int total=0;
                /*creamos una variable entero y parseamos
                 el edittext para almacenarlo en el */
                int n1=Integer.parseInt(etnumero.getText().toString());
                /*hacemos un blucle forte x = 0 y le damos limite que sea
                x sea mayor que 0 y menor igual a 10*/
                for (int x=0;x<=12;x++){
                    /*decimos que total sea la multiplicacion
                    de x por el numero ingresado*/
                    total=x*n1;
                    /*adicionamos a la lista x que aumentara de uno en uno
                    * el numero ingresado y por ultimo el numero resultante
                    *  de la multiplicacion*/
                    alista.add(String.valueOf(x)+" * "
                            +String.valueOf(n1)+" = "
                            +String.valueOf(total));
                    /*indicamos al adaptador el cambio*/
                    aa.notifyDataSetChanged();
                }
                /*limpiamos la caja de texto*/
                etnumero.setText("");
            }
        });
        /*creamos el metodo para el boton limpiar*/
        btnborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //limpiamos con el metodo clean
                alista.clear();
                //le indicamos al adaptador que verifique le cambio
                aa.notifyDataSetChanged();
            }
        });

        /*Creamos un metodo del listview */
        listatabla.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*capturamos la posicion de la lista*/
                String opcSelecion=(String)listatabla.getAdapter().getItem(i);
                /*decimos que lo elimnine solo a esa opcion*/
                alista.remove(i);
                /*indicamos al adaptador que hubo un cambio */
                aa.notifyDataSetChanged();
                /*mostramos cuando hacemos clic en la lista segun su posicion*/
                Toast.makeText(getApplication(),opcSelecion + " Elimindado",Toast.LENGTH_SHORT).show();
            }
        });
    }
}