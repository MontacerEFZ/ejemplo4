package montacer.elfazazi.ejemplo4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText txtPassword;
    private Button btnDesencriptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarVista();
        btnDesencriptar.setOnClickListener(new View.OnClickListener() { //para cargar la siguiente vista
            @Override
            public void onClick(View view) {
                String password = txtPassword.getText().toString();
                Intent intent = new Intent(MainActivity.this, DesencriptarActivity.class);

                Bundle bundle = new Bundle(); //esto es como la maleta para llevar info a la 2 actividad
                bundle.putString("PASS", password);
                intent.putExtras(bundle);
                startActivity(intent); //esto es como el coche para ir a la 2 actividad
            }
        });
    }

    private void iniciarVista() {
    txtPassword = findViewById(R.id.txtPasswordMain);
    btnDesencriptar = findViewById(R.id.btnDesencriptarMain);
    }
}