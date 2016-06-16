package net.intellectsoft.core;

import android.app.Application;

import net.intellectsoft.core.utils.AndroidUtils;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidUtils.init(this);
        CalligraphyConfig.initDefault(createCalligraphyBuilder().build());
        initTimber();
    }

    protected CalligraphyConfig.Builder createCalligraphyBuilder() {
        CalligraphyConfig.Builder calligraphyBuilder = new CalligraphyConfig.Builder();
        calligraphyBuilder.setFontAttrId(R.attr.fontPath);
        String defaultFontPath = getDefaultFontPath();
        if (getDefaultFontPath() != null) {
            calligraphyBuilder.setDefaultFontPath(defaultFontPath);
        }
        return calligraphyBuilder;
    }

    protected String getDefaultFontPath() {
        return null;
    }

    protected void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}
