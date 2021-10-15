package com.test.kumparan.ui.base

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavArgs
import com.test.kumparan.api.ApiErrorEvent
import com.test.kumparan.guice.RoboAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.net.HttpURLConnection
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

abstract class BaseActivity<P : IPresenter> : RoboAppCompatActivity() {

    @Inject
    lateinit var presenter: P



    protected val disposeBag: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    protected open fun getScreenName(): String {
        return ""
    }

    protected val isFromAppIndexing: Boolean
        get() = intent.data != null

    protected val appIndexingUrl: Uri?
        get() = intent.data

    private var argsInstance: Any? = null

    private val noInternetConnectionDialog: AlertDialog by lazy {
        AlertDialog.Builder(this).setTitle("No Connection Internet")
            .setTitle("Please check your internet connection")
            .setPositiveButton("Close") { dialogInterface, i -> dialogInterface.dismiss() }.create()
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @Suppress("UNCHECKED_CAST")
    fun <E : NavArgs> args(clazz: Class<E>): E {
        if (argsInstance == null) {
            val method = clazz.getMethod("fromBundle", Bundle::class.java)
            argsInstance = method.invoke(clazz, intent.extras)
        }

        return argsInstance as E
    }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (this is BaseView) {
            presenter.attachView(this as BaseView)
            this.onPrepare()
        }
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
        disposeBag.dispose()
    }

    public override fun onStart() {
        super.onStart()
        presenter.onViewStarted()
        EventBus.getDefault().register(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
    }

    public override fun onStop() {
        super.onStop()
        presenter.onViewStopped()
        //TODO
        EventBus.getDefault().unregister(this)
    }

    @Suppress("ControlFlowWithEmpty")
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ApiErrorEvent) {
        when (event.code) {
            HttpsURLConnection.HTTP_UNAUTHORIZED -> if (!overrideErrorResponse(event.code)) {
                if (isProcessingUnAuthorizeRedirect) return
                this.isProcessingUnAuthorizeRedirect = true
                Toast.makeText(this, "Your session has expired.", Toast.LENGTH_LONG).show()
                //                this.userSession.clearStorage()
            }
            HttpsURLConnection.HTTP_FORBIDDEN -> if (!overrideErrorResponse(event.code)) {

            }
            HttpsURLConnection.HTTP_INTERNAL_ERROR -> if (!overrideErrorResponse(event.code)) {

            }
            HttpsURLConnection.HTTP_BAD_REQUEST -> if (!overrideErrorResponse(event.code)) {

            }
            HttpURLConnection.HTTP_NOT_FOUND -> if (!overrideErrorResponse(event.code)) {

            }
            NO_NETWORK_AVAILABLE -> if (!noInternetConnectionDialog.isShowing) {
                noInternetConnectionDialog.show()
            }
        }
    }

    private var isProcessingUnAuthorizeRedirect = false

    @Suppress("UNUSED_PARAMETER")
    private fun overrideErrorResponse(errorCode: Int): Boolean {
        return false
    }

    companion object {
        private const val NO_NETWORK_AVAILABLE = 0
    }
}