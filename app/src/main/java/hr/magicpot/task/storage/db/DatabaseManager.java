package hr.magicpot.task.storage.db;

import java.sql.SQLException;

import hr.magicpot.task.storage.db.model.DBModel;

/**
 * Created by xxx on 20.11.2016..
 */

public interface DatabaseManager {
    interface onDatabaseConnection{
        void onRespones(String url, DBModel hashcode);
        void onMessage(String msg);
        void onStoreSuccess(String url, long hashcode);
    }

    void chechDatabase(String url, onDatabaseConnection listener);
    void storeModel(DBModel model, onDatabaseConnection listener);
}
