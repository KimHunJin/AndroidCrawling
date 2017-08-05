package dxmnd.com.androidcrawling.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import dxmnd.com.androidcrawling.R;

/**
 * Created by HunJin on 2017-08-05.
 */

public class RegionListViewHolder extends RecyclerView.ViewHolder {

    public TextView txtCode;
    public TextView txtRegion;

    public RegionListViewHolder(View itemView) {
        super(itemView);
        txtCode = (TextView) itemView.findViewById(R.id.txt_region_code);
        txtRegion = (TextView) itemView.findViewById(R.id.txt_region_name);
    }
}
