package com.ruby.rank.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import com.ruby.rank.BR
import com.ruby.rank.activity.vm.BaseViewModel

abstract class BaseActivity<B:ViewDataBinding,V: BaseViewModel>:AppCompatActivity(){

    lateinit var viewModel:V

    fun setView(clazz: Class<V>,layoutId:Int){
        this.viewModel = ViewModelProviders.of(this).get(clazz)
        val binding = DataBindingUtil.setContentView<B>(this,layoutId)
        binding.setVariable(BR.window,this)
        initData()
        viewModel.initData()
        initView()
    }

    abstract fun initData()

    abstract fun initView()
}