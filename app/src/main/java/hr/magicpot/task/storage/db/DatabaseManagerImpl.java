package hr.magicpot.task.storage.db;

import android.os.Handler;
import android.os.Looper;

import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import hr.magicpot.task.MainApplication;
import hr.magicpot.task.storage.db.model.DBModel;

/**
 * Created by xxx on 20.11.2016..
 */

public class DatabaseManagerImpl implements DatabaseManager {
    private DBHelper helper;
    private Handler handler;

    public DatabaseManagerImpl() {
        this.helper = MainApplication.getDbHelper();
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void chechDatabase(final String url, final onDatabaseConnection listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String trimedurl = url.trim().toLowerCase();
                            QueryBuilder builder = helper.getDao().queryBuilder();
                            builder.where().eq(DBModel.URL, trimedurl);
                            List<DBModel> list = helper.getDao().query(builder.prepare());
                            if(list.size() > 0)
                                listener.onRespones(trimedurl, list.get(0));
                            else
                                listener.onRespones(trimedurl, null);
                        } catch (SQLException e) {
                            listener.onMessage("Greška prilikom provjere baze");
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void storeModel(final DBModel model, final onDatabaseConnection listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            helper.getDao().createOrUpdate(model);
                            listener.onStoreSuccess(model.getUrl(), model.getHashcode());
                        } catch (SQLException e) {
                            listener.onMessage("Greška prilikom spremanja u bazu.");
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }
}
