package com.gersonsilvafilho.library.util.network.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.gersonsilvafilho.library.util.network.NetworkManager;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class NetworkManagerImpl extends BroadcastReceiver implements NetworkManager {

    private Context context;

    private IntentFilter connectivityIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

    private Map<String, Function0<Unit>> listeners = new HashMap<>();

    public NetworkManagerImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void start() {
        context.registerReceiver(this, connectivityIntentFilter);
    }

    @Override
    public void stop() {
        context.unregisterReceiver(this);
    }

    @Override
    public void remove(String tag) {
        listeners.remove(tag);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isNetworkAvailable()) {
            if (!isInitialStickyBroadcast()) {
                for (@NotNull Function0<Unit>  listener : listeners.values()) {
                    if (listener != null) {
                        listener.invoke();
                    }
                }
            }
        }
    }

    @Override
    public void add(@NotNull String tag, @NotNull Function0<Unit> bar) {
        listeners.put(tag, bar);
    }
}
