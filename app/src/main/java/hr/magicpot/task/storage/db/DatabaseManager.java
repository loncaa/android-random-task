package hr.magicpot.task.storage.db;

import java.sql.SQLException;

import hr.magicpot.task.storage.db.model.DBModel;

/**
 * Created by xxx on 20.11.2016..
 */

public interface DatabaseManager {
    interface onDatabaseConnection{
        void onRespones(boolean b, String url);
        void onError();
    }

    void chechDatabase(String url, onDatabaseConnection listener) throws SQLException;
    void storeModel(DBModel model, onDatabaseConnection listener) throws SQLException;
}
