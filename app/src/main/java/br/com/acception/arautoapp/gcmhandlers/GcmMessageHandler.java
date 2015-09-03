package br.com.acception.arautoapp.gcmhandlers;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by anderson on 17/01/15.
 */
public class GcmMessageHandler extends IntentService {

    String msg;
    private Handler handler;

    public GcmMessageHandler(){
        super("GcmMessageHandler");
    }

    public void onCreate() {
        super.onCreate();
        handler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);
        msg = extras.getString("mensagem").toString(); //gson.toJson(extras); //extras.getString("titulo");
        showToast();

        Log.i("GCM", "Mensagem Recebida" + msg);
        enviarMensagensSms(extras);
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    public void showToast() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void enviarMensagensSms(Bundle extras) {
        try {
            String id_msg = extras.getString("id_mensagem").toString();
            JSONObject jmsg = new JSONObject(extras.getString("mensagem"));
            JSONArray jarray = new JSONArray(extras.getString("pessoas"));
            Log.i("Map", "" + jarray.toString());

            for(int i = 0; i < jarray.length(); i++){
                JSONObject jsonObject = jarray.getJSONObject(i);

                String nome = jsonObject.getString("nome");
                String tel = jsonObject.getString("telefone");

                Log.i("NOME", nome);
                Log.i("TEL", tel);
                Log.i("MSG", jmsg.getString("conteudo"));
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(tel, null, jmsg.getString("conteudo"), null, null);
                //Toast.makeText(getApplicationContext(),"Mensagem Enviada!", Toast.LENGTH_LONG).show();
                Log.i("SEND MESSAGE", "Mensagem Enviada! : id->" + msg);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
