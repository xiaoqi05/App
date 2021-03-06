package com.ps.app.ui.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.baidu.mapapi.map.MapView;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialdrawer.util.RecyclerViewCacheUtil;
import com.ps.app.R;
import com.ps.app.base.Constant;
import com.ps.app.support.Bean.CommonError;
import com.ps.app.support.Bean.PushMsgListBean;
import com.ps.app.support.Bean.VersionBean;
import com.ps.app.support.utils.PhoneInfo;
import com.ps.app.support.utils.ViewFindUtils;
import com.ps.app.ui.fragment.AssetsSeizedFragment;
import com.ps.app.ui.fragment.WarrantyStaffFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import im.fir.sdk.FIR;
import im.fir.sdk.VersionCheckCallback;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements OnTabSelectListener {
    private static final int SDK_PERMISSION_REQUEST = 127;
    public static final int MSG_SET_ALIAS = 1001;
    private static final int PROFILE_SETTING = 1;
    private static final String TAG = "MainActivity";
    private MapView mMapView;
    private String permissionInfo;
    private Context mContext = this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "资产查封", "保外人员"
    };

    private int badgeCount = 0;
    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private boolean opened = false;
    private Menu menu;
    //用于搜索界面
    private int id = 0;
    public static boolean isForeground = false;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";

    public static final String KEY_EXTRAS = "extras";
    private MyHandler myHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private WeakReference<MainActivity> activityWeakReference;

        public MyHandler(MainActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = activityWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case MSG_SET_ALIAS:
                        Log.i(TAG, "Set alias in handler.");
                        JPushInterface.setAliasAndTags(activity, (String) msg.obj, null, activity.mAliasCallback);
                        break;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.ly_toolbar);
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        //获取地图控件引用
        //  mMapView = (MapView) findViewById(R.id.bmapView);
        getPersimmions();
        initActionBar(-1, "警务小秘书");
        initTab();
        initDraw(mToolbar, savedInstanceState);
        initPush();
        setAlias();

    }

    private void initPush() {
        JPushInterface.init(getApplicationContext());
        JPushInterface.resumePush(getApplicationContext());
    }

    private void initTab() {
        View decorView = getWindow().getDecorView();
        ViewPager vp = ViewFindUtils.find(decorView, R.id.vp);
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i(TAG, "position" + position + "positionOffset" + positionOffset + "positionOffsetPixels" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.i(TAG, "position" + position);
                id = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.i(TAG, "state" + state);
            }
        });

        /**自定义部分属性*/
        SlidingTabLayout tabLayout_2 = ViewFindUtils.find(decorView, R.id.tl_2);


        tabLayout_2.setViewPager(vp);
        tabLayout_2.setOnTabSelectListener(this);
        /*vp.setCurrentItem(1);
        tabLayout_2.showDot(1);
*/
      /*  tabLayout_2.showMsg(3, 5);
        tabLayout_2.setMsgMargin(3, 0, 10);
        MsgView rtv_2_3 = tabLayout_2.getMsgView(3);
        if (rtv_2_3 != null) {
            rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }

        tabLayout_2.showMsg(5, 5);
        tabLayout_2.setMsgMargin(5, 0, 10);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        this.menu = menu;
      /*  // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
*/
        if (badgeCount > 0) {
            ActionItemBadge.update(this, menu.findItem(R.id.user_message), getResources().getDrawable(R.drawable.massage), ActionItemBadge.BadgeStyles.RED, badgeCount);
        } else {
            ActionItemBadge.update(this, menu.findItem(R.id.user_message), getResources().getDrawable(R.drawable.massage), ActionItemBadge.BadgeStyles.RED, Integer.MIN_VALUE);
        }

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user_search:
                Intent intents = new Intent(MainActivity.this, AssetSearchActivity.class);
                intents.putExtra("id", id);
                Log.i(TAG, id + "id" + id);
                startActivity(intents);
                return true;
            case R.id.user_message:
                //to message activity  
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(intent);
                badgeCount = Integer.MIN_VALUE;
                ActionItemBadge.update(item, getResources().getDrawable(R.drawable.massage), badgeCount);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return super.onMenuOpened(featureId, menu);
    }

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            /*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
                permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请	
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onDestroy() {
        // mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  mMapView.onResume();
        JPushInterface.resumePush(getApplicationContext());
        isForeground = true;
        setMsgNoteNum();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isForeground = false;
        // mMapView.onPause();
    }

    public void location(View v) {
        startActivity(new Intent(MainActivity.this, LocationActivity.class));
        //openActivityAnim();
    }

    @Override
    public void onTabSelect(int position) {

    }


    @Override
    public void onTabReselect(int position) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return AssetsSeizedFragment.getInstance(mTitles[0]);
            }
            if (position == 1) {
                return WarrantyStaffFragment.getInstance(mTitles[1]);
            }
            return mFragments.get(position);
        }

    }

    private void initDraw(Toolbar toolbar, Bundle savedInstanceState) {
        String phone = getSharePreference("").getString("phone", "");
        final IProfile profile3 = new ProfileDrawerItem().withName("警务小秘书").withEmail(phone).withIcon(R.drawable.profile2).withIdentifier(102);
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.colorPrimary)
             /*   .addProfiles(
                        profile3,
                        new ProfileSettingDrawerItem().withName("添加账户").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_plus).actionBar().paddingDp(5).colorRes(R.color.material_drawer_primary_text)).withIdentifier(PROFILE_SETTING),
                        new ProfileSettingDrawerItem().withName("账户管理").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        //sample usage of the onProfileChanged listener
                        //if the clicked item has the identifier 1 add a new profile ;)
                        if (profile instanceof IDrawerItem && ((IDrawerItem) profile).getIdentifier() == PROFILE_SETTING) {
                            int count = 100 + headerResult.getProfiles().size() + 1;
                            IProfile newProfile = new ProfileDrawerItem().withNameShown(true).withName("Batman" + count).withEmail("batman" + count + "@gmail.com").withIcon(R.drawable.profile5).withIdentifier(count);
                            if (headerResult.getProfiles() != null) {
                                //we know that there are 2 setting elements. set the new profile above them ;)
                                headerResult.addProfile(newProfile, headerResult.getProfiles().size() - 2);
                            } else {
                                headerResult.addProfiles(newProfile);
                            }
                        }
                        //false if you have not consumed the event and it should close the drawer
                        return false;
                    }
                })*/
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer

        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("修改密码").withIcon(getResources().getDrawable(R.drawable.change_password)).withIdentifier(0).withSelectable(false),
                        new PrimaryDrawerItem().withName("操作指南").withIcon(getResources().getDrawable(R.drawable.operations_guide)).withIdentifier(1).withSelectable(false),
                        new PrimaryDrawerItem().withName("关于").withIcon(getResources().getDrawable(R.drawable.about)).withIdentifier(2).withSelectable(false),
                        new PrimaryDrawerItem().withName("退出").withIcon(getResources().getDrawable(R.drawable.logout)).withIdentifier(3).withSelectable(false)
                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem
                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 0) {
                                intent = new Intent(MainActivity.this, ResetPasswordActivity.class);
                            } else if (drawerItem.getIdentifier() == 1) {
                                intent = new Intent(MainActivity.this, OperateInstructionActivity.class);
                            } else if (drawerItem.getIdentifier() == 2) {
                                intent = new Intent(MainActivity.this, AboutActivity.class);
                            } else if (drawerItem.getIdentifier() == 3) {
                                logout();
                            }
                            /*else if (drawerItem.getIdentifier() == 19) {
                                //showcase a simple collapsable functionality
                                if (opened) {
                                    //remove the items which are hidden
                                    result.removeItems(2000, 2001);
                                } else {
                                    int curPos = result.getPosition(drawerItem);
                                    result.addItemsAtPosition(
                                            curPos,
                                            new SecondaryDrawerItem().withName("CollapsableItem").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_8tracks).withIdentifier(2000),
                                            new SecondaryDrawerItem().withName("CollapsableItem 2").withLevel(2).withIcon(GoogleMaterial.Icon.gmd_8tracks).withIdentifier(2001)
                                    );
                                }
                                opened = !opened;
                                return true;
                            }*/
                            if (intent != null) {
                                MainActivity.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(false)
                .build();

        //if you have many different types of DrawerItems you can magically pre-cache those items to get a better scroll performance
        //make sure to init the cache after the DrawerBuilder was created as this will first clear the cache to make sure no old elements are in
        RecyclerViewCacheUtil.getInstance().withCacheSize(2).init(result);

        //only set the active selection or active profile if we do not recreate the activity
        if (savedInstanceState == null) {
            // set the selection to the item with the identifier 11
            result.setSelection(21, false);

            //set the active profile
            headerResult.setActiveProfile(profile3);
        }

        result.updateBadge(4, new StringHolder(10 + ""));
    }

    private void logout() {
        showNormalPrograssDailogBar(this, "正在注销");
        String cookie = getSharePreference("").getString("cookie", "");
        OkHttpUtils.get().addParams("sid", getSharePreference("").getInt("mid", -1) + "").url(Constant.LOGOUT_URL).addHeader("cookie", cookie).build().execute(new UserLogoutCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.i(TAG, e.toString());
                dismissNormalPrograssDailogBar();
            }

            @Override
            public void onResponse(CommonError response) {
                dismissNormalPrograssDailogBar();
                if (response.getCode() == 2000) {
                    showShortToast("注销成功");
                    getSharePreference("").edit().clear().commit();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    getSharePreference("").edit().putInt("date", calendar.get(Calendar.DAY_OF_YEAR)).apply();
                    Intent intent = new Intent(MainActivity.this, Splash.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public abstract class UserLogoutCallback extends Callback<CommonError> {
        @Override
        public CommonError parseNetworkResponse(Response response) throws IOException {
            String string = response.body().string();
            return new Gson().fromJson(string, CommonError.class);
        }
    }


    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            if (drawerItem instanceof Nameable) {
                Log.i("material-drawer", "DrawerItem: " + ((Nameable) drawerItem).getName() + " - toggleChecked: " + isChecked);
            } else {
                Log.i("material-drawer", "toggleChecked: " + isChecked);
            }
        }
    };

    public void checkUpdate(String firToken) {
        FIR.checkForUpdateInFIR("208f46b04efb5c2920c693094a0e22e5", new VersionCheckCallback() {
            @Override
            public void onSuccess(String versionJson) {
                Log.i("fir", "check from fir.im success! " + "\n" + versionJson);
                try {
                    PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
                    int versionCode = packageInfo.versionCode;
                    VersionBean versionBean = new Gson().fromJson(versionJson, VersionBean.class);
                    String name = versionBean.getName();
                    String changelog = versionBean.getChangelog();
                    final String direct_install_url = versionBean.getDirect_install_url();
                    String versionBeanVersion = versionBean.getVersion();
                    if (Integer.parseInt(versionBeanVersion) > versionCode) {
                        //有新版本，需要更新
                        showProgressDialog(name, direct_install_url, changelog);
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(Exception exception) {
                Log.i("fir", "check fir.im fail! " + "\n" + exception.getMessage());
            }

            @Override
            public void onStart() {
            }

            @Override
            public void onFinish() {
            }
        });
    }

    private void showProgressDialog(String name, final String url, String changeLog) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(name);
        builder.setMessage("检测到新版本，是否更新?\n" + changeLog);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                OkHttpUtils.get().url(url).build().execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "ps.apk") {
                    @Override
                    public void inProgress(float progress, long total) {
                        progressDialog.setProgress((int) (progress * 100));
                    }

                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(File response) {
                        progressDialog.dismiss();
                        //install apk
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.parse("file://" + response.getAbsolutePath()), "application/vnd.android.package-archive");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        //showLongToast(response.getAbsolutePath());
                    }
                });
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setMsgNoteNum() {
        String cookie = getSharePreference("").getString("cookie", "");
        OkHttpUtils.get().addParams("pn", String.valueOf(1)).addParams("ps", String.valueOf(50)).addParams("mid", getSharePreference("").getInt("mid", -1) + "").addHeader("cookie", cookie)
                .url(Constant.GET_MSG_URL).build().connTimeOut(10000).execute(new UserMsgCallback() {
            @Override
            public void onError(Call call, Exception e) {
                Log.i(TAG, e.toString());
                showShortToast("你的登录失效，请重新登录" + e.toString());
            }

            @Override
            public void onResponse(PushMsgListBean response) {
                badgeCount = 0;
                if (response.getCode() == 2000) {
                    //验证cookie有效
                    for (int j = 0; j < response.getData().getTotal(); j++) {
                        if (response.getData().getList().get(j).getUnread() == 0) {
                            ++badgeCount;
                        }
                    }
                    Log.i(TAG, "badgeCount" + badgeCount + "getTotal" + response.getData().getTotal());
                    // badgeCount = response.getData().getTotal();
                    if (badgeCount != 0) {
                        ActionItemBadge.update(MainActivity.this, menu.findItem(R.id.user_message), getResources().getDrawable(R.drawable.massage), ActionItemBadge.BadgeStyles.RED, badgeCount);
                    }
                    if (!opened) {
                        //只有第一次打开软件时 提示
                        //应用更新
                        checkUpdate(Constant.FIRTOKEN);
                        //检查应用有效期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        int today = calendar.get(Calendar.DAY_OF_YEAR);
                        int loginDay = getSharePreference("").getInt("date", 0);
                        int valid = 30 - (today - loginDay);
                        showShortToast("试用软件，" + valid + "天后到期");
                        opened = true;
                    }

                }
                if (response.getCode() == 2201) {
                    //getSharePreference("").edit().clear().apply();
                    Intent intent = new Intent(MainActivity.this, Splash.class);
                    startActivity(intent);
                    return;
                }
                if (response.getCode() == 2204) {
                    showShortToast("你的登录失效，请重新登录");
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });
    }

    public abstract class UserMsgCallback extends Callback<PushMsgListBean> {
        @Override
        public PushMsgListBean parseNetworkResponse(Response response) throws IOException {
            String string = response.body().string();
            return new Gson().fromJson(string, PushMsgListBean.class);
        }
    }

    private void setAlias() {
        //调用JPush API设置Alias
        myHandler.sendMessage(myHandler.obtainMessage(MSG_SET_ALIAS, PhoneInfo.getImei(MainActivity.this)));
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(TAG, logs);
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    if (isNetworkAvailable()) {
                        myHandler.sendMessageDelayed(myHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    } else {
                        Log.i(TAG, "No network");
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }
            Log.e(TAG, logs);
        }

    };


}
