package net.intellectsoft.core.mvp;

public interface Presenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

}
