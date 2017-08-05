package dxmnd.com.androidcrawling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import dxmnd.com.androidcrawling.network.CrawlingMovieInfo;

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
        String code = it.getExtras().getString(REGION_CODE);

        Log.e(TAG, code);

        movieInfo(code);

    }

    private void movieInfo(String code) {
        String URL = MEAGBOX_THEADER_MOVIE_INFO + "count=0&cinema=" + code;
        Log.e(TAG, URL);
        CrawlingMovieInfo crawlingMovieInfo = new CrawlingMovieInfo(URL, MEGABOX_SELECT_MOVIE_INFO);
        crawlingMovieInfo.execute();
    }
}
