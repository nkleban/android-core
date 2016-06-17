package net.intellectsoft.sample.mvp_load;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import net.intellectsoft.core.mvp.BaseLoaderMvpView;
import net.intellectsoft.core.ui.BaseAppCompatActivity;
import net.intellectsoft.core.ui.view.EmptyRecyclerView;
import net.intellectsoft.core.ui.view.SwipeToRefreshLayout;
import net.intellectsoft.sample.MockDataObject;
import net.intellectsoft.sample.R;
import net.intellectsoft.sample.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Raman Branavitski on 6/17/16.
 */
public class MVPLoadActivity extends BaseAppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener, BaseLoaderMvpView<List<MockDataObject>> {

    @BindView(R.id.swipe_refresh) public SwipeToRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view) public EmptyRecyclerView recyclerView;
    @BindView(R.id.empty_view) public View emptyView;

    private MVPLoadPresenter presenter;
    private RecyclerViewAdapter adapter;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, MVPLoadActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_load);
        presenter = new MVPLoadPresenter();
        presenter.attachView(this);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        presenter.requestData();
    }

    @Override
    public void onRefresh() {
        recyclerView.setEmptyView(emptyView);
        adapter.setItems(new ArrayList<>());
        hideLoading();
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showContent() {

    }

    @Override
    public void setData(List<MockDataObject> data) {
        adapter.setItems(data);
    }

    @Override
    public void showError(@StringRes int errorResId) {
        Toast.makeText(this, getString(errorResId), Toast.LENGTH_LONG).show();
    }
}
