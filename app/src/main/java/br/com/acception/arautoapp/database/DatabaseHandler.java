package br.com.acception.arautoapp.database;

import com.activeandroid.query.Select;

import br.com.acception.arautoapp.database.domain.Arauto;

/**
 * Created by anderson on 28/04/15.
 */
public class DatabaseHandler {
    public DatabaseHandler() {
    }

    public Arauto getArauto(){
        return new Select().from(Arauto.class).where("id=?", "1").executeSingle();
    }

    public void initdb(){
        Arauto a = new Arauto();
        a.setClient_id("8465b97ce7b211e48193207c8f043011");
        a.setClient_secret("84666584e7b211e48193207c8f043011");
        a.setGrant_type("client_credentials");
        a.save();
    }
}
