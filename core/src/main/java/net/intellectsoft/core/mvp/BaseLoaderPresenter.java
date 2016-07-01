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
            mvpView.showError(R.string.connection_error);
            return;
        }

        mvpView.showLoading();
        Subscription subscription = getDataObservable()
                .subscribe(data -> {
                    this.dataRef = new SoftReference<>(data);
                    onDataReceived(data);
                }, this::onError);
        subscriptions.add(subscription);
    }

    protected abstract Observable<D> getDataObservable();

    protected void onDataReceived(D data) {
        mvpView.setData(data);
        mvpView.hideLoading();
        mvpView.showContent();
    }

    protected void onError(Throwable throwable) {
        mvpView.hideLoading();
        mvpView.showError(getErrorMessageRes(throwable));
    }

    @StringRes
    protected abstract int getErrorMessageRes(Throwable throwable);

}
