package net.intellectsoft.core.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public class BaseRecyclerHolder extends RecyclerView.ViewHolder{

    public BaseRecyclerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
