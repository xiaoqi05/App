package com.ps.app.ui.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.ps.app.support.utils.NetWorkHelper;

public class BaseFragment extends Fragment {
    private ProgressDialog progress;

    public boolean isNetworkAvailable(Context context) {
        return NetWorkHelper.isNetworkAvailable(context);
    }

    public void showShortToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 获取 SharedPreferences
     *
     * @param config 值为空时使用默认值 config
     * @return
     */
    public SharedPreferences getSharePreference(Context context, String config) {
        if (TextUtils.isEmpty(config)) {
            return context.getSharedPreferences("config", Activity.MODE_PRIVATE);
        } else {
            return context.getSharedPreferences(config, Activity.MODE_PRIVATE);
        }
    }

    public void showSnackbar(View view, String content) {
        Snackbar.make(view, content, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }


    public void showNormalPrograssDailogBar(Context context, String msg) {
        progress = new ProgressDialog(context);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setMessage(msg);
        progress.show();
    }


    public void dismissNormalPrograssDailogBar() {
        if (progress == null) {
            return;
        }
        progress.dismiss();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}