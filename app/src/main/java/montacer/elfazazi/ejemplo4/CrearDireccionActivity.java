package montacer.elfazazi.ejemplo4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CrearDireccionActivity extends AppCompatActivity {
    private EditText txtCalle;
    private EditText txtNumero;
    private EditText txtCiudad;

    private Button btnCrearDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_direccion);
    }
}