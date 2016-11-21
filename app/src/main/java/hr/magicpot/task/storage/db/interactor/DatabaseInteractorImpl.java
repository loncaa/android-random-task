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

    public DatabaseInteractorImpl(onDatabaseCheckListener listener) {
        this.listener = listener;
        this.databaseManager = new DatabaseManagerImpl();
    }

    //inicijalizacija baze
    @Override
    public void checkDatabase(String url) {
        if(!url.equals(""))
            databaseManager.chechDatabase(url, this);
        else
            listener.onDBError("Unesi url.");
    }

    @Override
    public void store(DBModel model) {
        databaseManager.storeModel(model, this);
    }

    @Override
    public void onRespones(String url, DBModel dbModel) {
        if(dbModel != null)
            listener.onDBResponse(false, url, dbModel.getHashcode());
        else
            listener.onDBResponse(true, url, 0);
    }

    @Override
    public void onMessage(String msg) {
        listener.onDBError(msg);
    }

    @Override
    public void onStoreSuccess(String url, long hashcode) {
        listener.onDBSucess(url, hashcode);
    }
}
