package hr.magicpot.task.storage.preference;

/**
 * Created by xxx on 19.11.2016..
 */

public interface SharedPreferencesInteractor {

    public interface onSharedPreferencesListener{
        void onSPResponse(boolean b, String url);
    }

    void storeHashcode(String url);
    void check(String url, onSharedPreferencesListener listener);
}
