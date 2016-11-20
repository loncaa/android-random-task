package hr.magicpot.task.storage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.query.In;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import hr.magicpot.task.storage.db.model.DBModel;

/**
 * Created by xxx on 20.11.2016..
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "task.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<DBModel, Integer> dao;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.clearTable(connectionSource, DBModel.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, DBModel.class, false);
            TableUtils.clearTable(connectionSource, DBModel.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<DBModel, Integer> getDao(){
        if(dao == null)
            try {
                dao = getDao(DBModel.class);
                return dao;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return dao;
    }
}
