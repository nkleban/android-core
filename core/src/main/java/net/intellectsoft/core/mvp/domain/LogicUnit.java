package net.intellectsoft.core.mvp.domain;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class LogicUnit {

    protected final Scheduler workerThreadScheduler;
    protected final Scheduler uiThreadScheduler;
    private final Observable.Transformer<Object, Object> schedulersTransformer;

    public LogicUnit() {
        this(Schedulers.io(), AndroidSchedulers.mainThread());
    }

    public LogicUnit(Scheduler workerThreadScheduler, Scheduler uiThreadScheduler) {
        this.workerThreadScheduler = workerThreadScheduler;
        this.uiThreadScheduler = uiThreadScheduler;
        this.schedulersTransformer = observable -> observable.subscribeOn(workerThreadScheduler)
                .observeOn(uiThreadScheduler);
    }

    @SuppressWarnings("unchecked")
    public <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

}
