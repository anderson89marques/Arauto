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
        a.setClient_id("256b6b961af311e5aa0a463eb8bddccd");
        a.setClient_secret("256be5e41af311e5aa0a463eb8bddccd");
        a.setGrant_type("client_credentials");
        a.save();
    }
}
