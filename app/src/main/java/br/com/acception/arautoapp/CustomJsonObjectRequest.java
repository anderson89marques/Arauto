package br.com.acception.arautoapp;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by anderson on 19/04/15.
 */
public class CustomJsonObjectRequest extends Request<JSONObject> {
    private Response.Listener response;
    private Map<String, String> params;

    public CustomJsonObjectRequest(int method, String url, Map<String,String> params,
                                   Response.Listener<JSONObject> response, Response.ErrorListener listener){
        super(method, url, listener);
        this.response = response;
        this.params = params;
    }

    //Esse construtor usa GET como padrão
    public CustomJsonObjectRequest(String url, Map<String,String> params,
                                   Response.Listener<JSONObject> response, Response.ErrorListener listener){
        super(Method.GET, url, listener);
        this.response = response;
        this.params = params;
    }

    public Map<String, String> getParams() throws AuthFailureError{
        return this.params;
    }

    /*
    * O retorno desse método é direcionado para o método deliverResponse
    * */
    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String js = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return (Response.success((new JSONObject(js)), HttpHeaderParser.parseCacheHeaders(response)));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        this.response.onResponse(response);
    }


}
