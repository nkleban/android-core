package net.intellectsoft.core.ui.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * Workaround for https://code.google.com/p/android/issues/detail?id=77712
 * Still exists in Support Library 24.0.0
 */
public class SwipeToRefreshLayout extends SwipeRefreshLayout {

    private boolean measured = false;
    private boolean preMeasureRefreshing = false;

    public SwipeToRefreshLayout(Context context) {
        super(context);
    }

    public SwipeToRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!measured) {
            measured = true;
            setRefreshing(preMeasureRefreshing);
        }
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        if (measured) {
            super.setRefreshing(refreshing);
        } else {
            preMeasureRefreshing = refreshing;
        }
    }

}
