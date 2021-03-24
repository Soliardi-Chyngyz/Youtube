package com.chyngyz.youtube.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chyngyz.youtube.ui.list_activity.PlayListViewModel
import com.chyngyz.youtube.utils.showToast
import org.koin.android.ext.android.inject

abstract class BaseActivity<T : BaseViewModel>(@LayoutRes val layoutId: Int, val vmClass: Class<T>) : AppCompatActivity() {
     lateinit var viewModel: T


    abstract fun getViewModule(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        viewModel = getViewModule()
        viewModel = ViewModelProvider(this).get(vmClass)
        observe(viewModel)
        viewModel.getToast().observe(this, Observer { this.showToast(it) })
    }

    private fun observe(viewModel: T){
        viewModel.isLoading().observe(this, {progress(it)})
    }

    abstract fun progress(it: Boolean)

}