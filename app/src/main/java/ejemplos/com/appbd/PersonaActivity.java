package ejemplos.com.appbd;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PersonaActivity extends AppCompatActivity {

    EditText txtnombre;
    EditText txtapellidos;
    Spinner sp_sexo;
    EditText txtedad;
    Button btn_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);

        txtnombre = findViewById(R.id.txtnombre);
        txtapellidos = findViewById(R.id.txtapellido);
        sp_sexo =  findViewById(R.id.sexo);
        txtedad = findViewById(R.id.txtedad);
        btn_reg = findViewById(R.id.btn_reg);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonaDbHelper db = new PersonaDbHelper(getBaseContext());
                db.saveLawyer(new Persona(txtnombre.getText().toString(),
                                            txtapellidos.getText().toString(),
                                            sp_sexo.getSelectedItem().toString(),
                                            txtedad.getText().toString()));
                Cursor c = db.getAllPersonas();
                String[][] datos = db.getdatos(c);


                int cc = c.getCount();

                Toast.makeText( getBaseContext() , "Guardado : "+ datos[0][1] +" "+ datos[0][2]+", Registros : "+cc, Toast.LENGTH_LONG).show();
            }
        });



    }



}
