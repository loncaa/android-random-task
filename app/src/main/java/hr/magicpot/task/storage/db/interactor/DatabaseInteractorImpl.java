package hr.magicpot.task.storage.db.interactor;

import java.sql.SQLException;

import hr.magicpot.task.storage.db.DBHelper;
import hr.magicpot.task.storage.db.DatabaseManager;
import hr.magicpot.task.storage.db.DatabaseManagerImpl;
import hr.magicpot.task.storage.db.model.DBModel;
import hr.magicpot.task.userinterface.MainActivity;

/**
 * Created by xxx on 18.11.2016..
 */

public class DatabaseInteractorImpl implements DatabaseInteractor, DatabaseManager.onDatabaseConnection {
    private DatabaseManager databaseManager;
    private onDatabaseCheckListener listener;

    public DatabaseInteractorImpl(onDatabaseCheckListener listener, DBHelper helper) {
        this.listener = listener;
        this.databaseManager = new DatabaseManagerImpl(helper);
    }

    //inicijalizacija baze
    @Override
    public void checkDatabase(String url) {
        if(!url.equals(""))
            try {
                databaseManager.chechDatabase(url, this);
            } catch (SQLException e) {
                listener.onDBError("Greška priliko provjere pdoatka.");
                e.printStackTrace();
            }
        else
            listener.onDBError("Unesi url.");
    }

    @Override
    public void store(DBModel model) {

        try {
            databaseManager.storeModel(model, this);
            listener.onDBSucess();
        } catch (SQLException e) {
            listener.onDBError("Greška priliko spremanja podataka.");
            e.printStackTrace();
        }
    }

    @Override
    public void onRespones(boolean b, String url) {
            listener.onDBResponse(b, url);
    }

    @Override
    public void onError() {
        listener.onDBError("Greška prilikom spajanja na bazu.");
    }
}
