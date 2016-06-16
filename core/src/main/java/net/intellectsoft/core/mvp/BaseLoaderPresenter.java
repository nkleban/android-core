package net.intellectsoft.core.mvp;

import android.support.annotation.StringRes;

import net.intellectsoft.core.R;
import net.intellectsoft.core.utils.AndroidUtils;

import rx.Observable;
import rx.Subscription;

public abstract class BaseLoaderPresenter<D, V extends BaseLoaderMvpView<D>> extends BasePresenter<V> {

    protected D data;

    public void requestData() {
        requestData(true);
    }

    public void requestData(boolean forceUpdate) {
        if (!forceUpdate && data != null) {
            onDataReceived(data);
            return;
        }

        if (!AndroidUtils.isNetworkConnected()) {
            mMvpView.showError(R.string.connection_error);
            return;
        }

        mMvpView.showLoading();
        Subscription subscription = getData()
                .subscribe(data -> {
                    this.data = data;
                    onDataReceived(data);
                }, this::onError);
        mSubscriptions.add(subscription);
    }

    protected abstract Observable<D> getData();

    protected void onDataReceived(D data) {
        mMvpView.setData(data);
        mMvpView.hideLoading();
        mMvpView.showContent();
    }

    protected void onError(Throwable throwable) {
        mMvpView.hideLoading();
        mMvpView.showError(getErrorMessageRes(throwable));
    }

    @StringRes
    protected abstract int getErrorMessageRes(Throwable throwable);

}
