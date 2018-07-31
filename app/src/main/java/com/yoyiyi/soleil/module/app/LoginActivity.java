package com.yoyiyi.soleil.module.app;

import android.content.Intent;

import com.yoyiyi.soleil.R;
import com.yoyiyi.soleil.base.BaseActivity;
import com.yoyiyi.soleil.constant.Constants;
import com.yoyiyi.soleil.module.home.MainActivity;
import com.yoyiyi.soleil.utils.PrefsUtils;

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/5/10 14:38
 * 描述:登录界面
 */

public class LoginActivity extends BaseActivity {



    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    protected void initWidget() {
        //名称监听

    }

    /**
     * 假登录
     */
    private void login() {


        PrefsUtils.getInstance().putBoolean(Constants.IS_LOGINED_FLAG, true);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void initToolbar() {
        super.initToolbar();
        mToolbar.setTitle("登录");
    }

}
