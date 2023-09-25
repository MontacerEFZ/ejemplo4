package montacer.elfazazi.ejemplo4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import montacer.elfazazi.ejemplo4.modelos.Direccion;
import montacer.elfazazi.ejemplo4.modelos.Usuario;

public class MainActivity extends AppCompatActivity {
    private EditText txtPassword;
    private EditText txtEmail;
    private Button btnDesencriptar;
    private Button btnCrearDireccion;
    private final int DIRECCIONES = 123;
    private ActivityResultLauncher<Intent> launcherDirecciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarVista();
        inicializarLauncher();
        btnDesencriptar.setOnClickListener(new View.OnClickListener() { //para cargar la siguiente vista
            @Override
            public void onClick(View view) {
                String password = txtPassword.getText().toString();  //importante el toString
                String email = txtEmail.getText().toString(); //importante el toString
                Usuario usuario = new Usuario(email, password);

                Intent intent = new Intent(MainActivity.this, DesencriptarActivity.class);

                Bundle bundle = new Bundle(); //esto es como la maleta para llevar info a la 2 actividad
               // bundle.putString("PASS", password); ya no la usaremos por crear la clase usuario
                bundle.putSerializable("USER", usuario);
                intent.putExtras(bundle);
                startActivity(intent); //esto es como el coche para ir a la 2 actividad
            }
        });
        btnCrearDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CrearDireccionActivity.class  );
                //empieza actividad y esperar resultado (respuesta)
                //startActivityForResult(intent, DIRECCIONES);
                launcherDirecciones.launch(intent);
            }
        });
    }

    private void inicializarLauncher() {
        //registrar una actividad de retorno con 2 partes
            //1. como lanzo la actividad hija (igual que el startactivityforresult)
            //2. que hago cuando la hija termina (igual al onactivityresult)

        launcherDirecciones = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            if (result.getData() != null){
                                Bundle bundle = result.getData().getExtras();
                                Direccion direccion = (Direccion) bundle.getSerializable("DIR");
                                Toast.makeText(MainActivity.this, direccion.toString(), Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MainActivity.this, "faltan datos", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "cancelada", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    /**
     * Se activa al retornar StartActivityForResult
     * @param requestCode --> identificador de la ventana que se ha cerrado (tipo de dato q retorna)
     * @param resultCode --> el modo en que se ha cerrado
     * @param data --> datos retornados (intent con el bundle dentro)
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //averiguar que actividad se cerro
        if (requestCode == DIRECCIONES){ //direcciones porque es lo que le hemos pasado en el startactivityforresult como identificador
            //averiguar si se cerro con exito
            if (resultCode == RESULT_OK){
                //averiguar si vuelve con datos
                if (data != null){
                    Bundle bundle = data.getExtras();
                    Direccion direccion = (Direccion) bundle.getSerializable("DIR");
                    Toast.makeText(this, direccion.toString(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "no hay datos", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "cancelada", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void iniciarVista() {
    txtPassword = findViewById(R.id.txtPasswordMain);
    txtEmail = findViewById(R.id.txtEmailMain);
    btnDesencriptar = findViewById(R.id.btnDesencriptarMain);
    btnCrearDireccion = findViewById(R.id.btnCrearDireccionMain);
    }
}