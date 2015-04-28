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
        a.setClient_id("90a30f52edd711e4b261207c8f043011");
        a.setClient_secret("90a3b196edd711e4b261207c8f043011");
        a.setGrant_type("client_credentials");
        a.save();
    }
}
