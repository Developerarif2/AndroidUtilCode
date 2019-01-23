package com.blankj.utilcode.pkg.feature.network

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import com.blankj.lib.base.BaseBackActivity
import com.blankj.utilcode.pkg.R
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.SpanUtils
import com.blankj.utilcode.util.ThreadUtils
import kotlinx.android.synthetic.main.activity_network.*

/**
 * ```
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2016/10/13
 * desc  : demo about NetworkUtils
 * ```
 */
class NetworkActivity : BaseBackActivity() {

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, NetworkActivity::class.java)
            context.startActivity(starter)
        }
    }

    private lateinit var spannableStringBuilder: SpannableStringBuilder

    private var mSimpleTask = object : ThreadUtils.SimpleTask<CharSequence>() {
        override fun doInBackground(): CharSequence? {
            return SpanUtils().appendLine(spannableStringBuilder)
                    .appendLine("isAvailableByPing: " + NetworkUtils.isAvailableByPing())
                    .append("getBaiduDomainAddress: " + NetworkUtils.getDomainAddress("baidu.com"))
                    .create()
        }

        override fun onSuccess(result: CharSequence?) {
            result?.run { networkAboutTv.text = this }
        }
    }

    override fun initData(bundle: Bundle?) {

    }

    override fun bindLayout(): Int {
        return R.layout.activity_network
    }

    override fun initView(savedInstanceState: Bundle?, contentView: View) {
        setTitle(R.string.demo_network)

        networkOpenWirelessSettingsBtn.setOnClickListener(this)
        networkSetWifiEnabledBtn.setOnClickListener(this)
        updateAboutNetwork()
    }

    override fun doBusiness() {
        ThreadUtils.executeBySingle(mSimpleTask)
    }

    override fun onWidgetClick(view: View) {
        when (view.id) {
            R.id.networkOpenWirelessSettingsBtn -> NetworkUtils.openWirelessSettings()
            R.id.btn_set_data_enabled -> NetworkUtils.setMobileDataEnabled(!NetworkUtils.getMobileDataEnabled())
            R.id.networkSetWifiEnabledBtn -> NetworkUtils.setWifiEnabled(!NetworkUtils.getWifiEnabled())
        }
        updateAboutNetwork()
    }

    private fun updateAboutNetwork() {
        spannableStringBuilder = SpanUtils.with(networkAboutTv)
                .appendLine("isConnected: " + NetworkUtils.isConnected())
                .appendLine("getMobileDataEnabled: " + NetworkUtils.getMobileDataEnabled())
                .appendLine("isMobileData: " + NetworkUtils.isMobileData())
                .appendLine("is4G: " + NetworkUtils.is4G())
                .appendLine("getWifiEnabled: " + NetworkUtils.getWifiEnabled())
                .appendLine("isWifiConnected: " + NetworkUtils.isWifiConnected())
                .appendLine("isWifiAvailable: " + NetworkUtils.isWifiAvailable())
                .appendLine("getNetworkOperatorName: " + NetworkUtils.getNetworkOperatorName())
                .appendLine("getNetworkTypeName: " + NetworkUtils.getNetworkType())
                .appendLine("getIPv4Address: " + NetworkUtils.getIPAddress(true))
                .appendLine("getIPv6Address: " + NetworkUtils.getIPAddress(false))
                .appendLine("getBroadcastIpAddress: " + NetworkUtils.getBroadcastIpAddress())
                .appendLine("getIpAddressByWifi: " + NetworkUtils.getIpAddressByWifi())
                .appendLine("getGatewayByWifi: " + NetworkUtils.getGatewayByWifi())
                .appendLine("getNetMaskByWifi: " + NetworkUtils.getNetMaskByWifi())
                .append("getServerAddressByWifi: " + NetworkUtils.getServerAddressByWifi())
                .create()
        ThreadUtils.executeBySingle(mSimpleTask)
    }

    override fun onDestroy() {
        mSimpleTask.cancel()
        super.onDestroy()
    }
}
