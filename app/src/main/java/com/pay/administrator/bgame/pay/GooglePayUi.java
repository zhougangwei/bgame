
package com.pay.administrator.bgame.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.utils.LogUtils;
import com.facebook.CallbackManager;
import com.facebook.appevents.AppEventsLogger;
import com.pay.administrator.bgame.R;
import com.pay.administrator.bgame.base.Contact;
import com.pay.administrator.bgame.bean.BaseBean;
import com.pay.administrator.bgame.bean.OrderBean;
import com.pay.administrator.bgame.http.ErrorCodeException;
import com.pay.administrator.bgame.http.ProxyPostHttpRequest;
import com.pay.administrator.bgame.http.RetrofitFactory;
import com.pay.administrator.bgame.http.RetryWithDelay;
import com.pay.administrator.bgame.utils.GsonUtil;
import com.pay.administrator.bgame.utils.InitDialog;
import com.pay.administrator.bgame.utils.ResultUtils;
import com.pay.administrator.bgame.utils.ToastUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.gaoshou.money.googlepay.util.IabBroadcastReceiver;
import me.gaoshou.money.googlepay.util.IabHelper;
import me.gaoshou.money.googlepay.util.IabResult;
import me.gaoshou.money.googlepay.util.Inventory;
import me.gaoshou.money.googlepay.util.Purchase;




public class GooglePayUi implements IabBroadcastReceiver.IabBroadcastListener ,GooglePayUiPrecneter {

    private static final String TAG = "GooglePayUi";

    public Activity activity;
    private String base64EncodedPublicKey;

    private String productId;
    static final int RC_REQUEST = 10001;
    private IabHelper mHelper;
    private IabBroadcastReceiver mBroadcastReceiver;
    private DialogCircleProgress dialogCircleProgress;
    private  ConstumCallBack constumCallBack;

    public final static String[] productArr=new String[]{ "namol4.9",
                    "namol19.9",
                    "namol199.9"};

    public final static String NAMOL_49="Namol4.9";
    public final static String NAMOL_199="Namol4.9";
    public final static String NAMOL_1999="Namol199.9";


    @Override
    public void init(Activity activity){
        this.activity = activity;
        initData(true);
    }



    @Override
    public void addConstumCallBack(ConstumCallBack constumCallBack) {
        this.constumCallBack=constumCallBack;
    }



    public void initData(final boolean initGoogle){
        if (initGoogle) {
            initGoogle();
        }
    }


    private void initGoogle() {
        base64EncodedPublicKey = activity.getString(R.string.googlepaycode);
        mHelper = new IabHelper(activity, base64EncodedPublicKey);
        mHelper.enableDebugLogging(true);
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            @Override
            public void onIabSetupFinished(IabResult result) {

                if (!result.isSuccess()) {

                    // Oh noes, there was a problem.
                    complain("Problem setting up in-app billing: " + result);
                    return;
                }

                // Have we been disposed of in the meantime? If so, quit.
                if (mHelper == null)
                    return;

                // Important: Dynamically register for broadcast messages about updated purchases.
                // We register the receiver here instead of as a <receiver> in the Manifest
                // because we always call getPurchases() at startup, so therefore we can ignore
                // any broadcasts sent while the app isn't running.
                // Note: registering this listener in an Activity is a bad idea, but is done here
                // because this is a SAMPLE. Regardless, the receiver must be registered after
                // IabHelper is setup, but before first call to getPurchases().
                mBroadcastReceiver = new IabBroadcastReceiver(GooglePayUi.this);
                IntentFilter broadcastFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
                activity.registerReceiver(mBroadcastReceiver, broadcastFilter);

                // IAB is fully set up. Now, let's get an inventory of stuff we own.
                LogUtils.e(TAG, "Setup successful. Querying inventory.");
                try {
                    List<String> skus = new ArrayList<>();
                    skus.addAll(Arrays.asList(productArr));
                    mHelper.queryInventoryAsync(true, skus, null, mGotInventoryListener);

                } catch (IabHelper.IabAsyncInProgressException e) {
                    complain("Error querying inventory. Another async operation in progress.");
                }
            }
        });
    }


    void complain(String message) {

        LogUtils.e(TAG, "**** TrivialDrive Error: " + message);
        // alert("Error: " + message);
    }
    @SuppressLint("CheckResult")
    @Override
    public  void charge(String data) {
        productId = data;
        setWaitScreen(true);
/*TODO: for security, generate your payload here for verification. See the comments on 2代表谷歌市场
         *        verifyDeveloperPayload() for more info.Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this.*/
        RetrofitFactory.getInstance().addProduct(ProxyPostHttpRequest.getInstance().addProduct(productId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OrderBean>() {
                    @Override
                    public void accept(OrderBean bean) throws Exception {
                        if (ResultUtils.cheekSuccess(bean)) {
                            try {
                                mHelper.launchPurchaseFlow(activity, productId, RC_REQUEST,
                                        mPurchaseFinishedListener, bean.getData());
                            } catch (IabHelper.IabAsyncInProgressException e) {
                                ToastUtils.showToast(activity,activity.getString(R.string.google_pay_net_fail));
                                complain("Error launching purchase flow. Another async operation in progress.");
                                setWaitScreen(false);
                            }
                        } else {
                            ToastUtils.showToast(activity,activity.getString(R.string.google_pay_net_fail));
                            complain("错误5" + GsonUtil.parseObjectToJson(bean));
                            setWaitScreen(false);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.showToast(activity,activity.getString(R.string.google_pay_net_fail));
                        setWaitScreen(false);
                    }
                });
    }

    @Override
    public void charge(String productid, String price) {
        if (TextUtils.isEmpty(productid)) {
            setWaitScreen(false);
            return;
        }
        charge(productid);
    }

    // Listener that's called when we finish querying the items and subscriptions we own
    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        @Override
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            LogUtils.e(TAG, "Query inventory finished.");

            // Have we been disposed of in the meantime? If so, quit.
            if (mHelper == null) return;

            // Is it a failure?
            if (result.isFailure()) {
                complain("Failed to query inventory: " + result);
                return;
            }

            LogUtils.e(TAG, "Query inventory was successful.");
           /*  * Check for items we own. Notice that for each purchase, we check
             * the developer payload to see if it's correct! See
             * verifyDeveloperPayload().*/



            // Do we have the premium upgrade?

            // Check for gas delivery -- if we own gas, we should fill up the tank immediately

            final List<Purchase> purchases = new ArrayList<>();

            Purchase gasPurchase = inventory.getPurchase(productId);
            try {
                mHelper.consumeAsync(gasPurchase, mConsumeFinishedListener);
            } catch (IabHelper.IabAsyncInProgressException e) {
                complain("Error consuming gas. Another async operation in progress.");
            }
        }
    };

    // Callback for when a purchase is finished  支付之后
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        @Override
        public void onIabPurchaseFinished(IabResult result, final Purchase purchase) {
            LogUtils.e(TAG, "Purchase finished: " + result + ", purchase: " + purchase);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null){
                LogUtils.e(TAG, "mHelper " +"空了");
                return;
            }

            if (result.isFailure()) {
                complain("Error purchasing: " + result);
                setWaitScreen(false);
                return;
            }
            if (purchase.getSku().equals(productId)) {
                try {
                    mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                } catch (IabHelper.IabAsyncInProgressException e) {
                    complain("Error consuming gas. Another async operation in progress.");
                    setWaitScreen(false);
                    return;
                }
            }
        }
    };

    private void setWaitScreen(boolean isshow) {
        if (isshow){
            if (dialogCircleProgress==null) {
                dialogCircleProgress =new DialogCircleProgress(activity);
            }
            InitDialog.setDialogMatchParent(dialogCircleProgress);
            dialogCircleProgress.show();
        }else{
            if (dialogCircleProgress!=null) {
                dialogCircleProgress.dismiss();
            }
        }
    }
    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        @Override
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            LogUtils.e(TAG, "Consumption finished. Purchase: " + purchase + ", result: " + result);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            // We know this is the "gas" sku because it's the only one we consume,
            // so we don't check which sku was consumed. If you have more than one
            // sku, you probably should check...
            if (result.isSuccess()) {
                // successfully consumed, so we apply the effects of the item in our
                saveData(purchase);
            } else {
                setWaitScreen(false);
                //  complain("Error while consuming: " + result);
            }
            updateUi();

            LogUtils.e(TAG, "End consumption flow.");
        }
    };



    //给钱
    @SuppressLint("CheckResult")
    private void saveData(final Purchase purchase) {

        LogUtils.e(TAG, "purchaseToken"+purchase.getToken());
        LogUtils.e(TAG, "purchase"+GsonUtil.parseObjectToJson(purchase));

        RetrofitFactory.getInstance().googleUpdateProduct(ProxyPostHttpRequest.getInstance().googleUpdateProduct(purchase.getToken(), productId,purchase.getOrderId()))
                .subscribeOn(Schedulers.io()).
                flatMap(new Function<BaseBean, ObservableSource<BaseBean>>() {
                    @Override
                    public ObservableSource<BaseBean> apply(BaseBean baseBean)  {
                        if (!(Contact.REPONSE_CODE_SUCCESS==baseBean.getCode())){
                            return Observable.error(new ErrorCodeException());
                        }
                        return Observable.just(baseBean);
                    }
                }).
                retryWhen(new RetryWithDelay(2,3000)).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        LogUtils.e(TAG, GsonUtil.parseObjectToJson(baseBean));
                        if (ResultUtils.cheekSuccess(baseBean)) {
                            constumSuccess();
                            ToastUtils.showToast(activity, activity.getString(R.string.charge_success));
                            initData(false);
                            setWaitScreen(false);
                        } else {
                            //其实这里就是 message不为空的 134""啥的
                           // complain(GsonUtil.parseObjectToJson(baseBean));
                            setWaitScreen(false);
                            ToastUtils.showToast(activity, activity.getString(R.string.charge_fail));
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        complain("错误4" + throwable.getMessage());
                        setWaitScreen(false);
                        ToastUtils.showToast(activity,activity. getString(R.string.charge_fail));
                    }
                });
    }


    private void constumSuccess(){
        if (constumCallBack!=null) {
            constumCallBack.constumSuccess();
        }
    }


    @Override
    public void receivedBroadcast() {
        try {
            mHelper.queryInventoryAsync(mGotInventoryListener);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error querying inventory. Another async operation in progress.");
        }
    }
    private void updateUi() {

    }

    @Override
    public void onDestroy(){
        if (mBroadcastReceiver != null) {
            activity.unregisterReceiver(mBroadcastReceiver);
        }
        // very important:
        LogUtils.e(TAG, "Destroying helper.");
        if (mHelper != null) {
            mHelper.disposeWhenFinished();
            mHelper = null;
        }

    }


   @Override
   public boolean onActivityResult(int requestCode, int resultCode, Intent data){
        if (mHelper == null) return false;
        // Pass on the activity result to the helper for handling
        if (mHelper.handleActivityResult(requestCode, resultCode, data)) {
            return true;
        } else {
            //needHandSelf
            return false;
        }
    }

}


