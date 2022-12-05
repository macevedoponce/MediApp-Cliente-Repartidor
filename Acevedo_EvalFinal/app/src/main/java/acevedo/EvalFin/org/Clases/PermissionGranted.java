package acevedo.EvalFin.org.Clases;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionGranted {

    public static void pedirPermiso(Activity activity, String string){
        ActivityCompat.requestPermissions(activity, new String[]{string}, 1);
    }

    public static boolean checkPermiso(Activity activity, String string){
        return ContextCompat.checkSelfPermission(activity, string ) == PackageManager.PERMISSION_GRANTED;
    }
}
