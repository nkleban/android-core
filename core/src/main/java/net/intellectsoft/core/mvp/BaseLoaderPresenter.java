package net.intellectsoft.core.mvp;

import android.support.annotation.StringRes;

import net.intellectsoft.core.R;
import net.intellectsoft.core.utils.AndroidUtils;

import java.lang.ref.SoftReference;

import rx.Observable;
import rx.Subscription;

public abstract class BaseLoaderPresenter<D, V extends BaseLoaderMvpView<D>> extends BasePresenter<V> {

    protected SoftReference<D> dataRef;

    public void requestData() {
        requestData(true);
    }

    public void requestData(boolean forceUpdate) {
        if (!forceUpdate && dataRef != null) {
            D data = dataRef.get();
            if (data != null) {
                onDataReceived(data);
                return;
            }
        }

        if (!AndroidUtils.isNetworkConnected()) {
            mMvpView.showError(R.string.connection_error);
            return;
        }

        mMvpView.showLoading();
        Subscription subscription = getDataObservable()
                .subscribe(data -> {
                    this.dataRef = new SoftReference<>(data);
                    onDataReceived(data);
                }, this::onError);
        mSubscriptions.add(subscription);
    }

    protected abstract Observable<D> getDataObservable();

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
