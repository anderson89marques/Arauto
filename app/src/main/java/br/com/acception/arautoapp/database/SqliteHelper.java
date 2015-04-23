package br.com.acception.arautoapp.database;



import android.content.Context;
import java.io.IOException;
import java.lang.reflect.Array;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import br.com.acception.arautoapp.database.domain.Arauto;

/**
 * Created by anderson on 21/04/15.
 */
public class SqliteHelper extends SQLiteOpenHelper{
    private static final String NOME_BANCO_DADOS = "Arauto.db";
    private static final int VERSAO_BANCO = 1;


    private static final String ARAUTO = "arauto";
    private static final String  TELEFONE = "telefone";

    private static String TELEFONE_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS" +
            TELEFONE + " (id INTEGER PRIMARY KEY, " +
            "numero TEXT NULL;";

    private static String ARAUTO_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS" +
            ARAUTO + " (id INTEGER PRIMARY KEY, " +
            "regId TEXT NULL," +
            "client_id TEXT NULL," +
            "client_secret TEXT NULL," +
            "access_token TEXT NULL," +
            "grant_type TEXT NULL," +
            "chave TEXT NULL," +
            "FOREIGN KEY (telefone_id) REFERENCES "+ TELEFONE +" (id) ON DELETE CASCADE;";

    public  void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    public  SqliteHelper(Context context){
        super(context, NOME_BANCO_DADOS, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TELEFONE_CREATE_TABLE);
        db.execSQL(ARAUTO_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TELEFONE);
        db.execSQL("DROP TABLE IF EXISTS" + ARAUTO);
        onCreate(db);
    }

    public void deleteTables(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS" + TELEFONE);
        db.execSQL("DROP TABLE IF EXISTS" + ARAUTO);
    }
}
