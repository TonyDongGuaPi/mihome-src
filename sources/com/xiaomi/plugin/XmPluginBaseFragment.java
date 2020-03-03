package com.xiaomi.plugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mi.global.bbs.utils.Constants;
import com.xiaomi.pluginbase.LayoutInflaterManager;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_constants.UrlConstants;

public abstract class XmPluginBaseFragment extends Fragment {
    static final String TAG = "XmPluginBaseFragment";
    protected Context mContext;
    protected boolean mEnableStartedForStat = false;
    String mFragmentName;
    protected String mIId = "";
    protected Intent mIntent;
    protected boolean mIsBack = false;
    protected LayoutInflater mLayoutInflater;
    boolean mNeedStatic = false;
    long mOnstartTime = 0;
    protected String mPageName = "";
    protected boolean mStartedForStat = false;
    protected String mUrl = "";
    XmPluginBaseActivity mXmPluginBaseActivity;
    protected XmPluginPackage mXmPluginPackage;

    public String getSpmref() {
        return "";
    }

    public abstract View onCreateView(LayoutInflater layoutInflater, @Nullable Bundle bundle);

    public XmPluginBaseFragment(XmPluginBaseFragment xmPluginBaseFragment, Intent intent) {
        LogUtils.d(TAG, getFragmentName() + " construct");
        this.mXmPluginPackage = xmPluginBaseFragment.mXmPluginPackage;
        this.mIntent = intent;
        this.mContext = xmPluginBaseFragment.mContext;
        this.mLayoutInflater = xmPluginBaseFragment.mLayoutInflater;
        if (this.mIntent != null) {
            this.mUrl = this.mIntent.getStringExtra("url");
        }
        if (TextUtils.isEmpty(this.mUrl)) {
            this.mUrl = xmPluginBaseFragment.getUrl();
        }
        if (!TextUtils.isEmpty(this.mUrl)) {
            this.mPageName = UrlConstants.parseShortPath(this.mUrl);
        }
    }

    public XmPluginBaseFragment(Context context, XmPluginPackage xmPluginPackage, Intent intent) {
        LogUtils.d(TAG, getFragmentName() + " construct");
        this.mIntent = intent;
        this.mXmPluginPackage = xmPluginPackage;
        this.mContext = new PluginContext(context, xmPluginPackage);
        this.mLayoutInflater = LayoutInflaterManager.getInstance().getLayoutInflater(this.mContext);
        if (this.mIntent != null) {
            this.mUrl = this.mIntent.getStringExtra("url");
            if (!TextUtils.isEmpty(this.mUrl)) {
                this.mPageName = UrlConstants.parseShortPath(this.mUrl);
            }
        }
    }

    public void enableNeedStatic() {
        this.mNeedStatic = true;
    }

    public XmPluginBaseFragment() {
        LogUtils.d(TAG, getFragmentName() + " construct");
    }

    public String getPageName() {
        return "main".equals(this.mPageName) ? Constants.WebViewURL.PAGE_HOME : this.mPageName;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setIID(String str) {
        this.mIId = str;
    }

    public String getIID() {
        return this.mIId;
    }

    public String getFragmentName() {
        if (this.mFragmentName == null) {
            this.mFragmentName = getClass().getSimpleName();
        }
        return this.mFragmentName;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        LogUtils.d(TAG, getFragmentName() + " onCreate");
    }

    public void onStartForStat() {
        this.mStartedForStat = true;
        LogUtils.d(TAG, getFragmentName() + " onStartForStat");
    }

    public void onStopForStat() {
        this.mStartedForStat = false;
        LogUtils.d(TAG, getFragmentName() + " onStopForStat");
    }

    public boolean isStartForStat() {
        return this.mStartedForStat;
    }

    public void enableStartedForStat(boolean z) {
        this.mEnableStartedForStat = z;
    }

    public void onStart() {
        super.onStart();
        LogUtils.d(TAG, getFragmentName() + " onStart");
    }

    public void onResume() {
        super.onResume();
        LogUtils.d(TAG, getFragmentName() + " onResume");
        if (!this.mEnableStartedForStat) {
            onStartForStat();
        }
        if (this.mNeedStatic) {
            this.mOnstartTime = System.currentTimeMillis();
            XmPluginHostApi.instance().addViewRecord(getPageName(), SPM.appendSpmrefToUrl(this.mUrl, XmPluginHostApi.instance().statGetSpmref(getSpmref())), this.mIId, getIsBackVal());
            this.mIsBack = true;
        }
    }

    public int getIsBackVal() {
        int i = this.mIsBack ? 1 : 2;
        this.mIsBack = true;
        return i;
    }

    public void onPause() {
        super.onPause();
        LogUtils.d(TAG, getFragmentName() + " onPause");
        if (!this.mEnableStartedForStat) {
            onStopForStat();
        }
        if (this.mNeedStatic) {
            XmPluginHostApi.instance().addViewEndRecord();
        }
    }

    public void onStop() {
        super.onStop();
        LogUtils.d(TAG, getFragmentName() + " onStop");
    }

    public void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG, getFragmentName() + " onDestroy");
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        LogUtils.d(TAG, getFragmentName() + " setUserVisibleHint:" + z);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        LogUtils.d(TAG, getFragmentName() + " onAttach");
        initilXmPlugActivity(activity);
    }

    public void onDetach() {
        super.onDetach();
        LogUtils.d(TAG, getFragmentName() + " onAttach");
    }

    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(TAG, getFragmentName() + " onDestroyView");
    }

    public XmPluginPackage getXmPluginPackage() {
        return this.mXmPluginPackage;
    }

    @NonNull
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle bundle) {
        return this.mLayoutInflater;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        LogUtils.d(TAG, getFragmentName() + " onCreateView");
        return onCreateView(this.mLayoutInflater, bundle);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        LogUtils.d(TAG, getFragmentName() + " onViewCreated");
    }

    public Context getContext() {
        return this.mContext;
    }

    public LayoutInflater getLayoutInflater(Bundle bundle) {
        super.getLayoutInflater(bundle);
        return this.mLayoutInflater;
    }

    /* access modifiers changed from: package-private */
    public void initilXmPlugActivity(Activity activity) {
        if (activity != null && (activity instanceof IXmPluginHostActivity)) {
            this.mXmPluginBaseActivity = ((IXmPluginHostActivity) activity).pluginBaseActivity();
            this.mXmPluginPackage = this.mXmPluginBaseActivity.pluginPackage();
            this.mIntent = this.mXmPluginBaseActivity.getIntent();
            this.mContext = this.mXmPluginBaseActivity;
            this.mLayoutInflater = LayoutInflaterManager.getInstance().getLayoutInflater(this.mContext);
            if (this.mIntent != null) {
                this.mUrl = this.mIntent.getStringExtra("url");
                if (!TextUtils.isEmpty(this.mUrl)) {
                    this.mPageName = UrlConstants.parseShortPath(this.mUrl);
                }
            }
        }
    }

    public void startActivity(Intent intent, Class<? extends XmPluginBaseActivity> cls) {
        startActivityForResult(intent, cls, -1);
    }

    public void startActivityForResult(Intent intent, Class<? extends XmPluginBaseActivity> cls, int i) {
        XmPluginHostApi.instance().startActivityForResult(getContext(), this.mXmPluginPackage, cls, intent, i);
    }
}
