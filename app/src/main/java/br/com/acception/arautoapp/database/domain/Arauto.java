package br.com.acception.arautoapp.database.domain;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**

 * Created by anderson on 21/04/15.
 */
public class Arauto extends Model {
    @Column(name = "regId")
    private String regId = "";

    @Column(name = "client_id")
    private String client_id = "";

    @Column(name = "client_secret")
    private String client_secret = "";

    @Column(name = "access_token")
    private String access_token = "";

    @Column(name = "grant_type")
    private String grant_type = "";

    @Column(name = "chave")
    private String chave = "";

    //@Column(name = "nome")
    //private List<String> telefones ;


    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    /*public List<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(ArrayList<String> telefones) {
        this.telefones = telefones;
    }
    */
    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String toString(){
        return "ID: " + this.getId() + ",RegID: " + this.getRegId() +",Client_id: " + this.getClient_id()
                + "Access_token: " + this.getAccess_token();
    }
}
