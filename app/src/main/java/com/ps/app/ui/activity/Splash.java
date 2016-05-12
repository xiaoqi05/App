package com.ps.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;

import com.ps.app.R;

import java.lang.ref.WeakReference;

public class Splash extends BaseActivity {
    ProgressBar progressBar;
    public static final int DISMISS_PROGRESSBAR = 1;
    private MyHandler myHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private WeakReference<Splash> activityWeakReference;

        public MyHandler(Splash activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Splash activity = activityWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case DISMISS_PROGRESSBAR:
                        activity.showProgress(activity.progressBar, false);
                        if (activity.getSharePreference("").getBoolean("isLogin", true)) {
                            activity.startActivity(new Intent(activity, MainActivity.class));
                            activity.finish();
                        } else {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                            activity.finish();
                        }
                        break;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findView();
        initData();
    } 

    private void initData() {
        showProgress(progressBar, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    // myHandler.sendEmptyMessage(DISMISS_PROGRESSBAR);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void findView() {
        progressBar = (ProgressBar) findViewById(R.id.splash_progress);
    }

    public void toLogin(View v) {
        startActivity(new Intent(Splash.this, LoginActivity.class));
    }

    public void toMain(View v) {
        if (!getSharePreference("").getBoolean("isLogin", false)) {
            showShortToast("请先登录");
            return;
        }
        startActivity(new Intent(Splash.this, MainActivity.class));
    }

    public void http_test(View v) {
        startActivity(new Intent(Splash.this, LocationActivity.class));
    }


}
