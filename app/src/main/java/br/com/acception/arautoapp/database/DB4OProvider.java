package br.com.acception.arautoapp.database;

/**
 * Created by anderson on 21/04/15.
 */
import java.util.List;

import android.content.Context;

import br.com.acception.arautoapp.database.domain.Arauto;

public class DB4OProvider extends Db4oHelper {

    private static DB4OProvider provider = null;

    //configure Db4oHelper by Passing Context.
    public DB4OProvider(Context ctx) {
        super(ctx);
    }

    public static DB4OProvider getInstance(Context ctx) {
        if (provider == null)
            provider = new DB4OProvider(ctx);
        return provider;
    }

    //This method is used to store the object into the database.
    public void store(Arauto exercise) {
        db().store(exercise);
    }

    //This method is used to delete the object into the database.
    public void delete(Arauto exercise) {
        db().delete(exercise);
    }

    //This method is used to retrive all object from database.
    public List<Arauto> findAll() {
        return db().query(Arauto.class);
    }

    //This method is used to retrive matched object from database.
    public List<Arauto> getByExample(Arauto s) {
        return db().queryByExample(s);
    }
// public ObjectSet<Student> getAllData() {
//  Student proto = new Student(null, null, 0);
//  ObjectSet<student> result = db().queryByExample(proto);
//  return result;
// }

}
