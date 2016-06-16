package net.intellectsoft.core.mvp;

import android.support.annotation.StringRes;

public interface BaseLoaderMvpView<D> extends MvpView {

    void showLoading();

    void hideLoading();

    void showContent();

    void setData(D data);

    void showError(@StringRes int errorResId);

}
