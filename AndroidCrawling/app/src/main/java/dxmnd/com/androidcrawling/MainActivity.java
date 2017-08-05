package dxmnd.com.androidcrawling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dxmnd.com.androidcrawling.adapters.BrandListRecyclerViewAdapter;
import dxmnd.com.androidcrawling.items.BrandListItem;
import dxmnd.com.androidcrawling.utils.ItemClick;

import static dxmnd.com.androidcrawling.utils.IntentKey.BRAND;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvBrandList;
    private BrandListRecyclerViewAdapter adapter;
    private List<BrandListItem> brandList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        itemAdd();
    }

    private void initialize() {
        rcvBrandList = (RecyclerView) findViewById(R.id.rcv_crawling_list_brand);
        adapter = new BrandListRecyclerViewAdapter();
        adapter.setItemClick(itemClick);
        rcvBrandList.setHasFixedSize(true);
        rcvBrandList.setLayoutManager(new LinearLayoutManager(this));
        rcvBrandList.setAdapter(adapter);
    }

    private void itemAdd() {
        BrandListItem itemMega = new BrandListItem();
        itemMega.setId(0);
        itemMega.setTitle("메가박스");

        BrandListItem itemCGV = new BrandListItem();
        itemCGV.setId(1);
        itemCGV.setTitle("CGV");

        BrandListItem itemRotte = new BrandListItem();
        itemRotte.setId(2);
        itemRotte.setTitle("롯데시네마");

        brandList.add(itemMega);
        brandList.add(itemCGV);
        brandList.add(itemRotte);

        adapter.setList(brandList);
    }

    private ItemClick itemClick = new ItemClick() {
        @Override
        public void click(int position) {
            Intent it = new Intent(MainActivity.this, CrawlingActivity.class);
            it.putExtra(BRAND, brandList.get(position).getId());
            startActivity(it);
        }
    };
}