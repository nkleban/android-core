package net.intellectsoft.core.mvp.presentation;

import java.lang.ref.SoftReference;

import rx.Observable;
import rx.Subscription;

public abstract class LoaderPresenter<D, V extends LoaderMvpView<D>> extends BasePresenter<V> {

    protected SoftReference<D> dataRef;

    public void requestData() {
        requestData(true);
    }

    public void requestData(boolean forceUpdate) {
        if (!forceUpdate && dataRef != null) {
            D data = dataRef.get();
            if (data != null) {
                onNext(data);
                return;
            }
        }

        mvpView.showLoading();
        Subscription subscription = getDataObservable()
                .subscribe(data -> {
                            this.dataRef = new SoftReference<>(data);
                            onNext(data);
                        },
                        this::onError,
                        this::onCompleted);
        subscriptions.add(subscription);
    }

    protected abstract Observable<D> getDataObservable();

    protected void onNext(D data) {
        mvpView.setData(data);
    }

    protected void onError(Throwable throwable) {
        mvpView.hideLoading();
        mvpView.showError(throwable);
    }

    protected void onCompleted() {
        mvpView.hideLoading();
    }

}
