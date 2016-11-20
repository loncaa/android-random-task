package hr.magicpot.task.storage.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import hr.magicpot.task.storage.db.interactor.DatabaseInteractor;
import hr.magicpot.task.storage.db.model.DBModel;
import hr.magicpot.task.userinterface.MainActivity;

/**
 * Created by xxx on 20.11.2016..
 */

public class DatabaseManagerImpl implements DatabaseManager {
    private DBHelper helper;

    public DatabaseManagerImpl(DBHelper helper) {
        this.helper = helper;
    }

    @Override
    public void chechDatabase(String url, onDatabaseConnection listener) throws SQLException{
            QueryBuilder builder = helper.getDao().queryBuilder();
            long hash = url.trim().toLowerCase().hashCode();
            builder.where().eq(DBModel.HASHCODE, hash);
            List<DBModel> list = helper.getDao().query(builder.prepare());
            listener.onRespones(list.isEmpty(), url);
    }

    @Override
    public void storeModel(DBModel model, onDatabaseConnection listener) throws SQLException {
        helper.getDao().createOrUpdate(model);
    }
}
