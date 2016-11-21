package hr.magicpot.task.network;

import hr.magicpot.task.network.runnable.HtmlContentRunnable;

/**
 * Created by xxx on 18.11.2016..
 */

public class InternetInteractorImpl implements InternetInteractor {
    @Override
    public void getHtmlContent(String urlstring, onWebDataListener listener) {
        new Thread(new HtmlContentRunnable(urlstring, listener)).start();
    }
}
