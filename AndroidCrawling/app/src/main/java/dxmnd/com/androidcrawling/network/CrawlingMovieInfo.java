package dxmnd.com.androidcrawling.network;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dxmnd.com.androidcrawling.items.MovieInfoListItem;

/**
 * Created by HunJin on 2017-08-05.
 */

public class CrawlingMovieInfo extends AsyncTask<Object, Object, List<MovieInfoListItem>> {

    private static final String TAG = CrawlingMovieInfo.class.getSimpleName();

    private String URL;
    private String select;

    private List<MovieInfoListItem> items = new ArrayList<>();

    public CrawlingMovieInfo(String URL, String select) {
        this.URL = URL;
        this.select = select;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected List<MovieInfoListItem> doInBackground(Object... params) {
        Log.e(TAG, "do in back ground");

        mega();

        return items;
    }

    private void mega() {
        try {
            Document document = Jsoup.connect(URL).get();
            Elements elements = document.select(select);

            for (Element element : elements) {

                Log.e(TAG, element.select(".title div a").html());
                Log.e(TAG, element.select(".cinema_time a .hover_time").html());
                Log.e(TAG, element.select(".cinema_time .time_info .seat").html());
                MovieInfoListItem item = new MovieInfoListItem();

                items.add(item);
            }

        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

}
