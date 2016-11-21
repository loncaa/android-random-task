package hr.magicpot.task.storage.preference;

import android.content.SharedPreferences;

import hr.magicpot.task.MainApplication;

/**
 * Created by xxx on 19.11.2016..
 */

public class SharedPreferenceInteratorImpl implements SharedPreferencesInteractor {

    @Override
    public void storeHashcode(String url, long hashcode, onSharedPreferencesListener listener) {
        SharedPreferences sharedPreferences = MainApplication.getSharedPref("hashcode", 1);
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putLong(url, hashcode);
        e.commit();
        listener.onSPStoreSuccess(url, hashcode);
    }

    @Override
    public void check(String url, onSharedPreferencesListener listener) {
        SharedPreferences sharedPreferences = MainApplication.getSharedPref("hashcode", 1);
        long hashcode = sharedPreferences.getLong(url, 0);
        boolean hasurl =  hashcode != 0;
        listener.onSPResponse(hasurl, url, hashcode);
    }
}
