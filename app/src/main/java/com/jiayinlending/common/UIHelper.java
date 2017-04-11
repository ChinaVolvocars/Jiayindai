package com.jiayinlending.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;


public class UIHelper {
    public static final String CELL_PHONE = "cellphone";
    public static String HF_URL_KEY = "url";
    public static String HF_TITLE_KEY = "title";
    public static String HF_CONTENT_KEY = "content";
    public static final String SIX_PWD = "TransactionPassword";
    private Context context;

   

    //不带参数
    public static void showSimpleBack(Context context, SimpleBackPage page) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }

    //不带参数,自定义customView
    public static void showSimpleBack(Context context, SimpleBackPage page, int customView) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_CUSTOM_VIEW, customView);
        context.startActivity(intent);
    }

    //带参数
    public static void showSimpleBack(Context context, SimpleBackPage page, Bundle args) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }

    //带参数
    public static void showSimpleBack(Context context, SimpleBackPage page, Bundle args, int customView) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_CUSTOM_VIEW, customView);
        context.startActivity(intent);
    }

    //需要返回结果，不带参数
    public static void showSimpleBackForResult(Activity context, int requestCode, SimpleBackPage page) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivityForResult(intent, requestCode);
    }

    //需要返回结果，带参数
    public static void showSimpleBackForResult(Activity context, int requestCode, SimpleBackPage page, Bundle args) {
        Intent intent = new Intent(context, SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        context.startActivityForResult(intent, requestCode);
    }

    //Fragment中跳转,需要返回结果，带参数
    public static void showSimpleBackForResult(Fragment fragment, int requestCode, SimpleBackPage page, Bundle args) {
        Intent intent = new Intent(fragment.getActivity(),
                SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        fragment.startActivityForResult(intent, requestCode);
    }

    //Fragment中跳转,需要返回结果，不带参数
    public static void showSimpleBackForResult(Fragment fragment, int requestCode, SimpleBackPage page) {
        Intent intent = new Intent(fragment.getActivity(),
                SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        fragment.startActivityForResult(intent, requestCode);
    }

    //Fragment中跳转,需要返回结果，不带参数
    public static void showSimpleBackForResult(Fragment fragment, int requestCode, SimpleBackPage page, Bundle args, int customView) {
        Intent intent = new Intent(fragment.getActivity(),
                SimpleBackActivity.class);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        intent.putExtra(SimpleBackActivity.BUNDLE_KEY_CUSTOM_VIEW, customView);
        fragment.startActivityForResult(intent, requestCode);
    }


}