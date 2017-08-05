package dxmnd.com.androidcrawling.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dxmnd.com.androidcrawling.R;
import dxmnd.com.androidcrawling.holders.RegionListViewHolder;
import dxmnd.com.androidcrawling.items.RegionListItem;
import dxmnd.com.androidcrawling.utils.ItemClick;

/**
 * Created by HunJin on 2017-08-05.
 */

public class RegionListRecyclerViewAdapter extends RecyclerView.Adapter<RegionListViewHolder> {

    private List<RegionListItem> items;

    private View view;

    private ItemClick itemClick;

    public RegionListRecyclerViewAdapter() {
        items = new ArrayList<>();
    }

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public void setItems(List<RegionListItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public RegionListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_region_list, parent, false);
        return new RegionListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RegionListViewHolder holder, final int position) {
        holder.txtCode.setText(items.get(position).getCode());
        holder.txtRegion.setText(items.get(position).getRegion());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.click(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
