package montacer.elfazazi.ejemplo4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class DesencriptarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desencriptar);

        //sacar la informacion que llega
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            String password = bundle.getString("PASS");
            Toast.makeText(this, password, Toast.LENGTH_SHORT).show();
        }
    }
}