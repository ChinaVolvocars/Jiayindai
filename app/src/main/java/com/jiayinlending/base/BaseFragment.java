package com.jiayinlending.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseFragment extends Fragment implements View.OnClickListener {
    protected static final int STATE_NONE = 0;//没有状态
    protected static final int STATE_REFRESH = 1;//刷新状态

    protected int mState = STATE_NONE;

    protected FragmentActivity curActivity;
    private Unbinder bind;


    protected int getLayoutRes() {
        return 0;
    }

    protected void initView(View view) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        curActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        if (getLayoutRes() != 0) {
            view = inflater.inflate(getLayoutRes(), container, false);
        }
        
        initView(view);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (getActivity().getCurrentFocus() != null && getActivity().getCurrentFocus().getWindowToken() != null) {
                        manager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                return false;
            }
        });
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(this, view);
    }



    protected FragmentActivity getCurActivity() {
        if (isAdded()) {
            return getActivity();
        } else {
            return curActivity;
        }
    }






    protected String getUITag() {
        return "";
    }

    protected TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            onAfterTextChanged(s);
        }
    };

    protected void onAfterTextChanged(Editable s) {

    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onClick(View v) {

    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        return false;
    }

    public void net() {

    }


}
