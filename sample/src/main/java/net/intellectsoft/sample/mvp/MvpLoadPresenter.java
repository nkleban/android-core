package net.intellectsoft.sample.mvp;

import net.intellectsoft.core.mvp.BaseLoaderMvpView;
import net.intellectsoft.core.mvp.BaseLoaderPresenter;
import net.intellectsoft.sample.MockDataObject;
import net.intellectsoft.sample.R;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;


public class MvpLoadPresenter
        extends BaseLoaderPresenter<List<MockDataObject>, BaseLoaderMvpView<List<MockDataObject>>> {

    @Override
    protected Observable<List<MockDataObject>> getDataObservable() {
        ArrayList<MockDataObject> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            items.add(new MockDataObject("Item " + (i + 1)));
        }
        return Observable.just(items);
    }

    @Override
    protected int getErrorMessageRes(Throwable throwable) {
        return R.string.connection_error;
    }
}
