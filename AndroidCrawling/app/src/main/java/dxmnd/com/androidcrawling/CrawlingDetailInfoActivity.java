package dxmnd.com.androidcrawling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import dxmnd.com.androidcrawling.network.CrawlingMovieInfo;

import static dxmnd.com.androidcrawling.utils.ConstValiable.LOTTE;
import static dxmnd.com.androidcrawling.utils.ConstValiable.MEGABOX;
import static dxmnd.com.androidcrawling.utils.IntentKey.BRAND;
import static dxmnd.com.androidcrawling.utils.IntentKey.DETAIL_DIVISION_CODE;
import static dxmnd.com.androidcrawling.utils.IntentKey.DIVISION_CODE;
import static dxmnd.com.androidcrawling.utils.IntentKey.REGION_CODE;
import static dxmnd.com.androidcrawling.utils.URL.MEAGBOX_THEADER_MOVIE_INFO;
import static dxmnd.com.androidcrawling.utils.URL.MEGABOX_SELECT_MOVIE_INFO;

public class CrawlingDetailInfoActivity extends AppCompatActivity {

    private static final String TAG = CrawlingDetailInfoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crawling_detail_info);

        Intent it = getIntent();

        if(it.getExtras().getInt(BRAND) == MEGABOX) {
            String code = it.getExtras().getString(REGION_CODE);

            Log.e(TAG, code);

            movieInfo(code);
        }
        else if(it.getExtras().getInt(BRAND) == LOTTE)
        {
            String cinemaid = it.getExtras().getString(REGION_CODE);
            String division = it.getExtras().getString(DIVISION_CODE);
            String detail = it.getExtras().getString(DETAIL_DIVISION_CODE);

            Log.e("check", "cinemaid : " + cinemaid + " division : " + division + " detail : " + detail);

            lotteinfo(cinemaid, division, detail);
        }
    }

    private void movieInfo(String code) {
        String URL = MEAGBOX_THEADER_MOVIE_INFO + "count=0&cinema=" + code;
        Log.e(TAG, URL);
        CrawlingMovieInfo crawlingMovieInfo = new CrawlingMovieInfo(URL, MEGABOX_SELECT_MOVIE_INFO);
        crawlingMovieInfo.execute();
    }

    private void lotteinfo(String cinemaID, String division, String detaildivison)
    {
        CrawlingMovieInfo crawlingMovieInfo = new CrawlingMovieInfo(cinemaID, division, detaildivison);
        crawlingMovieInfo.execute();
    }
}
