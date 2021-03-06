package br.com.acception.arautoapp.database.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import co.uk.rushorm.core.RushObject;
import co.uk.rushorm.core.RushObjectSerializer;
import co.uk.rushorm.core.annotations.RushList;

/**

 * Created by anderson on 21/04/15.
 */
public class Arauto extends RushObject{
    private String regId = "";
    private String client_id = "";
    private String client_secret = "";
    private String access_token = "";
    private String grant_type = "";
    private String chave = "";

    @RushList(classType = Telefone.class)
    private List<Telefone> telefones ;

    /*Sempre deve ter um Construtor vazio*/
    public Arauto(){}

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



    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        regId = regId;
    }

    public String toString(){
        return "RegID: " + this.getRegId() +",Client_id: " + this.getClient_id()
                + "Access_token: " + this.getAccess_token();
    }
}
