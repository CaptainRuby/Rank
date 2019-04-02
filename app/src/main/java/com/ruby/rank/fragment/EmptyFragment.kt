package com.ruby.rank.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ruby.rank.R
import com.ruby.rank.databinding.FragmentEmptyBinding
import com.ruby.rank.fragment.vm.EmptyViewModel

class EmptyFragment : BaseFragment<FragmentEmptyBinding, EmptyViewModel>() {

    companion object {
        const val TAG = "EmptyFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setView(EmptyViewModel::class.java, inflater, container, R.layout.fragment_empty).root
    }

    override fun initData() {}

    override fun initView() {

    }

    val onAddClick = View.OnClickListener {
        fragmentManager?.beginTransaction()
                ?.hide(fragmentManager?.findFragmentByTag(MatchFragment.TAG))
                ?.hide(fragmentManager?.findFragmentByTag(BoardFragment.TAG))
                ?.hide(fragmentManager?.findFragmentByTag(EmptyFragment.TAG))
                ?.show(fragmentManager?.findFragmentByTag(SearchFragment.TAG))
                ?.commit()
    }

}