package com.ruby.rank.fragment.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.ruby.rank.common.view.RankView

class RankAdapter(val context: Context, onResultClick: () -> Unit) : PagerAdapter() {

    val views: MutableList<RankView> = mutableListOf()

    init {
        views.add(RankView(context, onResultClick))
        views.add(RankView(context, onResultClick))
        views.add(RankView(context, onResultClick))
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }


    override fun getCount(): Int {
        return Int.MAX_VALUE / 2
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = views[innerPosition(position)]
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(views[innerPosition(position)])
    }

    private fun innerPosition(position: Int): Int {
        return position % views.size
    }

    fun refreshView(position: Int) {
        val view = views[innerPosition(position)]
        if (view.rank == null || view.rank?.finish == true) {
            view.initView()
        }
    }

}
