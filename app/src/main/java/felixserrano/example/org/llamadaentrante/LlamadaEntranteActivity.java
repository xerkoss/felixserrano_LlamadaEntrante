package felixserrano.example.org.llamadaentrante;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class LlamadaEntranteActivity extends AppCompatActivity {

    private static final
    String[] PERMISOS_INICIALES ={
            Manifest.permission.READ_PHONE_STATE
    };
    private static final int PETICION_INICIAL = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamada_entrante);

        if (!hayPermiso(Manifest.permission.READ_PHONE_STATE)) {
            ActivityCompat.requestPermissions(this, PERMISOS_INICIALES, PETICION_INICIAL);
        }
    }

    private boolean
    hayPermiso(String perm) {
        return (ContextCompat.checkSelfPermission(this, perm) == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void
    onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (!hayPermiso(Manifest.permission.READ_PHONE_STATE)) {
            Toast.makeText(this, "No se podrán capturar las llamadas, no has dado permiso", Toast.LENGTH_LONG).show();
        }
    }
}
