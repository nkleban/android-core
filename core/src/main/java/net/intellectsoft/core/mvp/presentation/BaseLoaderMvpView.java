package net.intellectsoft.core.mvp.presentation;

public interface BaseLoaderMvpView extends MvpView {

    void showLoading();

    void hideLoading();

    void showError(Throwable t);

}
