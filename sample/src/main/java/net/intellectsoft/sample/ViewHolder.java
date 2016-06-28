package net.intellectsoft.sample;

import android.view.View;
import android.widget.TextView;

import net.intellectsoft.core.ui.adapter.BaseRecyclerHolder;


public class ViewHolder extends BaseRecyclerHolder {

    public TextView textView;

    public ViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView;
    }

}
