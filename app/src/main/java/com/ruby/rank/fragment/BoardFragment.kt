package com.ruby.rank.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ruby.rank.R
import com.ruby.rank.databinding.FragmentBoardBinding
import com.ruby.rank.fragment.adapter.BoardAdapter
import com.ruby.rank.fragment.vm.BoardViewModel
import kotlinx.android.synthetic.main.fragment_board.*
import android.support.v7.widget.DividerItemDecoration

class BoardFragment : BaseFragment<FragmentBoardBinding, BoardViewModel>() {

    companion object {
        const val TAG = "BoardFragment"
    }

    private lateinit var adapter:BoardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setView(BoardViewModel::class.java, inflater, container, R.layout.fragment_board).root
    }

    override fun initData() {
    }

    override fun initView() {
        adapter = BoardAdapter(this@BoardFragment.context)
        adapter.data = viewModel.getSubjects()
        listView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        listView.adapter = adapter
    }

    fun update(){
        adapter.data = viewModel.getSubjects()
    }

}