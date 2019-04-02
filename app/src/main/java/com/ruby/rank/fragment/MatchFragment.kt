package com.ruby.rank.fragment

import android.os.Bundle
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ruby.rank.R
import com.ruby.rank.common.animation.DepthPageTransformer
import com.ruby.rank.common.animation.FixedSpeedScroller
import com.ruby.rank.common.view.RankView
import com.ruby.rank.databinding.FragmentMatchBinding
import com.ruby.rank.fragment.adapter.RankAdapter
import com.ruby.rank.fragment.vm.MatchViewModel
import kotlinx.android.synthetic.main.fragment_match.*

class MatchFragment : BaseFragment<FragmentMatchBinding, MatchViewModel>() {

    companion object {
        const val TAG = "MatchFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setView(MatchViewModel::class.java, inflater, container, R.layout.fragment_match).root
    }

    override fun initData() {}

    override fun initView() {
        //切换动画
        viewPager.setPageTransformer(true, DepthPageTransformer())
        //滑动速度
        try {
            val clazz = Class.forName("android.support.v4.view.ViewPager")
            val f = clazz.getDeclaredField("mScroller")
            val fixedSpeedScroller = FixedSpeedScroller(context, LinearOutSlowInInterpolator())
            fixedSpeedScroller.setmDuration(1000)
            f.isAccessible = true
            f.set(viewPager, fixedSpeedScroller)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        viewPager.adapter = RankAdapter(context!!, {
            nextPage()
        })
    }

    fun nextPage(){
        (viewPager.adapter as RankAdapter).refreshView(viewPager.currentItem+1)
        viewPager.currentItem++
    }

    fun refreshCurrentPage(){
        (viewPager.adapter as RankAdapter).refreshView(viewPager.currentItem)
    }

}