package com.ruby.rank.fragment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ruby.rank.BR
import com.ruby.rank.activity.vm.BaseViewModel

abstract class BaseFragment<B: ViewDataBinding,V: BaseViewModel>: Fragment(){

    lateinit var viewModel:V

    fun setView(clazz: Class<V>,inflater: LayoutInflater, container: ViewGroup?,layoutId:Int):B{
        this.viewModel = ViewModelProviders.of(this).get(clazz)
        val binding = DataBindingUtil.inflate<B>(inflater,layoutId,container,false)
        binding.setVariable(BR.window,this)
        return binding
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        viewModel.initData()
        initView()
    }

    abstract fun initData()

    abstract fun initView()
}