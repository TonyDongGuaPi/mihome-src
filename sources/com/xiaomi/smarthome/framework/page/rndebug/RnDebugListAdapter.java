package com.xiaomi.smarthome.framework.page.rndebug;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class RnDebugListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final int f16954a = 0;
    private static final int b = 1;
    private Context c;
    /* access modifiers changed from: private */
    public boolean d = false;
    /* access modifiers changed from: private */
    public List<JSONObject> e = new ArrayList();
    /* access modifiers changed from: private */
    public OnRnDebugListClickListener f;

    public interface OnRnDebugListClickListener {
        void a(View view);

        void a(View view, int i);

        void a(View view, int i, boolean z);

        void b(View view, int i);
    }

    public void onClick(View view) {
    }

    public RnDebugListAdapter(Context context, List<JSONObject> list) {
        this.c = context;
        a(list, this.e);
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i != 0) {
            return new RnDebugItemAutoDebugViewHolder(LayoutInflater.from(this.c).inflate(R.layout.item_rn_debug_list_plugin_debug, viewGroup, false));
        }
        View inflate = LayoutInflater.from(this.c).inflate(R.layout.item_rn_debug_list_plugin, viewGroup, false);
        inflate.setOnClickListener(this);
        return new RnDebugItemViewHolder(inflate);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof RnDebugItemViewHolder) {
            ((RnDebugItemViewHolder) viewHolder).a(this.e.get(i), i);
        } else if (viewHolder instanceof RnDebugItemAutoDebugViewHolder) {
            ((RnDebugItemAutoDebugViewHolder) viewHolder).a();
        }
    }

    public void a(List<JSONObject> list) {
        a(list, this.e);
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void a(OnRnDebugListClickListener onRnDebugListClickListener) {
        this.f = onRnDebugListClickListener;
    }

    public int getItemCount() {
        if (this.e == null || this.e.size() <= 0) {
            return 0;
        }
        return this.e.size() + 1;
    }

    public int getItemViewType(int i) {
        return (this.e == null || this.e.size() <= 0 || i != this.e.size()) ? 0 : 1;
    }

    public class RnDebugItemViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f16957a;
        CheckBox b;
        TextView c;
        TextView d;
        TextView e;

        public RnDebugItemViewHolder(View view) {
            super(view);
            this.f16957a = view.findViewById(R.id.layout_item_rn_debug_left);
            this.b = (CheckBox) view.findViewById(R.id.cb_debug_plugin_check);
            this.c = (TextView) view.findViewById(R.id.tv_debug_package_name);
            this.d = (TextView) view.findViewById(R.id.tv_debug_device_model);
            this.e = (TextView) view.findViewById(R.id.tv_debug_plugin_delete);
        }

        public void a(JSONObject jSONObject, final int i) {
            boolean z;
            String str;
            String str2;
            try {
                str2 = jSONObject.getString(RnDebugConstant.f16953a);
                try {
                    str = jSONObject.getString(RnDebugConstant.b);
                } catch (JSONException e2) {
                    e = e2;
                    str = null;
                    RnPluginLog.a(e.toString());
                    z = false;
                    RnPluginLog.a("name: " + str2 + "   model: " + str + "   isCheck: " + z);
                    TextView textView = this.c;
                    StringBuilder sb = new StringBuilder();
                    sb.append("package: ");
                    sb.append(str2);
                    textView.setText(sb.toString());
                    this.d.setText("model: " + str);
                    this.b.setChecked(z);
                    this.b.setEnabled(RnDebugListAdapter.this.d);
                    this.f16957a.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (RnDebugListAdapter.this.f != null) {
                                RnDebugListAdapter.this.f.a(RnDebugItemViewHolder.this.f16957a, i);
                            }
                        }
                    });
                    this.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                            if (RnDebugListAdapter.this.d && RnDebugListAdapter.this.f != null) {
                                RnDebugListAdapter.this.f.a(compoundButton, i, z);
                            }
                        }
                    });
                    this.e.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (RnDebugListAdapter.this.d) {
                                if (RnDebugListAdapter.this.f != null) {
                                    RnDebugListAdapter.this.f.b(view, i);
                                }
                                RnDebugListAdapter.this.e.remove(i);
                                RnDebugListAdapter.this.notifyDataSetChanged();
                            }
                        }
                    });
                }
                try {
                    z = jSONObject.getBoolean(RnDebugConstant.c);
                } catch (JSONException e3) {
                    e = e3;
                    RnPluginLog.a(e.toString());
                    z = false;
                    RnPluginLog.a("name: " + str2 + "   model: " + str + "   isCheck: " + z);
                    TextView textView2 = this.c;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("package: ");
                    sb2.append(str2);
                    textView2.setText(sb2.toString());
                    this.d.setText("model: " + str);
                    this.b.setChecked(z);
                    this.b.setEnabled(RnDebugListAdapter.this.d);
                    this.f16957a.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (RnDebugListAdapter.this.f != null) {
                                RnDebugListAdapter.this.f.a(RnDebugItemViewHolder.this.f16957a, i);
                            }
                        }
                    });
                    this.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                            if (RnDebugListAdapter.this.d && RnDebugListAdapter.this.f != null) {
                                RnDebugListAdapter.this.f.a(compoundButton, i, z);
                            }
                        }
                    });
                    this.e.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (RnDebugListAdapter.this.d) {
                                if (RnDebugListAdapter.this.f != null) {
                                    RnDebugListAdapter.this.f.b(view, i);
                                }
                                RnDebugListAdapter.this.e.remove(i);
                                RnDebugListAdapter.this.notifyDataSetChanged();
                            }
                        }
                    });
                }
            } catch (JSONException e4) {
                e = e4;
                str2 = null;
                str = null;
                RnPluginLog.a(e.toString());
                z = false;
                RnPluginLog.a("name: " + str2 + "   model: " + str + "   isCheck: " + z);
                TextView textView22 = this.c;
                StringBuilder sb22 = new StringBuilder();
                sb22.append("package: ");
                sb22.append(str2);
                textView22.setText(sb22.toString());
                this.d.setText("model: " + str);
                this.b.setChecked(z);
                this.b.setEnabled(RnDebugListAdapter.this.d);
                this.f16957a.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (RnDebugListAdapter.this.f != null) {
                            RnDebugListAdapter.this.f.a(RnDebugItemViewHolder.this.f16957a, i);
                        }
                    }
                });
                this.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        if (RnDebugListAdapter.this.d && RnDebugListAdapter.this.f != null) {
                            RnDebugListAdapter.this.f.a(compoundButton, i, z);
                        }
                    }
                });
                this.e.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (RnDebugListAdapter.this.d) {
                            if (RnDebugListAdapter.this.f != null) {
                                RnDebugListAdapter.this.f.b(view, i);
                            }
                            RnDebugListAdapter.this.e.remove(i);
                            RnDebugListAdapter.this.notifyDataSetChanged();
                        }
                    }
                });
            }
            RnPluginLog.a("name: " + str2 + "   model: " + str + "   isCheck: " + z);
            TextView textView222 = this.c;
            StringBuilder sb222 = new StringBuilder();
            sb222.append("package: ");
            sb222.append(str2);
            textView222.setText(sb222.toString());
            this.d.setText("model: " + str);
            this.b.setChecked(z);
            this.b.setEnabled(RnDebugListAdapter.this.d);
            this.f16957a.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (RnDebugListAdapter.this.f != null) {
                        RnDebugListAdapter.this.f.a(RnDebugItemViewHolder.this.f16957a, i);
                    }
                }
            });
            this.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (RnDebugListAdapter.this.d && RnDebugListAdapter.this.f != null) {
                        RnDebugListAdapter.this.f.a(compoundButton, i, z);
                    }
                }
            });
            this.e.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (RnDebugListAdapter.this.d) {
                        if (RnDebugListAdapter.this.f != null) {
                            RnDebugListAdapter.this.f.b(view, i);
                        }
                        RnDebugListAdapter.this.e.remove(i);
                        RnDebugListAdapter.this.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    public class RnDebugItemAutoDebugViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f16955a;

        public RnDebugItemAutoDebugViewHolder(View view) {
            super(view);
            this.f16955a = (TextView) view.findViewById(R.id.tv_rn_debug_auto_debug);
            this.f16955a.getPaint().setFlags(8);
        }

        public void a() {
            this.f16955a.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (RnDebugListAdapter.this.d && RnDebugListAdapter.this.f != null) {
                        RnDebugListAdapter.this.f.a(RnDebugItemAutoDebugViewHolder.this.f16955a);
                    }
                }
            });
        }
    }

    private void a(List list, List list2) {
        list2.clear();
        if (list != null && list.size() != 0) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                list2.add(list.get(i));
            }
        }
    }
}
