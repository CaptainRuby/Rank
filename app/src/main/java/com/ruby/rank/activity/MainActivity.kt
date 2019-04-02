package com.ruby.rank.activity

import android.Manifest
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.View
import com.ruby.rank.R
import com.ruby.rank.activity.vm.MainViewModel
import com.ruby.rank.common.Config
import com.ruby.rank.common.MatchPool
import com.ruby.rank.databinding.ActivityMainBinding
import com.ruby.rank.fragment.BoardFragment
import com.ruby.rank.fragment.EmptyFragment
import com.ruby.rank.fragment.MatchFragment
import com.ruby.rank.fragment.SearchFragment

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    lateinit var matchFragment: MatchFragment
    lateinit var searchFragment: SearchFragment
    lateinit var boardFragment: BoardFragment
    lateinit var emptyFragment: EmptyFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(MainViewModel::class.java, R.layout.activity_main)
    }

    override fun initData() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        MatchPool.produce()
        matchFragment = MatchFragment()
        searchFragment = SearchFragment()
        boardFragment = BoardFragment()
        emptyFragment = EmptyFragment()
    }

    override fun initView() {
        val transaction = supportFragmentManager.beginTransaction()
                .add(R.id.container, matchFragment, MatchFragment.TAG)
                .add(R.id.container, searchFragment, SearchFragment.TAG)
                .add(R.id.container, boardFragment, BoardFragment.TAG)
                .add(R.id.container, emptyFragment, EmptyFragment.TAG)
                .hide(searchFragment)
                .hide(boardFragment)
        if (Config(this).get(Config.SUBJECT_COUNT,0)>1) {
            transaction.hide(emptyFragment)
        } else {
            transaction.hide(matchFragment)
        }
        transaction.commit()
    }

    val onMatchClick = View.OnClickListener {
        val transaction = supportFragmentManager.beginTransaction()
                .hide(searchFragment)
                .hide(boardFragment)
        if (Config(this).get(Config.SUBJECT_COUNT,0)>1) {
            matchFragment.refreshCurrentPage()
            transaction.hide(emptyFragment).show(matchFragment)
        } else {
            transaction.hide(matchFragment).show(emptyFragment)
        }
        transaction.commit()
    }

    val onSearchClick = View.OnClickListener {
        supportFragmentManager.beginTransaction()
                .hide(emptyFragment)
                .hide(matchFragment)
                .hide(boardFragment)
                .show(searchFragment)
                .commit()
    }

    val onBordClick = View.OnClickListener {
        boardFragment.update()
        supportFragmentManager.beginTransaction()
                .hide(emptyFragment)
                .hide(matchFragment)
                .hide(searchFragment)
                .show(boardFragment)
                .commit()
    }

}

