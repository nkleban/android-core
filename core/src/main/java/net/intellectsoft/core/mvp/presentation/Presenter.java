package net.intellectsoft.core.mvp.presentation;

public interface Presenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

}
