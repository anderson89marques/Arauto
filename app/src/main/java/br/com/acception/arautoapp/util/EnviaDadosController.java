package br.com.acception.arautoapp.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.acception.arautoapp.ArautoMainActivity;

/**
 * Created by anderson on 22/04/15.
 */
public class EnviaDadosController {
    private static EnviaDadosController enviaDadosController= null;
    private String url = "http://192.168.1.140:8080";
    private Context context;
    private RequestQueue rq;
    private JSONObject resposta;

    public EnviaDadosController(Context context){
        this.rq = Volley.newRequestQueue(context);
        this.context = context;
    }

    public static EnviaDadosController getInstance(Context context){
        if(enviaDadosController == null)
            enviaDadosController = new EnviaDadosController(context);
        return enviaDadosController;
    }

    public void setResposta(JSONObject jsono){
        this.resposta = jsono;
    }

    public JSONObject getResposta(){
        return this.resposta;
    }

    public JSONObject enviaDados(int metedo, String path, JSONObject jsonObject){
        final JSONObject resposta;
        resposta = null;
        Log.d("RESPOSTA setada ", "valor null");
        try {
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url + path, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //try {
                                Log.d("Response:", response.toString());
                                setResposta(response);
                            //} catch (JSONException e) {
                            //    e.printStackTrace();
                            //}
                        }
                    },  new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Erro", "Error:" + error.getMessage());
                    error.printStackTrace();
                }
            });

            rq.add(req);
        } catch (Exception e){
            e.printStackTrace();
        }
        return getResposta();
    }

}
