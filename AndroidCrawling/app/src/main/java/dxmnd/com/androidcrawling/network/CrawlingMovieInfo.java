package dxmnd.com.androidcrawling.network;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import dxmnd.com.androidcrawling.items.MovieInfoListItem;
import dxmnd.com.androidcrawling.items.RegionListItem;

import static dxmnd.com.androidcrawling.utils.ConstValiable.CGV;
import static dxmnd.com.androidcrawling.utils.ConstValiable.LOTTE;
import static dxmnd.com.androidcrawling.utils.ConstValiable.MEGABOX;
import static dxmnd.com.androidcrawling.utils.URL.LOTTE_THEADER_MOVIE_INFO;

/**
 * Created by HunJin on 2017-08-05.
 */

public class CrawlingMovieInfo extends AsyncTask<Object, Object, List<MovieInfoListItem>> {

    private static final String TAG = CrawlingMovieInfo.class.getSimpleName();

    private int brandflag;

    // MEGA
    private String URL;
    private String select;

    // LOTTE
    private String cinemaID;
    private String division;
    private String detaildivision;

    private List<MovieInfoListItem> items = new ArrayList<>();

    // MEGA
    public CrawlingMovieInfo(String URL, String select) {
        this.URL = URL;
        this.select = select;

        brandflag = MEGABOX;
    }

    // LOTTE
    public CrawlingMovieInfo(String cinemaID, String divison, String detaildivision)
    {
        URL = LOTTE_THEADER_MOVIE_INFO;
        this.cinemaID = cinemaID;
        this.division = divison;
        this.detaildivision = detaildivision;

        brandflag = LOTTE;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected List<MovieInfoListItem> doInBackground(Object... params) {
        Log.e(TAG, "do in back ground");

        if(brandflag == MEGABOX) {
            mega();
        }
        else if(brandflag == CGV) {

        }else if(brandflag == LOTTE) {
            lotte();
        }

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

    private void lotte()
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date today = new Date();

            Log.e("paramlist", "{\"MethodName\":\"GetPlaySequence\",\"channelType\":\"HO\",\"osType\":\"Chrome\",\"osVersion\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36\",\"playDate\":\""+sdf.format(today)+"\",\"cinemaID\":\""+Integer.parseInt(division)+"|"+Integer.parseInt(detaildivision)
                    +"|"+Integer.parseInt(cinemaID)+"\",\"representationMovieCode\":\"\"}");

            Document document = Jsoup.connect(URL)
                    .ignoreContentType(true)
                    .data("paramList", "{\"MethodName\":\"GetPlaySequence\",\"channelType\":\"HO\",\"osType\":\"Chrome\",\"osVersion\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36\",\"playDate\":\""+sdf.format(today)+"\",\"cinemaID\":\""+Integer.parseInt(division)+"|"+Integer.parseInt(detaildivision)
                            +"|"+Integer.parseInt(cinemaID)+"\",\"representationMovieCode\":\"\"}")
                    .post();

            Elements elements = document.select("body");

            Log.e("json", elements.text().toString());

            JSONObject jsonObj =  new JSONObject(elements.text());

            JSONArray jsonArr = jsonObj.getJSONObject("PlaySeqsHeader").getJSONArray("Items");

            HashMap<String, String> movie = new HashMap<>();

            for(int i = 0 ; i < jsonArr.length() ; ++i)
            {
                JSONObject jsonTemp = jsonArr.getJSONObject(i);

                String moviecode = jsonTemp.get("MovieCode").toString();
                String moviename = jsonTemp.get("MovieNameKR").toString();

                if(!movie.containsKey(moviecode))
                {
                    movie.put(moviecode, moviename);
                }
            }

            jsonArr = jsonObj.getJSONObject("PlaySeqs").getJSONArray("Items");

            for(int i = 0 ; i < jsonArr.length() ; ++i) {
                JSONObject jsonTemp = jsonArr.getJSONObject(i);

                MovieInfoListItem item = new MovieInfoListItem();

                String movieCode = jsonTemp.get("MovieCode").toString();
                String movieName = "Missing";
                String startTime = jsonTemp.get("StartTime").toString();
                String endTime = jsonTemp.get("EndTime").toString();
                String totalSeat = jsonTemp.get("TotalSeatCount").toString();
                String bookingSeat = jsonTemp.get("BookingSeatCount").toString();
                String screenNum = jsonTemp.get("ScreenNameKR").toString();
                if (movie.containsKey(movieCode)) {
                    movieName = movie.get(movieCode);
                }

                item.setSeat(bookingSeat);
                item.setTime(startTime);
                item.setTitle(movieName);

                Log.e("parsing data","movie code : " + movieCode + " movie name : " + movieName + " start time : " + startTime +
                    "end time : " + endTime + " total seat : " + totalSeat + " booking seat : " + bookingSeat + "screen number : " + screenNum);

                items.add(item);
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
