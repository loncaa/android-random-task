package hr.magicpot.task.network;

/**
 * Created by xxx on 18.11.2016..
 */

public interface InternetInteractor {

    interface onWebDataListener {
        void onWDResponse(String response, String url);
        void onWDError(String msg);
    }

    void getHtmlContent(String url, onWebDataListener responsListener);
}
