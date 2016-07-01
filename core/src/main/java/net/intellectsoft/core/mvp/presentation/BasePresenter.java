package net.intellectsoft.core.mvp.presentation;

import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<V extends MvpView> implements Presenter<V> {

    protected final CompositeSubscription subscriptions = new CompositeSubscription();
    protected V mvpView;

    @Override
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void detachView() {
        mvpView = null;
        subscriptions.clear();
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public V getMvpView() {
        return mvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

}
