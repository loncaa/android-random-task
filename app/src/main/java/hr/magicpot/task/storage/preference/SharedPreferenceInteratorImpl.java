package hr.magicpot.task.storage.preference;

import android.content.SharedPreferences;

import hr.magicpot.task.userinterface.MainActivity;

/**
 * Created by xxx on 19.11.2016..
 */

public class SharedPreferenceInteratorImpl implements SharedPreferencesInteractor {

    @Override
    public void storeHashcode(String url) {
        long hashcode = url.trim().toLowerCase().hashCode();
        SharedPreferences sharedPreferences = MainActivity.getSPreferences("hashcode", 1);
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putLong(String.valueOf(hashcode), hashcode);
        e.commit();
    }

    @Override
    public void check(String url, onSharedPreferencesListener listener) {
        long hash = url.trim().toLowerCase().hashCode();
        SharedPreferences sharedPreferences = MainActivity.getSPreferences("hashcode", 1);
        long l = sharedPreferences.getLong(String.valueOf(hash), 0);

        boolean response = l != 0;
        listener.onSPResponse(response, url);
    }
}
