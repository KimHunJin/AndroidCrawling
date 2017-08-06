package dxmnd.com.androidcrawling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import dxmnd.com.androidcrawling.adapters.RegionListRecyclerViewAdapter;
import dxmnd.com.androidcrawling.items.RegionListItem;
import dxmnd.com.androidcrawling.network.CrawlingTheaderInfo;
import dxmnd.com.androidcrawling.utils.ItemClick;

import static dxmnd.com.androidcrawling.utils.ConstValiable.CGV;
import static dxmnd.com.androidcrawling.utils.ConstValiable.MEGABOX;
import static dxmnd.com.androidcrawling.utils.ConstValiable.LOTTE;
import static dxmnd.com.androidcrawling.utils.ConstValiable.MEGA_REGION_INFO;
import static dxmnd.com.androidcrawling.utils.ConstValiable.LOTTE_DIVISION_INFO;
import static dxmnd.com.androidcrawling.utils.ConstValiable.LOTTE;
import static dxmnd.com.androidcrawling.utils.IntentKey.BRAND;
import static dxmnd.com.androidcrawling.utils.IntentKey.CINEMAID;
import static dxmnd.com.androidcrawling.utils.IntentKey.DETAIL_DIVISION_CODE;
import static dxmnd.com.androidcrawling.utils.IntentKey.DIVISION_CODE;
import static dxmnd.com.androidcrawling.utils.IntentKey.REGION_CODE;
import static dxmnd.com.androidcrawling.utils.URL.LOTTE_THEADER_URL;
import static dxmnd.com.androidcrawling.utils.URL.MEGABOX_SELECT_THEADER;
import static dxmnd.com.androidcrawling.utils.URL.MEGABOX_THEADER_URL;

public class CrawlingActivity extends AppCompatActivity {

    private CrawlingTheaderInfo crawlingTheaderInfo;

    private static final String TAG = CrawlingActivity.class.getSimpleName();

    private RecyclerView rcvCrawlingRegion;
    private RegionListRecyclerViewAdapter adapter;


    private List<RegionListItem> items;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crawling);

        Intent it = getIntent();
        id = it.getExtras().getInt(BRAND);

        initialize();

        switch (id) {
            case MEGABOX: {
                crawlingMegaBox();

                break;
            }
            case CGV: {
                crawlingCGV();
                break;
            }
            case LOTTE: {
                crawlingLotte();

                break;
            }
        }
    }


    private void initialize() {
        rcvCrawlingRegion = (RecyclerView) findViewById(R.id.rcv_crawling_list_region);
        rcvCrawlingRegion.setHasFixedSize(true);
        adapter = new RegionListRecyclerViewAdapter();
        adapter.setItemClick(click);
        rcvCrawlingRegion.setLayoutManager(new LinearLayoutManager(this));
        rcvCrawlingRegion.setAdapter(adapter);
    }

    private void crawlingCGV() {

    }


    private void crawlingMegaBox() {
        crawlingTheaderInfo = new CrawlingTheaderInfo(MEGA_REGION_INFO, MEGABOX_THEADER_URL, MEGABOX_SELECT_THEADER);
        try {
            items = crawlingTheaderInfo.execute().get();

            adapter.setItems(items);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void crawlingLotte() {
        crawlingTheaderInfo = new CrawlingTheaderInfo(LOTTE_DIVISION_INFO, LOTTE_THEADER_URL, "");

        try{
            items = crawlingTheaderInfo.execute().get();

            adapter.setItems(items);
        } catch (ExecutionException e){
            e.printStackTrace();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private ItemClick click = new ItemClick() {
        @Override
        public void click(int position) {
            // TODO: 2017-08-05 crawling use code
            switch (id) {
                case MEGABOX: {
                    Intent it = new Intent(getApplicationContext(), CrawlingDetailInfoActivity.class);
                    it.putExtra(BRAND, MEGABOX);
                    it.putExtra(REGION_CODE, items.get(position).getCode());
                    startActivity(it);
                    break;
                }
                case CGV: {
                    break;
                }
                case LOTTE: {
                    Intent it = new Intent(getApplicationContext(), CrawlingDetailInfoActivity.class);
                    it.putExtra(BRAND, LOTTE);
                    it.putExtra(DIVISION_CODE, items.get(position).getDivision());
                    it.putExtra(DETAIL_DIVISION_CODE, items.get(position).getDetailDivision());
                    it.putExtra(REGION_CODE, items.get(position).getCode());
                    startActivity(it);
                    break;
                }
            }
        }
    };
}
