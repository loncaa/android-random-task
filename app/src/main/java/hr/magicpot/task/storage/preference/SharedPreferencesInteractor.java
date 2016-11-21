package hr.magicpot.task.storage.preference;

/**
 * Created by xxx on 19.11.2016..
 */

public interface SharedPreferencesInteractor {

    public interface onSharedPreferencesListener{
        void onSPResponse(boolean b, String url, long hashcode);
        void onSPStoreSuccess(String url, long hashcode);
    }

    void storeHashcode(String url, long hashcode, onSharedPreferencesListener listener);
    void check(String url, onSharedPreferencesListener listener);
}
