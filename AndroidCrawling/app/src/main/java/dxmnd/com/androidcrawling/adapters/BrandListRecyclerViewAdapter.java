package dxmnd.com.androidcrawling.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dxmnd.com.androidcrawling.R;
import dxmnd.com.androidcrawling.holders.BrandListViewHolder;
import dxmnd.com.androidcrawling.items.BrandListItem;
import dxmnd.com.androidcrawling.utils.ItemClick;

/**
 * Created by HunJin on 2017-08-05.
 */

public class BrandListRecyclerViewAdapter extends RecyclerView.Adapter<BrandListViewHolder> {

    private ItemClick itemClick;

    private View view;
    private List<BrandListItem> list;

    public BrandListRecyclerViewAdapter() {
        list = new ArrayList<>();
    }

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @Override
    public BrandListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_brand_list, parent, false);
        return new BrandListViewHolder(view);
    }

    public void setList(List<BrandListItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(BrandListViewHolder holder, final int position) {
        holder.txtBrandTitle.setText(list.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.click(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
