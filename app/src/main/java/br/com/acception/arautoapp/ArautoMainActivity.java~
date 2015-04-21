package br.com.acception.arautoapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.*;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.acception.arautoapp.util.OperacaoAsyncTask;


public class ArautoMainActivity extends Activity {
    Map<String, String> params;
    private RequestQueue rq;
    private String url;
    GoogleCloudMessaging gcm;
    String regid;
    ProgressDialog progress;
    TextView ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arauto_main);
        ed = (TextView) findViewById(R.id.textView);
        url = "http://192.168.0.106:8080/gcm/criaGcm";
        rq = Volley.newRequestQueue(ArautoMainActivity.this);
    }

    /*
    Método que vai registar o Android no google gcm server
     */
    public void registrarAndroidNoGoogle(View view){
        OperacaoAsyncTask op = new OperacaoAsyncTask(this);
        Void vo = null;
        op.execute(vo);
    }

    public void MostrarResultado(String regid){
        this.regid = regid;
        this.ed.setText(regid);
    }

    //chamadas volley
    public void callByJsonObjectRequest(View view){
        this.params = new HashMap<String, String>();
        this.params.put("client_id", "");
        this.params.put("client_secret", "");
        this.params.put("grant_type", "");
        this.params.put("scopes", "");

        CustomJsonObjectRequest cjor = new CustomJsonObjectRequest(Method.POST, this.url, this.params,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Volley Teste", "Sucesso: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ArautoMainActivity.this, "Erro:" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

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
