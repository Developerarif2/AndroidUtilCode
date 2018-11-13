package com.blankj.androidutilcode.feature.core.adaptScreen

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.blankj.androidutilcode.R
import com.blankj.utilcode.util.AdaptScreenUtils
import com.blankj.utilcode.util.LogUtils

class CloseAdaptActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, CloseAdaptActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adapt_close)
    }

    override fun getResources(): Resources {
        val resources = super.getResources()
        LogUtils.e(resources.displayMetrics)
        return AdaptScreenUtils.adaptWidth(resources, 1080, false)
    }
}
