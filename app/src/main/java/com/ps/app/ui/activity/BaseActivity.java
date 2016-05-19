package com.ps.app.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ps.app.R;
import com.ps.app.support.utils.NetWorkHelper;

import cn.jpush.android.api.JPushInterface;

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog progress;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    public boolean isNetworkAvailable() {
        return NetWorkHelper.isNetworkAvailable(BaseActivity.this);
    }

    public boolean isMobileDataEnable() {
        try {
            return NetWorkHelper.isMobileDataEnable(BaseActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isWifiDataEnable() {
        try {
            return NetWorkHelper.isWifiDataEnable(BaseActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void showShortToast(String msg) {
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String msg) {
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_LONG).show();
    }


    public void activityAnim() {
        overridePendingTransition(R.anim.right_in,
                R.anim.right_out);
    }

    public void openActivityAnim() {
        overridePendingTransition(R.anim.left_in,
                R.anim.left_out);
    }


    /**
     * 带动画效果的关闭
     */
    @Override
    public void finish() {
        super.finish();
    }

    /**
     * 系统默认关闭
     */
    public void defaultFinish() {
        super.finish();
    }

    /**
     * 初始化actionbar
     *
     * @param logo  -1为不设置logo
     * @param title title
     */
    public void initActionBar(int logo, String title) {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        if (logo != -1) {
            actionBar.setIcon(logo);
        }
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        if (!TextUtils.isEmpty(title)) {
            actionBar.setTitle(title);
        }
    }


    public void showProgress(final ProgressBar mProgressView, final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);


            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 获取 SharedPreferences
     *
     * @param config 值为空时使用默认值 config
     * @return
     */
    public SharedPreferences getSharePreference(String config) {
        if (TextUtils.isEmpty(config)) {
            return getSharedPreferences("config", MODE_PRIVATE);
        } else {
            return getSharedPreferences(config, MODE_PRIVATE);
        }
    }

    public void showSnackbar(View view, String content) {
        Snackbar.make(view, content, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    public void showNormalPrograssDailogBar(Context context,String msg) {
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
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}