package br.com.acception.arautoapp.database;

/**
 * Created by anderson on 21/04/15.
 */
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.com.acception.arautoapp.database.domain.Arauto;

public class SqliteProvider extends SqliteHelper {

    private SQLiteDatabase database;
    private SqliteHelper dbhelper;

    public SqliteProvider(Context context){
        super(context);
    }


}
