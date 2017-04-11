package com.jiayinlending.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;


import com.jiayinlending.R;

import java.lang.reflect.Field;


public class BaseActivity extends AppCompatActivity {
    private boolean isVisible;

    private ActionBar mActionBar;
    protected LayoutInflater mInflater;
    private Toolbar mToolBar;
    private TextView mToolBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBeforeSetContentLayout();
        setContentView(getLayoutId());
        mInflater = getLayoutInflater();
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mToolBar.setVisibility(View.VISIBLE);
        setSupportActionBar(mToolBar);
        mToolBarTitle = (TextView) mToolBar.findViewById(R.id.tv_toolbar_title);
        mActionBar = getSupportActionBar();
        if (hasActionBar()) {
            initActionBar(mToolBar, mActionBar);
        }
        init(savedInstanceState);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    protected void initActionBar(Toolbar toolbar, ActionBar actionBar) {
        if (toolbar == null)
            return;
//        if (hasBackButton()) {
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayHomeAsUpEnabled(false);
        //隐藏默认的标题
        actionBar.setDisplayShowTitleEnabled(false);
        TextView back = (TextView) toolbar.findViewById(R.id.tv_toolbar_back);
        if (back != null) {
            if (!hasBackButton()) {
                back.setVisibility(View.GONE);
            } else {
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        }
        mToolBarTitle = (TextView) toolbar
                .findViewById(R.id.tv_toolbar_title);
        if (mToolBarTitle == null) {
            throw new IllegalArgumentException(
                    "can not find R.id.tv_actionbar_title in customView");
        }

        int titleRes = getActionBarTitle();
        if (titleRes != 0) {
            mToolBarTitle.setText(titleRes);
        }
    }

    private void initTextView() {
        ViewConfiguration configuration = ViewConfiguration.get(this);

        Class claz = configuration.getClass();

        try {

            Field field = claz.getDeclaredField("mFadingMarqueeEnabled");

            field.setAccessible(true);

            field.set(configuration, true);

        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void init(Bundle savedInstanceState) {
    }

    protected boolean hasBackButton() {
        return false;
    }

    protected int getActionBarCustomView() {
        return 0;
    }


    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }

    protected int getActionBarTitle() {
        return R.string.app_name;
    }

    protected void onBeforeSetContentLayout() {
        isVisible = true;
        initTextView();
    }

    protected boolean hasActionBar() {
        return true;
    }


    private int layoutId;

    protected int getLayoutId() {
        return layoutId;
    }

    protected void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public void setActionBarTitle(int resId) {
        if (resId != 0) {
            setActionBarTitle(getString(resId));
        }
    }

    public void setActionBarTitle(String title) {
        if (hasActionBar()) {
            if (mToolBarTitle != null) {
                mToolBarTitle.setText(title);
            }
            mActionBar.setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected Toolbar getmToolBar() {
        return mToolBar;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
