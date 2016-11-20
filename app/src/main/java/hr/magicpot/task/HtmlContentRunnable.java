package hr.magicpot.task;

import android.os.Handler;
import android.os.Looper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import hr.magicpot.task.network.InternetInteractor;

/**
 * Created by xxx on 19.11.2016..
 */

public class HtmlContentRunnable implements Runnable {
    InternetInteractor.onWebDataListener listener;
    String urlstring;

    public HtmlContentRunnable(String urlstring, InternetInteractor.onWebDataListener listener) {
        this.listener = listener;
        this.urlstring = urlstring;
    }

    @Override
    public void run() {
        Handler mHandler = new Handler(Looper.getMainLooper());
        final String html = getHtmlContentFromUrl(urlstring);
        if(html != null){
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    listener.onWDResponse(html, urlstring);
                }
            });
        }else{
            mHandler.post(new Runnable() {
                @Override
                public void run() { listener.onWDError("Neuspješno dohvacanje stranice.");
                }
            });
        }
    }

    private String getHtmlContentFromUrl(String pageUrl){
        URL url = null;
        InputStream is = null;
        BufferedReader br = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            String html;
            url = new URL(pageUrl);
            is = url.openStream();
            br = new BufferedReader(new InputStreamReader(is));

            while((html = br.readLine()) != null){
                stringBuilder.append(html);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return stringBuilder.toString();
    }
}
