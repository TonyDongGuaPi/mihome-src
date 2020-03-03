package com.mi.global.shop.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.WebActivity;
import com.mi.global.shop.newmodel.home.NewHomeBlockData;
import com.mi.global.shop.newmodel.home.NewHomeBlockInfo;
import com.mi.global.shop.newmodel.home.NewHomeBlockInfoItem;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.ImageUtil;
import com.mi.global.shop.util.UIAdapter;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.AutoScrollViewPager;
import com.mi.global.shop.widget.CustomRecyclerView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.EasyTextView;
import com.mi.global.shop.widget.NoScrollGridView;
import com.mi.global.shop.widget.SpaceItemDecoration;
import com.mi.global.shop.widget.home.HomeThemeItemClick;
import com.mi.global.shop.widget.vpi.LinePageIndicator;
import com.mi.util.Coder;
import com.mi.util.Device;
import com.mi.util.ScreenInfo;
import com.xiaomi.smarthome.camera.activity.timelapse.TCPClient;
import java.util.ArrayList;
import java.util.List;

public class HomeListAdapter extends BaseAdapter {
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = 4;
    private static final int g = 5;
    private static final int h = 6;
    private static final int i = 7;
    private static final int j = 8;
    private static final int k = 9;

    /* renamed from: a  reason: collision with root package name */
    public List<NewHomeBlockInfo> f5543a = new ArrayList();
    private ArrayList<Integer> l = new ArrayList<>();
    private Context m;

    public long getItemId(int i2) {
        return (long) i2;
    }

    public int getViewTypeCount() {
        return 10;
    }

    public class HDHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private HDHolder f5553a;

        @UiThread
        public HDHolder_ViewBinding(HDHolder hDHolder, View view) {
            this.f5553a = hDHolder;
            hDHolder.grid = (NoScrollGridView) Utils.findRequiredViewAsType(view, R.id.home_image_grid_view, "field 'grid'", NoScrollGridView.class);
        }

        @CallSuper
        public void unbind() {
            HDHolder hDHolder = this.f5553a;
            if (hDHolder != null) {
                this.f5553a = null;
                hDHolder.grid = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class StationMoreHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private StationMoreHolder f5566a;

        @UiThread
        public StationMoreHolder_ViewBinding(StationMoreHolder stationMoreHolder, View view) {
            this.f5566a = stationMoreHolder;
            stationMoreHolder.more = Utils.findRequiredView(view, R.id.home_grid_header_more, "field 'more'");
        }

        @CallSuper
        public void unbind() {
            StationMoreHolder stationMoreHolder = this.f5566a;
            if (stationMoreHolder != null) {
                this.f5566a = null;
                stationMoreHolder.more = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class PhoneHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private PhoneHolder f5558a;

        @UiThread
        public PhoneHolder_ViewBinding(PhoneHolder phoneHolder, View view) {
            this.f5558a = phoneHolder;
            phoneHolder.list = (CustomRecyclerView) Utils.findRequiredViewAsType(view, R.id.phone_list, "field 'list'", CustomRecyclerView.class);
            phoneHolder.bg = (ImageView) Utils.findRequiredViewAsType(view, R.id.phone_list_bg, "field 'bg'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            PhoneHolder phoneHolder = this.f5558a;
            if (phoneHolder != null) {
                this.f5558a = null;
                phoneHolder.list = null;
                phoneHolder.bg = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class ProductHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ProductHolder f5561a;

        @UiThread
        public ProductHolder_ViewBinding(ProductHolder productHolder, View view) {
            this.f5561a = productHolder;
            productHolder.content = Utils.findRequiredView(view, R.id.home_product_content, "field 'content'");
            productHolder.product = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.product_image, "field 'product'", SimpleDraweeView.class);
        }

        @CallSuper
        public void unbind() {
            ProductHolder productHolder = this.f5561a;
            if (productHolder != null) {
                this.f5561a = null;
                productHolder.content = null;
                productHolder.product = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class AccessoryHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private AccessoryHolder f5545a;

        @UiThread
        public AccessoryHolder_ViewBinding(AccessoryHolder accessoryHolder, View view) {
            this.f5545a = accessoryHolder;
            accessoryHolder.content = Utils.findRequiredView(view, R.id.accessory_list_content, "field 'content'");
            accessoryHolder.list = (CustomRecyclerView) Utils.findRequiredViewAsType(view, R.id.accessory_list, "field 'list'", CustomRecyclerView.class);
            accessoryHolder.bg = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.accessory_list_bg, "field 'bg'", SimpleDraweeView.class);
        }

        @CallSuper
        public void unbind() {
            AccessoryHolder accessoryHolder = this.f5545a;
            if (accessoryHolder != null) {
                this.f5545a = null;
                accessoryHolder.content = null;
                accessoryHolder.list = null;
                accessoryHolder.bg = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class GalleryHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private GalleryHolder f5548a;

        @UiThread
        public GalleryHolder_ViewBinding(GalleryHolder galleryHolder, View view) {
            this.f5548a = galleryHolder;
            galleryHolder.pager = (AutoScrollViewPager) Utils.findRequiredViewAsType(view, R.id.home_gallery_viewpager, "field 'pager'", AutoScrollViewPager.class);
            galleryHolder.indicator = (LinePageIndicator) Utils.findRequiredViewAsType(view, R.id.home_gallery_indicator, "field 'indicator'", LinePageIndicator.class);
        }

        @CallSuper
        public void unbind() {
            GalleryHolder galleryHolder = this.f5548a;
            if (galleryHolder != null) {
                this.f5548a = null;
                galleryHolder.pager = null;
                galleryHolder.indicator = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class GameHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private GameHolder f5551a;

        @UiThread
        public GameHolder_ViewBinding(GameHolder gameHolder, View view) {
            this.f5551a = gameHolder;
            gameHolder.content = Utils.findRequiredView(view, R.id.game_content, "field 'content'");
            gameHolder.bg = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.game_bg, "field 'bg'", SimpleDraweeView.class);
            gameHolder.text = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.game_text, "field 'text'", CustomTextView.class);
            gameHolder.gameIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.game_icon, "field 'gameIcon'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            GameHolder gameHolder = this.f5551a;
            if (gameHolder != null) {
                this.f5551a = null;
                gameHolder.content = null;
                gameHolder.bg = null;
                gameHolder.text = null;
                gameHolder.gameIcon = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class StationTitleHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private StationTitleHolder f5567a;

        @UiThread
        public StationTitleHolder_ViewBinding(StationTitleHolder stationTitleHolder, View view) {
            this.f5567a = stationTitleHolder;
            stationTitleHolder.textTitle = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.home_grid_header_title, "field 'textTitle'", CustomTextView.class);
            stationTitleHolder.imageTitle = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.home_grid_header_image_title, "field 'imageTitle'", SimpleDraweeView.class);
        }

        @CallSuper
        public void unbind() {
            StationTitleHolder stationTitleHolder = this.f5567a;
            if (stationTitleHolder != null) {
                this.f5567a = null;
                stationTitleHolder.textTitle = null;
                stationTitleHolder.imageTitle = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class HotBuyHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private HotBuyHolder f5556a;

        @UiThread
        public HotBuyHolder_ViewBinding(HotBuyHolder hotBuyHolder, View view) {
            this.f5556a = hotBuyHolder;
            hotBuyHolder.image = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.hot_buy_image, "field 'image'", SimpleDraweeView.class);
            hotBuyHolder.name = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.hot_buy_name, "field 'name'", CustomTextView.class);
            hotBuyHolder.desc = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.hot_buy_desc, "field 'desc'", CustomTextView.class);
            hotBuyHolder.prize = (EasyTextView) Utils.findRequiredViewAsType(view, R.id.hot_buy_prize, "field 'prize'", EasyTextView.class);
            hotBuyHolder.time = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.hot_buy_time, "field 'time'", CustomTextView.class);
            hotBuyHolder.textBuy = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.hot_buy_text, "field 'textBuy'", CustomTextView.class);
            hotBuyHolder.imageBuy = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.hot_buy_image_btn, "field 'imageBuy'", SimpleDraweeView.class);
        }

        @CallSuper
        public void unbind() {
            HotBuyHolder hotBuyHolder = this.f5556a;
            if (hotBuyHolder != null) {
                this.f5556a = null;
                hotBuyHolder.image = null;
                hotBuyHolder.name = null;
                hotBuyHolder.desc = null;
                hotBuyHolder.prize = null;
                hotBuyHolder.time = null;
                hotBuyHolder.textBuy = null;
                hotBuyHolder.imageBuy = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class StationItemHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private StationItemHolder f5564a;

        @UiThread
        public StationItemHolder_ViewBinding(StationItemHolder stationItemHolder, View view) {
            this.f5564a = stationItemHolder;
            stationItemHolder.leftView = Utils.findRequiredView(view, R.id.left_layout, "field 'leftView'");
            stationItemHolder.rightView = Utils.findRequiredView(view, R.id.right_layout, "field 'rightView'");
            stationItemHolder.leftImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.left_item_image, "field 'leftImage'", SimpleDraweeView.class);
            stationItemHolder.leftName = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.left_item_name, "field 'leftName'", CustomTextView.class);
            stationItemHolder.leftPrice = (EasyTextView) Utils.findRequiredViewAsType(view, R.id.left_item_price, "field 'leftPrice'", EasyTextView.class);
            stationItemHolder.leftIconImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.left_icon_image, "field 'leftIconImage'", SimpleDraweeView.class);
            stationItemHolder.leftIconText = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.left_icon_text, "field 'leftIconText'", CustomTextView.class);
            stationItemHolder.rightImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.right_item_image, "field 'rightImage'", SimpleDraweeView.class);
            stationItemHolder.rightName = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.right_item_name, "field 'rightName'", CustomTextView.class);
            stationItemHolder.rightPrice = (EasyTextView) Utils.findRequiredViewAsType(view, R.id.right_item_price, "field 'rightPrice'", EasyTextView.class);
            stationItemHolder.rightIconImage = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.right_icon_image, "field 'rightIconImage'", SimpleDraweeView.class);
            stationItemHolder.rightIconText = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.right_icon_text, "field 'rightIconText'", CustomTextView.class);
        }

        @CallSuper
        public void unbind() {
            StationItemHolder stationItemHolder = this.f5564a;
            if (stationItemHolder != null) {
                this.f5564a = null;
                stationItemHolder.leftView = null;
                stationItemHolder.rightView = null;
                stationItemHolder.leftImage = null;
                stationItemHolder.leftName = null;
                stationItemHolder.leftPrice = null;
                stationItemHolder.leftIconImage = null;
                stationItemHolder.leftIconText = null;
                stationItemHolder.rightImage = null;
                stationItemHolder.rightName = null;
                stationItemHolder.rightPrice = null;
                stationItemHolder.rightIconImage = null;
                stationItemHolder.rightIconText = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public HomeListAdapter(Context context) {
        this.m = context;
    }

    public void a(NewHomeBlockData newHomeBlockData) {
        System.out.println(newHomeBlockData);
        this.l.clear();
        this.f5543a.clear();
        if (newHomeBlockData.mHeaderGallery != null && newHomeBlockData.mHeaderGallery.size() > 0) {
            NewHomeBlockInfo newHomeBlockInfo = newHomeBlockData.mHeaderGallery.get(0);
            if (newHomeBlockInfo.mItems != null && newHomeBlockInfo.mItems.size() > 0) {
                this.f5543a.add(newHomeBlockInfo);
                this.l.add(0);
            }
        }
        if (newHomeBlockData.mHDSections != null && newHomeBlockData.mHDSections.size() > 0) {
            NewHomeBlockInfo newHomeBlockInfo2 = newHomeBlockData.mHDSections.get(0);
            if (newHomeBlockInfo2.mItems != null && newHomeBlockInfo2.mItems.size() > 0) {
                this.f5543a.add(newHomeBlockInfo2);
                this.l.add(9);
            }
        }
        if (newHomeBlockData.mHotBuySections != null && newHomeBlockData.mHotBuySections.size() > 0) {
            NewHomeBlockInfo newHomeBlockInfo3 = newHomeBlockData.mHotBuySections.get(0);
            if (newHomeBlockInfo3.mItems != null && newHomeBlockInfo3.mItems.size() > 0) {
                this.f5543a.add(newHomeBlockInfo3);
                this.l.add(3);
            }
        }
        if (newHomeBlockData.mGameSections != null && newHomeBlockData.mGameSections.size() > 0) {
            NewHomeBlockInfo newHomeBlockInfo4 = newHomeBlockData.mGameSections.get(0);
            if (newHomeBlockInfo4.mItems != null && newHomeBlockInfo4.mItems.size() > 0) {
                this.f5543a.add(newHomeBlockInfo4);
                this.l.add(4);
            }
        }
        if (newHomeBlockData.mPhoneSections != null && newHomeBlockData.mPhoneSections.size() > 0) {
            NewHomeBlockInfo newHomeBlockInfo5 = newHomeBlockData.mPhoneSections.get(0);
            if (newHomeBlockInfo5.mItems != null && newHomeBlockInfo5.mItems.size() > 0) {
                this.f5543a.add(newHomeBlockInfo5);
                this.l.add(1);
            }
        }
        a(newHomeBlockData.getHomeSectionBySort(1));
        if (newHomeBlockData.mAccessorySections != null && newHomeBlockData.mAccessorySections.size() > 0) {
            NewHomeBlockInfo newHomeBlockInfo6 = newHomeBlockData.mAccessorySections.get(0);
            if (newHomeBlockInfo6.mItems != null && newHomeBlockInfo6.mItems.size() > 0) {
                this.f5543a.add(newHomeBlockInfo6);
                this.l.add(2);
            }
        }
        a(newHomeBlockData.getHomeSectionBySort(2));
        if (newHomeBlockData.mProductSections != null && newHomeBlockData.mProductSections.size() > 0) {
            NewHomeBlockInfo newHomeBlockInfo7 = newHomeBlockData.mProductSections.get(0);
            if (newHomeBlockInfo7.mItems != null && newHomeBlockInfo7.mItems.size() > 0) {
                this.f5543a.add(newHomeBlockInfo7);
                this.l.add(8);
            }
        }
        notifyDataSetChanged();
    }

    private void a(NewHomeBlockInfo newHomeBlockInfo) {
        if (newHomeBlockInfo != null && newHomeBlockInfo.mItems != null && newHomeBlockInfo.mItems.size() > 0) {
            NewHomeBlockInfo newHomeBlockInfo2 = new NewHomeBlockInfo();
            newHomeBlockInfo2.mDesc = newHomeBlockInfo.mDesc;
            this.f5543a.add(newHomeBlockInfo2);
            this.l.add(5);
            int i2 = 0;
            while (i2 < newHomeBlockInfo.mItems.size()) {
                NewHomeBlockInfo newHomeBlockInfo3 = new NewHomeBlockInfo();
                newHomeBlockInfo3.mItems.add(newHomeBlockInfo.mItems.get(i2));
                int i3 = i2 + 1;
                if (i3 < newHomeBlockInfo.mItems.size()) {
                    newHomeBlockInfo3.mItems.add(newHomeBlockInfo.mItems.get(i3));
                    i2 = i3;
                }
                this.f5543a.add(newHomeBlockInfo3);
                this.l.add(6);
                i2++;
            }
            if (newHomeBlockInfo.mDesc != null && newHomeBlockInfo.mDesc.mSort == 2) {
                this.f5543a.add(newHomeBlockInfo2);
                this.l.add(7);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00b1, code lost:
        r5 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00c4, code lost:
        if (r5 == null) goto L_0x00d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00c6, code lost:
        r4.setTag(r5);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View getView(int r3, android.view.View r4, android.view.ViewGroup r5) {
        /*
            r2 = this;
            if (r4 != 0) goto L_0x00ca
            int r0 = r2.getItemViewType(r3)
            r1 = 0
            switch(r0) {
                case 0: goto L_0x00b3;
                case 1: goto L_0x00a0;
                case 2: goto L_0x008e;
                case 3: goto L_0x007c;
                case 4: goto L_0x006a;
                case 5: goto L_0x0058;
                case 6: goto L_0x0046;
                case 7: goto L_0x0033;
                case 8: goto L_0x0020;
                case 9: goto L_0x000d;
                default: goto L_0x000a;
            }
        L_0x000a:
            r5 = 0
            goto L_0x00c4
        L_0x000d:
            android.content.Context r4 = r2.m
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            int r0 = com.mi.global.shop.R.layout.shop_home_hd
            android.view.View r4 = r4.inflate(r0, r5, r1)
            com.mi.global.shop.adapter.home.HomeListAdapter$HDHolder r5 = new com.mi.global.shop.adapter.home.HomeListAdapter$HDHolder
            r5.<init>(r4)
            goto L_0x00c4
        L_0x0020:
            android.content.Context r4 = r2.m
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            int r0 = com.mi.global.shop.R.layout.shop_home_product
            android.view.View r4 = r4.inflate(r0, r5, r1)
            com.mi.global.shop.adapter.home.HomeListAdapter$ProductHolder r5 = new com.mi.global.shop.adapter.home.HomeListAdapter$ProductHolder
            r5.<init>(r4)
            goto L_0x00c4
        L_0x0033:
            android.content.Context r4 = r2.m
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            int r0 = com.mi.global.shop.R.layout.shop_home_station_more
            android.view.View r4 = r4.inflate(r0, r5, r1)
            com.mi.global.shop.adapter.home.HomeListAdapter$StationMoreHolder r5 = new com.mi.global.shop.adapter.home.HomeListAdapter$StationMoreHolder
            r5.<init>(r4)
            goto L_0x00c4
        L_0x0046:
            android.content.Context r4 = r2.m
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            int r0 = com.mi.global.shop.R.layout.shop_home_station_item
            android.view.View r4 = r4.inflate(r0, r5, r1)
            com.mi.global.shop.adapter.home.HomeListAdapter$StationItemHolder r5 = new com.mi.global.shop.adapter.home.HomeListAdapter$StationItemHolder
            r5.<init>(r4)
            goto L_0x00c4
        L_0x0058:
            android.content.Context r4 = r2.m
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            int r0 = com.mi.global.shop.R.layout.shop_home_station_title
            android.view.View r4 = r4.inflate(r0, r5, r1)
            com.mi.global.shop.adapter.home.HomeListAdapter$StationTitleHolder r5 = new com.mi.global.shop.adapter.home.HomeListAdapter$StationTitleHolder
            r5.<init>(r4)
            goto L_0x00c4
        L_0x006a:
            android.content.Context r4 = r2.m
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            int r0 = com.mi.global.shop.R.layout.shop_home_game
            android.view.View r4 = r4.inflate(r0, r5, r1)
            com.mi.global.shop.adapter.home.HomeListAdapter$GameHolder r5 = new com.mi.global.shop.adapter.home.HomeListAdapter$GameHolder
            r5.<init>(r4)
            goto L_0x00c4
        L_0x007c:
            android.content.Context r4 = r2.m
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            int r0 = com.mi.global.shop.R.layout.shop_home_hot_buy
            android.view.View r4 = r4.inflate(r0, r5, r1)
            com.mi.global.shop.adapter.home.HomeListAdapter$HotBuyHolder r5 = new com.mi.global.shop.adapter.home.HomeListAdapter$HotBuyHolder
            r5.<init>(r4)
            goto L_0x00c4
        L_0x008e:
            android.content.Context r4 = r2.m
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            int r0 = com.mi.global.shop.R.layout.shop_accessory_list
            android.view.View r4 = r4.inflate(r0, r5, r1)
            com.mi.global.shop.adapter.home.HomeListAdapter$AccessoryHolder r0 = new com.mi.global.shop.adapter.home.HomeListAdapter$AccessoryHolder
            r0.<init>(r4, r5)
            goto L_0x00b1
        L_0x00a0:
            android.content.Context r4 = r2.m
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            int r0 = com.mi.global.shop.R.layout.shop_phone_list
            android.view.View r4 = r4.inflate(r0, r5, r1)
            com.mi.global.shop.adapter.home.HomeListAdapter$PhoneHolder r0 = new com.mi.global.shop.adapter.home.HomeListAdapter$PhoneHolder
            r0.<init>(r4, r5)
        L_0x00b1:
            r5 = r0
            goto L_0x00c4
        L_0x00b3:
            android.content.Context r4 = r2.m
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            int r0 = com.mi.global.shop.R.layout.shop_home_gallery
            android.view.View r4 = r4.inflate(r0, r5, r1)
            com.mi.global.shop.adapter.home.HomeListAdapter$GalleryHolder r5 = new com.mi.global.shop.adapter.home.HomeListAdapter$GalleryHolder
            r5.<init>(r4)
        L_0x00c4:
            if (r5 == 0) goto L_0x00d0
            r4.setTag(r5)
            goto L_0x00d0
        L_0x00ca:
            java.lang.Object r5 = r4.getTag()
            com.mi.global.shop.adapter.home.HomeListAdapter$ViewHolder r5 = (com.mi.global.shop.adapter.home.HomeListAdapter.ViewHolder) r5
        L_0x00d0:
            if (r5 == 0) goto L_0x00dd
            java.util.List<com.mi.global.shop.newmodel.home.NewHomeBlockInfo> r0 = r2.f5543a
            java.lang.Object r3 = r0.get(r3)
            com.mi.global.shop.newmodel.home.NewHomeBlockInfo r3 = (com.mi.global.shop.newmodel.home.NewHomeBlockInfo) r3
            r5.a(r3)
        L_0x00dd:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.adapter.home.HomeListAdapter.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    public Object getItem(int i2) {
        return this.f5543a.get(i2);
    }

    public int getCount() {
        return this.f5543a.size();
    }

    public int getItemViewType(int i2) {
        return this.l.get(i2).intValue();
    }

    static class ViewHolder {
        public void a(NewHomeBlockInfo newHomeBlockInfo) {
        }

        ViewHolder() {
        }
    }

    static class GalleryHolder extends ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        HomeGalleryPagerAdapter f5546a;
        int b = 0;
        @BindView(2131493412)
        LinePageIndicator indicator;
        @BindView(2131493413)
        AutoScrollViewPager pager;

        public GalleryHolder(View view) {
            ButterKnife.bind((Object) this, view);
            view.getLayoutParams().height = UIAdapter.a().a(20);
            this.pager.setStopScrollWhenTouch(true);
            this.pager.setStopScrollWhenTouched(true);
            this.f5546a = new HomeGalleryPagerAdapter(view.getContext());
            this.pager.setAdapter(this.f5546a);
            a();
            this.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                public void onPageScrollStateChanged(int i) {
                }

                public void onPageScrolled(int i, float f, int i2) {
                }

                public void onPageSelected(int i) {
                    HomeThemeItemClick.a((NewHomeBlockInfoItem) GalleryHolder.this.f5546a.a(i));
                }
            });
            this.pager.setCurrentItem(0);
            this.pager.startAutoScroll(5000);
        }

        public void a(NewHomeBlockInfo newHomeBlockInfo) {
            if (newHomeBlockInfo != null && newHomeBlockInfo.mItems != null && !newHomeBlockInfo.mItems.isEmpty()) {
                this.f5546a.a(newHomeBlockInfo.mItems);
                this.indicator.setRealCount(this.f5546a.a());
            }
        }

        private void a() {
            int a2 = this.f5546a.a();
            this.indicator.setVisibility(0);
            this.indicator.setViewPager(this.pager);
            this.indicator.setRealCount(a2);
            this.indicator.setCentered(true);
            this.indicator.setStrokeWidth(0.0f);
        }
    }

    static class PhoneHolder extends ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        HomePhoneListAdapter f5557a;
        @BindView(2131493865)
        ImageView bg;
        @BindView(2131493864)
        CustomRecyclerView list;

        public PhoneHolder(View view, ViewGroup viewGroup) {
            ButterKnife.bind((Object) this, view);
            int a2 = Device.f1349a - Coder.a(view.getContext(), 70.0f);
            this.list.addItemDecoration(new SpaceItemDecoration((a2 / Math.round((float) (a2 / Coder.a(view.getContext(), 90.0f)))) - Coder.a(view.getContext(), 70.0f), Coder.a(view.getContext(), 35.0f), Coder.a(view.getContext(), 35.0f)));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
            linearLayoutManager.setOrientation(0);
            this.list.setLayoutManager(linearLayoutManager);
            this.list.setItemAnimator((RecyclerView.ItemAnimator) null);
            this.f5557a = new HomePhoneListAdapter(view.getContext());
            this.list.setAdapter(this.f5557a);
            this.list.setParent(viewGroup);
        }

        public void a(NewHomeBlockInfo newHomeBlockInfo) {
            if (newHomeBlockInfo != null && newHomeBlockInfo.mItems != null && !newHomeBlockInfo.mItems.isEmpty()) {
                this.f5557a.a((List<NewHomeBlockInfoItem>) newHomeBlockInfo.mItems);
            }
        }
    }

    static class AccessoryHolder extends ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        HomeAccessoryListAdapter f5544a;
        @BindView(2131492895)
        SimpleDraweeView bg;
        @BindView(2131492896)
        View content;
        @BindView(2131492894)
        CustomRecyclerView list;

        public AccessoryHolder(View view, ViewGroup viewGroup) {
            ButterKnife.bind((Object) this, view);
            this.content.getLayoutParams().height = UIAdapter.a().a(24);
            this.list.addItemDecoration(new SpaceItemDecoration(Coder.a(view.getContext(), 7.0f), Coder.a(view.getContext(), 25.0f), Coder.a(view.getContext(), 25.0f)));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
            linearLayoutManager.setOrientation(0);
            this.list.setLayoutManager(linearLayoutManager);
            this.list.setItemAnimator((RecyclerView.ItemAnimator) null);
            this.f5544a = new HomeAccessoryListAdapter(view.getContext());
            this.list.setAdapter(this.f5544a);
            this.list.setParent(viewGroup);
        }

        public void a(NewHomeBlockInfo newHomeBlockInfo) {
            if (newHomeBlockInfo != null && newHomeBlockInfo.mItems != null && !newHomeBlockInfo.mItems.isEmpty()) {
                this.f5544a.a((List<NewHomeBlockInfoItem>) newHomeBlockInfo.mItems);
                FrescoUtils.a(newHomeBlockInfo.mDesc.background, this.bg);
            }
        }
    }

    static class HotBuyHolder extends ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f5554a;
        @BindView(2131493427)
        CustomTextView desc;
        @BindView(2131493428)
        SimpleDraweeView image;
        @BindView(2131493429)
        SimpleDraweeView imageBuy;
        @BindView(2131493430)
        CustomTextView name;
        @BindView(2131493431)
        EasyTextView prize;
        @BindView(2131493432)
        CustomTextView textBuy;
        @BindView(2131493433)
        CustomTextView time;

        public HotBuyHolder(View view) {
            this.f5554a = view;
            ButterKnife.bind((Object) this, view);
        }

        public void a(NewHomeBlockInfo newHomeBlockInfo) {
            if (newHomeBlockInfo != null && newHomeBlockInfo.mItems != null && !newHomeBlockInfo.mItems.isEmpty()) {
                final NewHomeBlockInfoItem newHomeBlockInfoItem = newHomeBlockInfo.mItems.get(0);
                this.name.setText(newHomeBlockInfoItem.mProductName);
                this.desc.setText(newHomeBlockInfoItem.mProductMore);
                this.prize.setPrize(newHomeBlockInfoItem);
                this.time.setText(newHomeBlockInfo.mDesc.mTitle);
                FrescoUtils.a(newHomeBlockInfoItem.getImageUrl(), this.image);
                this.f5554a.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        HomeThemeItemClick.a(HotBuyHolder.this.f5554a.getContext(), newHomeBlockInfoItem);
                    }
                });
                if (!TextUtils.isEmpty(newHomeBlockInfo.mDesc.mButtonImage)) {
                    this.textBuy.setVisibility(8);
                    this.imageBuy.setVisibility(0);
                    FrescoUtils.a(newHomeBlockInfo.mDesc.mButtonImage, this.imageBuy);
                } else {
                    this.textBuy.setVisibility(0);
                    if (!TextUtils.isEmpty(newHomeBlockInfo.mDesc.mButtonText)) {
                        this.textBuy.setText(newHomeBlockInfo.mDesc.mButtonText);
                    }
                    this.imageBuy.setVisibility(8);
                }
                HomeThemeItemClick.a(newHomeBlockInfoItem);
            }
        }
    }

    static class GameHolder extends ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f5549a;
        @BindView(2131493373)
        SimpleDraweeView bg;
        @BindView(2131493374)
        View content;
        @BindView(2131493375)
        ImageView gameIcon;
        @BindView(2131493376)
        CustomTextView text;

        public GameHolder(View view) {
            this.f5549a = view;
            ButterKnife.bind((Object) this, view);
            this.content.getLayoutParams().height = UIAdapter.a().a(23);
        }

        public void a(NewHomeBlockInfo newHomeBlockInfo) {
            if (newHomeBlockInfo != null && newHomeBlockInfo.mItems != null && !newHomeBlockInfo.mItems.isEmpty()) {
                final NewHomeBlockInfoItem newHomeBlockInfoItem = newHomeBlockInfo.mItems.get(0);
                if (newHomeBlockInfo.mDesc != null) {
                    if (newHomeBlockInfo.mDesc.show_arrow) {
                        this.gameIcon.setVisibility(0);
                    } else {
                        this.gameIcon.setVisibility(4);
                    }
                }
                this.text.setText(newHomeBlockInfoItem.mProductName);
                this.f5549a.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        HomeThemeItemClick.a(GameHolder.this.f5549a.getContext(), newHomeBlockInfoItem);
                    }
                });
                FrescoUtils.a(newHomeBlockInfoItem.getImageUrl(), this.bg);
                HomeThemeItemClick.a(newHomeBlockInfoItem);
            }
        }
    }

    static class StationMoreHolder extends ViewHolder {
        @BindView(2131493416)
        View more;

        public StationMoreHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }

        public void a(NewHomeBlockInfo newHomeBlockInfo) {
            if (newHomeBlockInfo == null || newHomeBlockInfo.mDesc == null || newHomeBlockInfo.mDesc.mSort != 2) {
                this.more.setVisibility(8);
                return;
            }
            this.more.setVisibility(0);
            this.more.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(StationMoreHolder.this.more.getContext(), WebActivity.class);
                    intent.putExtra("url", ConnectionHelper.az());
                    StationMoreHolder.this.more.getContext().startActivity(intent);
                }
            });
        }
    }

    static class StationItemHolder extends ViewHolder {
        @BindView(2131493617)
        SimpleDraweeView leftIconImage;
        @BindView(2131493618)
        CustomTextView leftIconText;
        @BindView(2131493620)
        SimpleDraweeView leftImage;
        @BindView(2131493621)
        CustomTextView leftName;
        @BindView(2131493622)
        EasyTextView leftPrice;
        @BindView(2131493623)
        View leftView;
        @BindView(2131493949)
        SimpleDraweeView rightIconImage;
        @BindView(2131493950)
        CustomTextView rightIconText;
        @BindView(2131493952)
        SimpleDraweeView rightImage;
        @BindView(2131493953)
        CustomTextView rightName;
        @BindView(2131493954)
        EasyTextView rightPrice;
        @BindView(2131493955)
        View rightView;

        public StationItemHolder(View view) {
            ButterKnife.bind((Object) this, view);
            this.leftImage.getLayoutParams().width = UIAdapter.a().a(13);
            this.leftImage.getLayoutParams().height = UIAdapter.a().a(14);
            this.rightImage.getLayoutParams().width = UIAdapter.a().a(13);
            this.rightImage.getLayoutParams().height = UIAdapter.a().a(14);
        }

        public void a(NewHomeBlockInfo newHomeBlockInfo) {
            if (newHomeBlockInfo == null || newHomeBlockInfo.mItems == null) {
                this.leftView.setVisibility(8);
                this.rightView.setVisibility(8);
                return;
            }
            if (newHomeBlockInfo.mItems.size() <= 0 || newHomeBlockInfo.mItems.get(0) == null) {
                this.leftView.setVisibility(8);
            } else {
                final NewHomeBlockInfoItem newHomeBlockInfoItem = newHomeBlockInfo.mItems.get(0);
                this.leftView.setVisibility(0);
                int b = ScreenInfo.a().b() / 2;
                String imageUrl = newHomeBlockInfoItem.getImageUrl();
                if (!TextUtils.isEmpty(imageUrl)) {
                    imageUrl = ImageUtil.a(b, (int) TCPClient.SOCKET_CONNECTTIMEOUT, newHomeBlockInfoItem.getImageUrl());
                }
                FrescoUtils.a(imageUrl, this.leftImage);
                this.leftName.setText(newHomeBlockInfoItem.mProductName);
                this.leftPrice.setPrize(newHomeBlockInfoItem);
                if (TextUtils.isEmpty(newHomeBlockInfoItem.mIconImg)) {
                    this.leftIconImage.setVisibility(8);
                } else {
                    this.leftIconImage.setVisibility(0);
                    FrescoUtils.a(newHomeBlockInfoItem.mIconImg, this.leftIconImage);
                }
                if (TextUtils.isEmpty(newHomeBlockInfoItem.mIconContent)) {
                    this.leftIconText.setVisibility(8);
                } else {
                    this.leftIconText.setVisibility(0);
                    this.leftIconText.setText(newHomeBlockInfoItem.mIconContent);
                }
                this.leftView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        HomeThemeItemClick.a(StationItemHolder.this.leftView.getContext(), newHomeBlockInfoItem);
                    }
                });
                HomeThemeItemClick.a(newHomeBlockInfoItem);
            }
            if (newHomeBlockInfo.mItems.size() <= 1 || newHomeBlockInfo.mItems.get(1) == null) {
                this.rightView.setVisibility(8);
                return;
            }
            final NewHomeBlockInfoItem newHomeBlockInfoItem2 = newHomeBlockInfo.mItems.get(1);
            this.rightView.setVisibility(0);
            int b2 = ScreenInfo.a().b() / 2;
            String imageUrl2 = newHomeBlockInfoItem2.getImageUrl();
            if (!TextUtils.isEmpty(imageUrl2)) {
                imageUrl2 = ImageUtil.a(b2, (int) TCPClient.SOCKET_CONNECTTIMEOUT, newHomeBlockInfoItem2.getImageUrl());
            }
            FrescoUtils.a(imageUrl2, this.rightImage);
            this.rightName.setText(newHomeBlockInfoItem2.mProductName);
            this.rightPrice.setPrize(newHomeBlockInfoItem2);
            if (TextUtils.isEmpty(newHomeBlockInfoItem2.mIconImg)) {
                this.rightIconImage.setVisibility(8);
            } else {
                this.rightIconImage.setVisibility(0);
                FrescoUtils.a(newHomeBlockInfoItem2.mIconImg, this.rightIconImage);
            }
            if (TextUtils.isEmpty(newHomeBlockInfoItem2.mIconContent)) {
                this.rightIconText.setVisibility(8);
            } else {
                this.rightIconText.setVisibility(0);
                this.rightIconText.setText(newHomeBlockInfoItem2.mIconContent);
            }
            this.rightView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    HomeThemeItemClick.a(StationItemHolder.this.rightView.getContext(), newHomeBlockInfoItem2);
                }
            });
            HomeThemeItemClick.a(newHomeBlockInfoItem2);
        }
    }

    static class StationTitleHolder extends ViewHolder {
        @BindView(2131493415)
        SimpleDraweeView imageTitle;
        @BindView(2131493417)
        CustomTextView textTitle;

        public StationTitleHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }

        public void a(NewHomeBlockInfo newHomeBlockInfo) {
            if (newHomeBlockInfo != null && newHomeBlockInfo.mDesc != null) {
                if (!TextUtils.isEmpty(newHomeBlockInfo.mDesc.mButtonImage)) {
                    this.textTitle.setVisibility(8);
                    this.imageTitle.setVisibility(0);
                    FrescoUtils.a(newHomeBlockInfo.mDesc.mButtonImage, this.imageTitle);
                    return;
                }
                this.textTitle.setVisibility(0);
                this.textTitle.setText(newHomeBlockInfo.mDesc.mTitle);
                this.imageTitle.setVisibility(8);
            }
        }
    }

    static class HDHolder extends ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        HomeImageGridAdapter f5552a;
        @BindView(2131493418)
        NoScrollGridView grid;

        public HDHolder(View view) {
            ButterKnife.bind((Object) this, view);
            this.f5552a = new HomeImageGridAdapter(view.getContext());
            this.grid.setAdapter(this.f5552a);
        }

        public void a(NewHomeBlockInfo newHomeBlockInfo) {
            if (newHomeBlockInfo != null && newHomeBlockInfo.mItems != null && !newHomeBlockInfo.mItems.isEmpty()) {
                this.grid.setNumColumns(newHomeBlockInfo.mItems.size());
                this.f5552a.a(newHomeBlockInfo.mItems);
            }
        }
    }

    static class ProductHolder extends ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f5559a;
        @BindView(2131493419)
        View content;
        @BindView(2131493887)
        SimpleDraweeView product;

        public ProductHolder(View view) {
            this.f5559a = view;
            ButterKnife.bind((Object) this, view);
            this.content.getLayoutParams().height = UIAdapter.a().a(25);
        }

        public void a(NewHomeBlockInfo newHomeBlockInfo) {
            if (newHomeBlockInfo != null && newHomeBlockInfo.mItems != null && !newHomeBlockInfo.mItems.isEmpty()) {
                final NewHomeBlockInfoItem newHomeBlockInfoItem = newHomeBlockInfo.mItems.get(0);
                this.f5559a.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        HomeThemeItemClick.a(ProductHolder.this.f5559a.getContext(), newHomeBlockInfoItem);
                    }
                });
                HomeThemeItemClick.a(newHomeBlockInfoItem);
                String imageUrl = newHomeBlockInfoItem.getImageUrl();
                if (!TextUtils.isEmpty(imageUrl) && !imageUrl.equals(this.product.getTag())) {
                    this.product.setTag(imageUrl);
                    FrescoUtils.a(imageUrl, this.product);
                }
            }
        }
    }
}
