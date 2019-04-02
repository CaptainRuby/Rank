package com.ruby.rank.activity.vm

import android.arch.lifecycle.ViewModel

abstract class BaseViewModel:ViewModel(){
    abstract fun initData()
}