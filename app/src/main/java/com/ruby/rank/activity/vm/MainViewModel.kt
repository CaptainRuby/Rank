package com.ruby.rank.activity.vm

import com.ruby.rank.common.MatchPool
import com.ruby.rank.fragment.MatchFragment

class MainViewModel: BaseViewModel(){

    lateinit var matchFragments:MutableList<MatchFragment>

    override fun initData() {
        MatchPool.produce()
        matchFragments = mutableListOf()
        matchFragments.add(MatchFragment())
        matchFragments.add(MatchFragment())
        matchFragments.add(MatchFragment())
    }

}