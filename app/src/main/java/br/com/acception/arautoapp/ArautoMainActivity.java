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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Request.*;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.db4o.ext.Db4oException;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.acception.arautoapp.database.SqliteProvider;
import br.com.acception.arautoapp.database.domain.Arauto;
import br.com.acception.arautoapp.util.EnviaDadosController;
import br.com.acception.arautoapp.util.OperacaoAsyncTask;


public class ArautoMainActivity extends Activity {
    SqliteProvider dbprovider;
    Map<String, String> params;
    EnviaDadosController envd;
    //private RequestQueue rq;
    //private String url;
    GoogleCloudMessaging gcm;
    String regid;
    ProgressDialog progress;
    TextView ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arauto_main);
        ed = (TextView) findViewById(R.id.textView);

        this.dbprovider = new SqliteProvider(ArautoMainActivity.this);
        this.envd = EnviaDadosController.getInstance(getApplicationContext());

        //Iniciando o base de dados
        this.initArautodb();
    }

    private void initArautodb(){
        Log.d("INITDB", "inicializando");
        try {
            dbprovider.open();

        }catch (SQLException e){
            e.printStackTrace();
        };
       dbprovider.inidb();
       dbprovider.close();
    }

    /*
    Método que vai registar o Android no google gcm server
     */
    public void registrarAndroidNoGoogle(View view){
        Log.d("Register ID", "registrando android");
        try {
            dbprovider.open();

        }catch (SQLException e){
            e.printStackTrace();
        };

        Arauto a = dbprovider.getArauto();
        dbprovider.close();
        if(a.getRegId().equalsIgnoreCase("")) {
            Log.d("Não Registrado", "Registrando no gcm server");
            OperacaoAsyncTask op = new OperacaoAsyncTask(this);
            Void vo = null;
            op.execute(vo);
        }else {
            Toast.makeText(ArautoMainActivity.this, "Já Registrado", Toast.LENGTH_LONG).show();
        }
    }

    public void MostrarResultado(String regid){
        try {
            dbprovider.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.regid = regid;
        ContentValues cv = new ContentValues();
        cv.put("regId", regid);
        dbprovider.updateArauto(cv);
        this.ed.setText(regid);
        dbprovider.close();
    }

    //chamadas volley
    public void registrarArautoNoKhipu(View view){
        Log.d("Android", "Enviando dados");
        try {
            dbprovider.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        final Arauto a = dbprovider.getArauto();

        if(!a.getRegId().equals("")){
            final JSONObject jsonBody = new JSONObject();

            try {
                jsonBody.put("client_id", a.getClient_id());
                jsonBody.put("client_secret", a.getClient_secret());
                jsonBody.put("grant_type", a.getGrant_type());
                jsonBody.put("scopes", "");
                String  path = "/token";
                JSONObject resposta = this.envd.enviaDados(Method.POST, path, jsonBody);
                a.setAccess_token(resposta.getString("body"));
            } catch (JSONException e){
                e.printStackTrace();
            }
        }else{
            Toast.makeText(ArautoMainActivity.this, "Aplicação Ainda não Registrada", Toast.LENGTH_LONG).show();
        }
        dbprovider.close();
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
