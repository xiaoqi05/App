package com.ps.app.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ps.app.support.utils.NetWorkHelper;

public class BaseActivity  extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    
    
    public boolean isNetworkAvailable(){
        return NetWorkHelper.isNetworkAvailable(BaseActivity.this);
    }
    
    public boolean isMobileDataEnable(){
        try {
            return NetWorkHelper.isMobileDataEnable(BaseActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isWifiDataEnable(){
        try {
            return NetWorkHelper.isWifiDataEnable(BaseActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void showShortToast(String msg){
        Toast.makeText(BaseActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String msg){
        Toast.makeText(BaseActivity.this,msg,Toast.LENGTH_LONG).show();
    }
    


    
}