package net.intellectsoft.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseCompatFragment extends Fragment {

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //workaround for https://code.google.com/p/android/issues/detail?id=67482
        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean("isHidden", false)) {
                getFragmentManager().beginTransaction()
                        .hide(this)
                        .commit();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isHidden", isHidden());
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    protected abstract int getLayout();

}
