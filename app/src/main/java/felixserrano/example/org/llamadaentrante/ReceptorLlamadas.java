package felixserrano.example.org.llamadaentrante;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class ReceptorLlamadas extends BroadcastReceiver {
    public ReceptorLlamadas() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Sacamos información del intent
        String estado = "", numero = "";
        Bundle extras = intent.getExtras();
        if (extras != null)
        {
            estado = extras.getString(TelephonyManager.EXTRA_STATE);
            if (estado.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                numero = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            }
        }
        String info = estado + " " + numero;
        Log.d("ReceptorAnuncio", info + " intent=" + intent);

        // Creamos Notificación
        NotificationManager nm =
                (NotificationManager)context
                        .getSystemService(Context.NOTIFICATION_SERVICE);
        Intent mIntent = new Intent(context, LlamadaEntranteActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity( context, 0, mIntent, 0);
        Notification notification = new Notification.Builder(context)
                .setContentTitle("Llamada !!!")
                .setTicker("Llamada Entrante (PMDM) ")
                .setContentText(info)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .getNotification();
        nm.notify(1, notification);
    }
}
