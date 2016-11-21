package hr.magicpot.task.storage.db.interactor;

import hr.magicpot.task.storage.db.model.DBModel;

/**
 * Created by xxx on 18.11.2016..
 */

public interface DatabaseInteractor {

    interface onDatabaseCheckListener{
        void onDBResponse(boolean response, String url, long hashcode);
        void onDBError(String msg);
        void onDBSucess(String url, long hashcode);
    }

    void checkDatabase(String url);
    void store(DBModel model);
}
