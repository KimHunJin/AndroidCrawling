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

import dxmnd.com.androidcrawling.items.RegionListItem;
import dxmnd.com.androidcrawling.utils.ConstValiable;

/**
 * Created by HunJin on 2017-08-05.
 */

public class CrawlingTheaderInfo extends AsyncTask<Object, Object, List<RegionListItem>> {

    private static final String TAG = CrawlingTheaderInfo.class.getSimpleName();

    private String type;
    private String URL;
    private String select;

    private List<RegionListItem> items = new ArrayList<>();

    public CrawlingTheaderInfo(String type, String URL, String select) {
        this.type = type;
        this.URL = URL;
        this.select = select;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected List<RegionListItem> doInBackground(Object... params) {
        Log.e(TAG, "do in back ground");

        if (type.equals(ConstValiable.MEGA_REGION_INFO)) {
            mega();
        }

        return items;
    }

    private void mega() {
        try {
            Document document = Jsoup.connect(URL).get();
            Elements elements = document.select(select);

            for (Element element : elements) {

                String code = element.attr("onclick");
                code = code.substring(code.length() - 5, code.length() - 1);
                String region = element.html();

                RegionListItem item = new RegionListItem();
                item.setCode(code);
                item.setRegion(region);

                items.add(item);
            }

        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(List<RegionListItem> aVoid) {
        super.onPostExecute(aVoid);
    }
}
