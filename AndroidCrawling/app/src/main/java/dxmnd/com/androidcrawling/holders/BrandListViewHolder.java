package dxmnd.com.androidcrawling.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import dxmnd.com.androidcrawling.R;

/**
 * Created by HunJin on 2017-08-05.
 */

public class BrandListViewHolder extends RecyclerView.ViewHolder {
    public TextView txtBrandTitle;

    public BrandListViewHolder(View itemView) {
        super(itemView);

        txtBrandTitle = (TextView) itemView.findViewById(R.id.txt_item_brand_name);
    }
}
