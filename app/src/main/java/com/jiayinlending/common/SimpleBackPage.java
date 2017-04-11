package com.jiayinlending.common;


import com.jiayinlending.R;

public enum SimpleBackPage {
    SINGLEWEBVIEW(0, R.string.app_name, SingleWebViewFragment.class);


    public static int BASE_TOOLBAR_YES = 0;//使用
    public static int BASE_TOOLBAR_NO = 1;
    private int title;
    private Class<?> clz;
    private int value;

    //枚举类型构造方法只能是私有的
    private SimpleBackPage(int value, int title, Class<?> clz) {
        this.value = value;
        this.title = title;
        this.clz = clz;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static SimpleBackPage getPageByValue(int val) {
        //values()表示得到全部的枚举内容，然后用对象数组的形式用forEach输出
        for (SimpleBackPage p : values()) {
            if (p.getValue() == val)
                return p;
        }
        return null;
    }

}
