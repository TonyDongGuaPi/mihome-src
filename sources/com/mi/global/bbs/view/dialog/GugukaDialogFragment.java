package com.mi.global.bbs.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.adapter.PrizeAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.GiftInfo;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.mi.global.bbs.utils.ScratchUtil;
import com.mi.global.bbs.view.XCGuaguakaView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.List;
import org.json.JSONObject;

public class GugukaDialogFragment extends DialogFragment {
    public static final int CODE_NEED_SHARE = 1003;
    public static final int CODE_NOT_LOGIN = 1001;
    public static final int CODE_TIME_UP = 1002;
    public static final int RES_CODE_SUCCEED = 0;
    public static final String TAG = "GugukaDialogFragment";
    /* access modifiers changed from: private */
    public BaseActivity activity;
    private ImageButton mBtnClose;
    private Button mBtnRetry;
    private FrameLayout mDialogContent;
    private ProgressBar mProgressBar;
    private TextView mTvTitle;
    private boolean shouldDismissDialog = true;
    private XCGuaguakaView xcGuaguakaView;

    public void onDismiss(DialogInterface dialogInterface) {
    }

    public void onAttach(Activity activity2) {
        super.onAttach(activity2);
        if (activity2 instanceof BaseActivity) {
            this.activity = (BaseActivity) activity2;
        }
    }

    public static GugukaDialogFragment newInstance(String str) {
        GugukaDialogFragment gugukaDialogFragment = new GugukaDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        gugukaDialogFragment.setArguments(bundle);
        return gugukaDialogFragment;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = this.activity.getLayoutInflater().inflate(R.layout.bbs_fragment_event_dialog, (ViewGroup) null);
        this.mDialogContent = (FrameLayout) inflate.findViewById(R.id.dialog_content);
        this.mProgressBar = (ProgressBar) inflate.findViewById(R.id.progress_bar);
        this.mTvTitle = (TextView) inflate.findViewById(R.id.tv_gift_dialog_title);
        this.mTvTitle.setText(ScratchUtil.getGameTitle());
        this.mBtnClose = (ImageButton) inflate.findViewById(R.id.close_btn);
        this.mBtnClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GugukaDialogFragment.this.showDialog(false);
            }
        });
        this.mBtnRetry = (Button) inflate.findViewById(R.id.retry_btn);
        this.mProgressBar.setVisibility(0);
        if (!LoginManager.getInstance().hasLogin()) {
            this.shouldDismissDialog = false;
            addLoginView();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setView(inflate);
        AlertDialog create = builder.create();
        create.getWindow().getDecorView().setBackgroundResource(17170445);
        return create;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void setRetryListener(View.OnClickListener onClickListener) {
        if (this.activity != null && onClickListener != null) {
            this.mBtnRetry.setOnClickListener(onClickListener);
            this.mBtnRetry.setVisibility(0);
            this.mProgressBar.setVisibility(8);
        }
    }

    public void setLoading(boolean z, boolean z2) {
        if (this.activity != null) {
            if (z) {
                if (z2) {
                    this.mDialogContent.removeAllViews();
                }
                this.mBtnRetry.setVisibility(8);
                this.mProgressBar.setVisibility(0);
                return;
            }
            this.mProgressBar.setVisibility(8);
        }
    }

    public void addView(View view) {
        if (this.activity != null) {
            if (view != null) {
                this.mDialogContent.removeAllViews();
                this.mDialogContent.addView(view);
            }
            this.mBtnRetry.setVisibility(8);
            this.mProgressBar.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void receiveCardGift(boolean z, final int i, final View view) {
        if (view != null) {
            if (z) {
                setLoading(true, false);
            }
            final Button button = (Button) view.findViewById(R.id.btn_card_share);
            button.setEnabled(true);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    GugukaDialogFragment.this.setLoading(true, true);
                    if (i > 0) {
                        button.setEnabled(false);
                        GugukaDialogFragment.this.activity.goShareCardEventFB();
                        return;
                    }
                    GugukaDialogFragment.this.addNoChanceView();
                }
            });
            final Button button2 = (Button) view.findViewById(R.id.btn_card_myprize);
            final TextView textView = (TextView) view.findViewById(R.id.text_game_share_tip);
            button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    GugukaDialogFragment.this.addPrizeListView();
                }
            });
            button.setVisibility(8);
            button2.setVisibility(8);
            final int i2 = i;
            final View view2 = view;
            ApiClient.getCarEventGift(ConnectionHelper.getCardConfirmUrl()).subscribe(new Consumer<JsonObject>() {
                public void accept(@NonNull JsonObject jsonObject) throws Exception {
                    try {
                        if (new JSONObject(jsonObject.toString()).optInt("code", -1) == 0) {
                            GugukaDialogFragment.this.setLoading(false, false);
                            button.setVisibility(0);
                            button2.setVisibility(0);
                            textView.setVisibility(0);
                            if (i2 > 0) {
                                button.setText(R.string.go_to_share);
                            } else {
                                button.setText(R.string.go_to_share);
                            }
                        } else {
                            GugukaDialogFragment.this.setRetryListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    GugukaDialogFragment.this.receiveCardGift(true, i2, view2);
                                }
                            });
                        }
                    } catch (Exception unused) {
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    GugukaDialogFragment.this.setRetryListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            GugukaDialogFragment.this.receiveCardGift(true, i, view);
                        }
                    });
                }
            });
        }
    }

    public void showDialog(boolean z) {
        if (getDialog() != null) {
            if (z && !getDialog().isShowing()) {
                getDialog().show();
            }
            if (!z && getDialog().isShowing()) {
                getDialog().dismiss();
            }
        }
    }

    public void shareGainChance() {
        ApiClient.getCarEventGift(ConnectionHelper.getCardEventShareUrl()).subscribe(new Consumer<JsonObject>() {
            public void accept(@NonNull JsonObject jsonObject) throws Exception {
                try {
                    if (new JSONObject(jsonObject.toString()).optInt("code", -1) == 0) {
                        GugukaDialogFragment.this.addGuakaView(true);
                    } else {
                        GugukaDialogFragment.this.setRetryListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                GugukaDialogFragment.this.addGuakaView(true);
                            }
                        });
                    }
                } catch (Exception unused) {
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                GugukaDialogFragment.this.setRetryListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        GugukaDialogFragment.this.addGuakaView(true);
                    }
                });
            }
        });
    }

    public void addGuakaView(final boolean z) {
        setLoading(true, true);
        if (z) {
            showDialog(true);
        }
        ApiClient.getCarEventGift(ConnectionHelper.getCardEventGetUrl()).subscribe(new Consumer<JsonObject>() {
            public void accept(@NonNull JsonObject jsonObject) throws Exception {
                GugukaDialogFragment.this.handleGetGiftInfo(z, jsonObject);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                GugukaDialogFragment.this.showDialog(false);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleGetGiftInfo(final boolean z, JsonObject jsonObject) {
        JSONObject optJSONObject;
        try {
            JSONObject jSONObject = new JSONObject(jsonObject.toString());
            GiftInfo giftInfo = null;
            int i = -1;
            int optInt = jSONObject.optInt("code", -1);
            if (optInt == 0 && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
                i = optJSONObject.optInt("chance", -1);
                giftInfo = new GiftInfo();
                giftInfo.setName(optJSONObject.optString("name"));
                giftInfo.setDesc(optJSONObject.optString("desc"));
            }
            if (z || i >= 0 || optInt == 0) {
                if (optInt == 0) {
                    if (i >= 0) {
                        addView(getGuakaView(giftInfo, i));
                    } else {
                        addNoChanceView();
                    }
                } else if (optInt == 1001) {
                    addLoginView();
                } else if (optInt == 1002) {
                    addNoChanceView();
                } else if (optInt == 1003) {
                    addNeedShareView();
                } else {
                    setRetryListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            GugukaDialogFragment.this.addGuakaView(z);
                        }
                    });
                }
                showDialog(true);
                return;
            }
            showDialog(false);
        } catch (Exception unused) {
            showDialog(false);
        }
    }

    public void onStart() {
        super.onStart();
        if (this.shouldDismissDialog && getDialog() != null) {
            getDialog().dismiss();
        }
        this.shouldDismissDialog = true;
    }

    public void onStop() {
        if (getDialog() != null && getDialog().isShowing()) {
            this.shouldDismissDialog = false;
        }
        super.onStop();
    }

    public View getGuakaView(GiftInfo giftInfo, final int i) {
        if (this.activity == null) {
            return null;
        }
        final View inflate = this.activity.getLayoutInflater().inflate(R.layout.bbs_fragment_event_guaguaka_dialog, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.text_game_time)).setText(getString(R.string.str_campaing_period) + ScratchUtil.getGameDateInfo());
        ((Button) inflate.findViewById(R.id.btn_card_share)).setVisibility(8);
        ((Button) inflate.findViewById(R.id.btn_card_myprize)).setVisibility(8);
        ((TextView) inflate.findViewById(R.id.text_game_share_tip)).setVisibility(8);
        this.xcGuaguakaView = (XCGuaguakaView) inflate.findViewById(R.id.ggk);
        this.xcGuaguakaView.setGiftContent(giftInfo);
        this.xcGuaguakaView.setmOnCanGetGiftListener(new XCGuaguakaView.onCanGetGiftListener() {
            public void onCanGetGift() {
                GugukaDialogFragment.this.receiveCardGift(false, i, inflate);
            }
        });
        return inflate;
    }

    public void addPrizeListView() {
        setLoading(true, true);
        ApiClient.getCarEventGift(ConnectionHelper.getCardEventlistUrl()).subscribe(new Consumer<JsonObject>() {
            public void accept(@NonNull JsonObject jsonObject) throws Exception {
                try {
                    JSONObject jSONObject = new JSONObject(jsonObject.toString());
                    if (jSONObject.optInt("code", -1) == 0) {
                        String optString = jSONObject.optString("data");
                        GugukaDialogFragment.this.addView(GugukaDialogFragment.this.getPrizeListView((List) new Gson().fromJson(optString, new TypeToken<List<GiftInfo>>() {
                        }.getType())));
                        return;
                    }
                    GugukaDialogFragment.this.setRetryListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            GugukaDialogFragment.this.addPrizeListView();
                        }
                    });
                } catch (Exception unused) {
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                GugukaDialogFragment.this.setRetryListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        GugukaDialogFragment.this.addPrizeListView();
                    }
                });
            }
        });
    }

    public View getPrizeListView(List<GiftInfo> list) {
        if (this.activity == null) {
            return null;
        }
        View inflate = this.activity.getLayoutInflater().inflate(R.layout.bbs_fragment_event_prize_list_dialog, (ViewGroup) null);
        ListView listView = (ListView) inflate.findViewById(R.id.prize_list);
        TextView textView = (TextView) inflate.findViewById(R.id.empty);
        if (list == null || list.size() <= 0) {
            listView.setVisibility(8);
        } else {
            listView.setAdapter(new PrizeAdapter(this.activity, list));
            textView.setVisibility(8);
        }
        ((Button) inflate.findViewById(R.id.prize_btn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GugukaDialogFragment.this.addGuakaView(true);
            }
        });
        return inflate;
    }

    public void addLoginView() {
        setLoading(true, true);
        addView(getLoginView());
    }

    public View getLoginView() {
        if (this.activity == null) {
            return null;
        }
        View inflate = this.activity.getLayoutInflater().inflate(R.layout.bbs_fragment_event_login_dialog, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.text_content)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GugukaDialogFragment.this.activity.gotoAccount();
            }
        });
        ((Button) inflate.findViewById(R.id.login_btn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GugukaDialogFragment.this.activity.gotoAccount();
            }
        });
        return inflate;
    }

    public void addNoChanceView() {
        addView(getNoChanceView());
    }

    public View getNoChanceView() {
        if (this.activity == null) {
            return null;
        }
        View inflate = this.activity.getLayoutInflater().inflate(R.layout.bbs_fragment_event_no_chance_dialog, (ViewGroup) null);
        ((Button) inflate.findViewById(R.id.btn_tomorrow_retry)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GugukaDialogFragment.this.showDialog(false);
            }
        });
        ((Button) inflate.findViewById(R.id.btn_no_chance_my_prize)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GugukaDialogFragment.this.addPrizeListView();
            }
        });
        return inflate;
    }

    public void addNeedShareView() {
        addView(getNeedShareView());
    }

    public View getNeedShareView() {
        if (this.activity == null) {
            return null;
        }
        View inflate = this.activity.getLayoutInflater().inflate(R.layout.bbs_fragment_event_need_share_dialog, (ViewGroup) null);
        ((Button) inflate.findViewById(R.id.btn_go_share)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GugukaDialogFragment.this.activity.goShareCardEventFB();
            }
        });
        ((Button) inflate.findViewById(R.id.btn_no_chance_my_prize)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GugukaDialogFragment.this.addPrizeListView();
            }
        });
        return inflate;
    }
}
