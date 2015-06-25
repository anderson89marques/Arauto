package br.com.acception.arautoapp.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.acception.arautoapp.ArautoMainActivity;

public class OperacaoAsyncTask extends AsyncTask<Void, Void, Void> {
	Context contexto;
    GoogleCloudMessaging gcm;
    String regid;
    String NUMERO_PROJETO = "247352349874"; //projeto que tem de ser criado no google
	ProgressDialog progress;
	
	public OperacaoAsyncTask(Context context) {
        this.contexto = context;
	}
	
	
	@Override
    protected void onPreExecute()
    {
        progress = ProgressDialog.show(contexto, "Pegando Regid no google...", "Aguarde um pouquinho XD...");
    }				
	
	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		//Map<String, String> parameters = new HashMap<String, String>();

        String msg = "";
        while (true) {
            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(contexto.getApplicationContext());
                }
                regid = gcm.register(NUMERO_PROJETO);
                msg = "Disposistivo Registrado, Id registro = " + regid;
                Log.i("RegID", msg);
                break;
            } catch (IOException ex) {
                msg = "Erro " + ex.getMessage();
            }
        }


		//parameters.put("RegId", regid);
        //parameters.put("id_projeto", "736300024f653c669ef602eeb295e0c594e258e6ac257111f9f797edf03122cc9b9267c253eadfe38b094a546f8f13faac4305baedf628e36a0497caaf3448b867339d0946503b2c00ffbc1658df83076926057a263db451456c7f94f6b382a6348188b8");

		return null;
	}
	
	@Override
    protected void onPostExecute(Void res)
    {
		ArautoMainActivity ope = (ArautoMainActivity) contexto;
		ope.saveRegId(this.regid, progress);
    }
}
