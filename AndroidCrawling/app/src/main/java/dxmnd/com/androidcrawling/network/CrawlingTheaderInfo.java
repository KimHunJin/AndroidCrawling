package dxmnd.com.androidcrawling.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import dxmnd.com.androidcrawling.items.RegionListItem;
import dxmnd.com.androidcrawling.utils.ConstValiable;
import dxmnd.com.androidcrawling.utils.URL;

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
        else if(type.equals(ConstValiable.LOTTE_DIVISION_INFO)){
            lotte();
        }

        return items;
    }

    private void mega() {
        try {
            Document document = Jsoup.connect(URL).get();
            Elements elements = document.select(select);

            Log.e("mega", elements.toString());

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

    private void lotte()
    {
        try {
            Document document = Jsoup.connect(URL)
                    .ignoreContentType(true)
                    .data("paramList", "{\"MethodName\":\"GetCinemaItems\",\"channelType\":\"MW\",\"osType\":\"Chrome\",\"osVersion\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36\"}")
                    .post();

            Elements elements = document.select("body");

            Log.e("json", elements.text().toString());

            JSONObject jsonObj =  new JSONObject(elements.text());

            JSONArray jsonArr = jsonObj.getJSONObject("Cinemas").getJSONArray("Items");

            for(int i = 0 ; i < jsonArr.length() ; ++i)
            {
                JSONObject jsonTemp = jsonArr.getJSONObject(i);

                String CinemaID  = jsonTemp.get("CinemaID").toString();
                String detail = jsonTemp.get("DetailDivisionCode").toString();
                String division = jsonTemp.get("DivisionCode").toString();
                String region = jsonTemp.get("CinemaNameKR").toString();

                RegionListItem item = new RegionListItem();
                item.setCode(CinemaID);
                item.setDivision(division);
                item.setRegion(region);
                item.setDetaildivision(detail);

                items.add(item);
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(List<RegionListItem> aVoid) {
        super.onPostExecute(aVoid);
    }
}
