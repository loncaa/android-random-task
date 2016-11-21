package hr.magicpot.task.network.runnable;

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
//špageti kod!
public class HtmlContentRunnable implements Runnable {
    private InternetInteractor.onWebDataListener listener;
    private String urlstring;
    private Handler handler;

    public HtmlContentRunnable(String urlstring, InternetInteractor.onWebDataListener listener) {
        this.listener = listener;
        this.urlstring = urlstring;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {
        final String html = getHtmlContentFromUrl(urlstring);
        if(html != null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    listener.onWDResponse(html, urlstring);
                }
            });
        }else{
            postMessage("Neuspješno dohvaćanje stranice.");
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
        } catch (IOException e) {
            postMessage("Neispravan url.\nMoreate upisati http://... ili https://...");
            e.printStackTrace();
            return null;
        } finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    postMessage("Greška prilikom zatvaranja streama.");
                    e.printStackTrace();
                    return null;
                }
            }
        }

        return stringBuilder.toString();
    }

    private void postMessage(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onWDError(msg);
            }
        });
    }
}
