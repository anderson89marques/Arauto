package br.com.acception.arautoapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request.*;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;


public class ArautoMainActivity extends ActionBarActivity {
    Map<String, String> params;
    private RequestQueue rq;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arauto_main);
        url = "http://0.0.0.0:8080/gcm/criaGcm";
        rq = Volley.newRequestQueue(ArautoMainActivity.this);
    }


    //chamadas volley
    public void callByJsonObjectRequest(View view){
        this.params = null;
        CustomJsonObjectRequest cjor = new CustomJsonObjectRequest(Method.POST, this.url, this.params,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                log.i("Volley Teste", "Sucesso: "+response);
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
