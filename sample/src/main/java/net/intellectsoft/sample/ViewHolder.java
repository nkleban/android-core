package net.intellectsoft.sample;

import android.view.View;
import android.widget.TextView;

import net.intellectsoft.core.ui.adapter.BaseRecyclerHolder;

/**
 * Created by Raman Branavitski on 6/17/16.
 */
public class ViewHolder extends BaseRecyclerHolder {

    public TextView textView;

    public ViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView;
    }
}
