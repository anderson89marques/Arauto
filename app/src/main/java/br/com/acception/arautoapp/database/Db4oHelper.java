package br.com.acception.arautoapp.database;



import android.content.Context;
import java.io.IOException;
import java.lang.reflect.Array;

import android.util.Log;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;

import br.com.acception.arautoapp.database.domain.Arauto;

/**
 * Created by anderson on 21/04/15.
 */
public class Db4oHelper {
    private static ObjectContainer oc = null;
    private Context context;

    /**
     * @param ctx
     */
    public Db4oHelper(Context ctx) {
        context = ctx;
    }

    /**
     * Cria, abre e fecha o banco de dados
     */
    public ObjectContainer db() {

        try {
            if (oc == null || oc.ext().isClosed()) {
                oc = Db4oEmbedded.openFile(dbConfig(), db4oDBFullPath(context));
            }

            return oc;

        } catch (Exception ie) {
            Log.e(Db4oHelper.class.getName(), ie.toString());
            return null;
        }
    }

    /**
     * Configure the behavior of the database
     */

    private EmbeddedConfiguration dbConfig() throws IOException {
        EmbeddedConfiguration configuration = Db4oEmbedded.newConfiguration();
        configuration.common().objectClass(Arauto.class).objectField("name")
                .indexed(true);
        configuration.common().objectClass(Arauto.class).cascadeOnUpdate(true);
        configuration.common().objectClass(Arauto.class)
                .cascadeOnActivate(true);
        return configuration;
    }

    /**
     * Returns the path for the database location
     */

    private String db4oDBFullPath(Context ctx) {
        return ctx.getDir("data", 0) + "/" + "arautodb.db4o";
    }

    /**
     * Closes the database
     */

    public void close() {
        if (oc != null)
            oc.close();
    }
}
