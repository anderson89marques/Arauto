package br.com.acception.arautoapp.database;

/**
 * Created by anderson on 21/04/15.
 */
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.com.acception.arautoapp.database.domain.Arauto;

public class SqliteProvider extends SqliteHelper {

    private SQLiteDatabase database;
    private SqliteHelper dbhelper;
    private Context context;

    public SqliteProvider(Context context){
        super(context);
        this.context = context;
        dbhelper = new SqliteHelper(context);
    }

    public SQLiteDatabase getDatabase(){
        return database;
    }

    public void setDatabase(SQLiteDatabase sqLiteDatabase){
        this.database = sqLiteDatabase;
    }

    public void open() throws SQLException{
        setDatabase(dbhelper.getWritableDatabase());
    }

    public void close(){
        dbhelper.close();
    }

    public void inidb(){
        System.out.println("LOG CAT!");
        Log.d("INICIALIZANDO", "INICIO DA INICIALIZAÇÃO");
        try {
            if(getArauto() == null) {
                Log.d("INICIALIZANDO", "Banco Estava vazio!!");
                ContentValues cv = new ContentValues();
                cv.put("client_id", "8465b97ce7b211e48193207c8f043011");
                cv.put("client_secret", "84666584e7b211e48193207c8f043011");
                cv.put("grant_type", "client_credentials");
                database.insert(SqliteHelper.ARAUTO, null, cv);
            }
        }catch (NullPointerException e){
            Log.d("INICIALIZANDO", "Banco Estava vazio!!");
            ContentValues cv = new ContentValues();
            cv.put("client_id", "8465b97ce7b211e48193207c8f043011");
            cv.put("client_secret", "84666584e7b211e48193207c8f043011");
            cv.put("grant_type", "client_credentials");
            database.insert(SqliteHelper.ARAUTO, null, cv);
        }

        Log.d("INICIALIZANDO", "FIM DA INICIALIZAÇÃO");
    }

    public Arauto getArauto(){
        Arauto a= null;
        Cursor cursor = database.query(SqliteHelper.ARAUTO, new String[]{"id","regId", "client_id",
                "client_secret", "access_token", "grant_type", "chave"}, null, null, null, null,"id");
        if(cursor.moveToFirst()){
            a = cursorToArauto(cursor);
        }
        cursor.close();
        Log.d("GETARAUTO", a.toString());
        return a;
    }

    public Arauto cursorToArauto(Cursor cursor){
        Arauto a = new Arauto();
        a.setRegId(cursor.getString(1));
        a.setClient_id(cursor.getString(2));
        a.setClient_secret(cursor.getString(3));
        a.setAccess_token(cursor.getString(4));
        a.setGrant_type(cursor.getString(5));
        a.setChave(cursor.getString(6));
        return a;
    }

    public Arauto updateArauto(ContentValues cv){
        Arauto a = getArauto();
        Log.d("UPDATE arauto ID", ""+context.getDatabasePath("Arauto.db").getPath());
        Log.d("UPDATE arauto cv", ""+cv.getAsString("regId"));
        if(a != null){
            Log.d("UPDATE", "Entrei no update");
            String sqlstatiment = "UPDATE "+ SqliteHelper.ARAUTO + " SET regId = ?" + " WHERE id = ?";
            //int i = database.update(SqliteHelper.ARAUTO,cv,"id = " + a.getId(), null);
            database.rawQuery(sqlstatiment, new String[]{cv.getAsString("regId"), ""+a.getId()});
            Log.d("ID depois do update", "");
        }
        a = getArauto();
        Log.d("UPDATE arauto ID2", ""+a.getId());
        return a;
    }


}
