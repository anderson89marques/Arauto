package br.com.acception.arautoapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request.*;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;

import br.com.acception.arautoapp.database.DatabaseHandler;
import br.com.acception.arautoapp.database.SqliteProvider;
import br.com.acception.arautoapp.database.domain.Arauto;
import br.com.acception.arautoapp.util.EnviaDadosController;
import br.com.acception.arautoapp.util.OperacaoAsyncTask;
import android.telephony.TelephonyManager;

public class ArautoMainActivity extends Activity {
    SqliteProvider dbprovider;
    Map<String, String> params;
    EnviaDadosController envd;
    GoogleCloudMessaging gcm;
    String regid;
    ProgressDialog progress;
    TextView ed;
    DatabaseHandler dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arauto_main);
        ed = (TextView) findViewById(R.id.textView);

        //this.dbprovider = new SqliteProvider(ArautoMainActivity.this);
        this.envd = EnviaDadosController.getInstance(this);
        this.dbh = new DatabaseHandler(); //transformar em singleton

        //Iniciando o base de dados
        this.initArautodb();
    }

    private void initArautodb(){
        Log.d("INITDB", "inicializando");
        Arauto a = this.dbh.getArauto();
        if(a == null){
            this.dbh.initdb();
        }
    }

    /*
    Método que vai registar o Android no google gcm server
     */
    public void registrarAndroidNoGoogle(View view){
        Log.d("Register ID", "registrando android");
        Arauto a = this.dbh.getArauto();

        Log.d("REGISTRAR GOOGLE", a.toString());
        if(a != null && a.getRegId().equals("")) {
            Log.d("Não Registrado", "Registrando no gcm server");
            OperacaoAsyncTask op = new OperacaoAsyncTask(this);
            Void vo = null;
            op.execute(vo);
        }else {
            Toast.makeText(ArautoMainActivity.this, "Já Registrado", Toast.LENGTH_LONG).show();
        }
    }

    public void saveRegId(String regid, ProgressDialog progress){
        this.progress = progress;
        this.regid = regid;
        Arauto a = this.dbh.getArauto();

        if(a != null){
            a.setRegId(this.regid);
            //a.save();
            this.ed.setText(regid);
        }
        a = this.dbh.getArauto();
        Log.d("MOSTRAR RESULTADO", a.toString());
        registrarArautoNoKhipu();
        //registrarRegIdNoKhipu();
    }

    public void switchMetodosArautoForKhipu(int metodokhipu){
        final Arauto a = this.dbh.getArauto();

        switch (metodokhipu){
            case 1: saveAccesToken(a, this.envd.getResposta());
                break;
            case 2: alertaRegistroRegId(this.envd.getResposta());

        }
    }

    public void saveAccesToken(Arauto a, JSONObject resposta){
        Log.d("saveAccessToken", "resposta"+resposta.toString());
        try{
            a.setAccess_token(resposta.getString("access_token"));
            //a.save();
            registrarRegIdNoKhipu();
        } catch( JSONException e){
            e.printStackTrace();
        }

    }

    public void alertaRegistroRegId(JSONObject resposta){
        Toast.makeText(this, "Resposta:"+resposta.toString(),Toast.LENGTH_LONG ).show();
    }


    public void registrarArautoNoKhipu(){
        Log.d("Android", "Enviando dados");

        final Arauto a = this.dbh.getArauto();

        if(a != null && a.getAccess_token().equals("")){
            final JSONObject jsonBody = new JSONObject();

            try {
                jsonBody.put("client_id", a.getClient_id());
                jsonBody.put("client_secret", a.getClient_secret());
                jsonBody.put("grant_type", a.getGrant_type());
                jsonBody.put("scopes", "");
                String  path = "/token";
                Log.d("RESPOSTA", "Antes de enviar dados");
                this.envd.enviaDados(1, Method.GET, path, jsonBody);
                //Log.d("RESPOSTA", resposta.toString());
            } catch (JSONException e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(ArautoMainActivity.this, "Aplicação Já Registrada no khipu", Toast.LENGTH_LONG).show();
        }
    }

    public void registrarRegIdNoKhipu(){

        final JSONObject jsonBody = new JSONObject();
        final JSONArray jsonArray = new JSONArray();
        jsonArray.put("984593967");
        final Arauto a = this.dbh.getArauto();

        try {

            jsonBody.put("access_token", a.getAccess_token());
            jsonBody.put("RegId", a.getRegId());
            jsonBody.put("telefones", jsonArray);
            jsonBody.put("senha_registro_android", "123");

            String  path = "/gcm/criarGcm";
            this.envd.enviaDados(2, Method.POST, path, jsonBody);
        } catch (JSONException e){
            e.printStackTrace();
        }
        this.progress.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_arauto_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
