package br.com.acception.arautoapp.database;

import com.activeandroid.query.Select;

import br.com.acception.arautoapp.database.domain.Arauto;
import br.com.acception.arautoapp.util.Constantes;

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
        a.setClient_id(Constantes.cliente_id);
        a.setClient_secret(Constantes.cliente_secret);
        a.setGrant_type(Constantes.grant_type);
        a.save();
    }
}
