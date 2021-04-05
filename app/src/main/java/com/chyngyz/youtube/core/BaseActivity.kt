package com.chyngyz.youtube.core

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.chyngyz.youtube.ui.list_activity.PlayListViewModel
import com.chyngyz.youtube.utils.showToast
import org.koin.android.ext.android.inject

//    @LayoutRes val layoutId: Int, val vmClass: Class<T>
abstract class BaseActivity<T : BaseViewModel, VB : ViewBinding> : AppCompatActivity() {
    protected abstract val viewModel : T
    protected lateinit var viewBinding : VB

    protected abstract fun inflateViewBinding(inflater: LayoutInflater): VB

//    abstract fun getViewModule(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = inflateViewBinding(LayoutInflater.from(this))
        setContentView(viewBinding.root)
//        viewModel = getViewModule()
//        viewModel = ViewModelProvider(this).get(vmClass)
        observe(viewModel)
        viewModel.getToast().observe(this, Observer { this.showToast(it) })
    }

    private fun observe(viewModel: T){
        viewModel.isLoading().observe(this, {progress(it)})
    }

    abstract fun progress(it: Boolean)

}