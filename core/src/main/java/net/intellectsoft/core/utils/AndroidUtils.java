package net.intellectsoft.core.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class AndroidUtils {

    private static Context appContext;
    private static float density;

    public static void init(Context context) {
        appContext = context.getApplicationContext();
        density = appContext.getResources().getDisplayMetrics().density;
    }

    public static int toDp(int px) {
        return (int) (px / density);
    }

    public static int toPx(int dp) {
        return (int) (dp * density);
    }

    public static float toPx(float dp) {
        return dp * density;
    }

    public static void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) appContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) appContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static boolean isKeyboardShowed(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) appContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            return imm.isActive(view);
        }
        return false;
    }

    public static boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) appContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static boolean isPermissionGranted(Context context, String permission) {
        return ActivityCompat.checkSelfPermission(context, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    public static File getCacheDir() {
        String state = null;
        try {
            state = Environment.getExternalStorageState();
        } catch (Exception e) {
            Timber.e(e, "getExternalStorageState error");
        }
        if (state == null || state.startsWith(Environment.MEDIA_MOUNTED)) {
            try {
                File file = appContext.getExternalCacheDir();
                if (file != null) {
                    return file;
                }
            } catch (Exception e) {
                Timber.e(e, "getExternalCacheDir error");
            }
        }
        try {
            File file = appContext.getCacheDir();
            if (file != null) {
                return file;
            }
        } catch (Exception e) {
            Timber.e(e, "getCacheDir error");
        }
        return null;
    }

}
