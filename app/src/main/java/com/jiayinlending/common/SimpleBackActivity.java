package com.jiayinlending.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;


import com.jiayinlending.R;
import com.jiayinlending.base.BaseActivity;
import com.jiayinlending.base.BaseFragment;

import java.lang.ref.WeakReference;

public class SimpleBackActivity extends BaseActivity {

    private static final String TAG = SimpleBackActivity.class.getSimpleName();

    public final static String BUNDLE_KEY_PAGE = "BUNDLE_KEY_PAGE";
    public final static String BUNDLE_KEY_ARGS = "BUNDLE_KEY_ARGS";
    public final static String BUNDLE_KEY_CUSTOM_VIEW = "BUNDLE_KEY_CUSTOM_VIEW";

    private WeakReference<Fragment> mFragment;

    private Intent data;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onBeforeSetContentLayout() {
        super.onBeforeSetContentLayout();
        data = getIntent();
        if (data == null) {
            throw new RuntimeException(
                    "you must provide a page info to display");
        }
        initLayoutRes();
    }


    protected void initLayoutRes() {
        if (data != null) {
            Bundle args = data.getBundleExtra(BUNDLE_KEY_ARGS);
            int layoutRes = 0;
            if (args != null)
                layoutRes = args.getInt(BUNDLE_KEY_CUSTOM_VIEW);
            if (layoutRes == SimpleBackPage.BASE_TOOLBAR_YES) {
                setLayoutId(R.layout.activity_simple_fragment);
            } else {
                setLayoutId(layoutRes);
            }
        }
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        int pageValue = data.getIntExtra(BUNDLE_KEY_PAGE, 0);
        SimpleBackPage page = SimpleBackPage.getPageByValue(pageValue);
        if (page == null) {
            throw new IllegalArgumentException("can not find page by value:"
                    + pageValue);
        }
        setActionBarTitle(page.getTitle());

        try {
            Fragment fragment = (Fragment) page.getClz().newInstance();

            Bundle args = data.getBundleExtra(BUNDLE_KEY_ARGS);

            if (args != null) {
                fragment.setArguments(args);
            }

            FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction();
            trans.replace(R.id.container, fragment, TAG);
            trans.commit();

            mFragment = new WeakReference<Fragment>(fragment);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "generate fragment error. by value:" + pageValue);
        }
    }

    @Override
    public void onBackPressed() {
        if (mFragment != null && mFragment.get() != null && mFragment.get() instanceof BaseFragment) {
            BaseFragment bf = (BaseFragment) mFragment.get();
            if (!bf.onBackPressed()) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        super.onActivityResult(arg0, arg1, arg2);
//        UMShareAPI.get(this).onActivityResult(arg0, arg1, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
