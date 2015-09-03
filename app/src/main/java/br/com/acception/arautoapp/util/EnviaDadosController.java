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
import br.com.acception.arautoapp.database.domain.Arauto;

/**
 * Created by anderson on 22/04/15.
 */
public class EnviaDadosController {
    private static EnviaDadosController enviaDadosController= null;
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

    public ArautoMainActivity getArautoMainActivity(){
        ArautoMainActivity ope = (ArautoMainActivity) this.context;
        return ope;
    }

    public JSONObject enviaDados(final int metodokhipu,int metodo, String path, JSONObject jsonObject){

        Log.d("RESPOSTA setada ", "valor null");
        try {
            if(metodokhipu == 1){ //método GET
                //O jsonObject é colocado na url para que a requisição seja aceita no servidor.
                path += "?q="+jsonObject.toString();
            }
            JsonObjectRequest req = new JsonObjectRequest(metodo, Constantes.url + path, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //try {
                                Log.d("Response:", response.toString());
                                setResposta(response);

                                getArautoMainActivity().switchMetodosArautoForKhipu(metodokhipu);
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
