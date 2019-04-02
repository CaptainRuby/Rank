package com.ruby.rank.fragment

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ruby.rank.R
import com.ruby.rank.fragment.adapter.SearchAdapter
import com.ruby.rank.databinding.FragmentSearchBinding
import com.ruby.rank.fragment.vm.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    companion object {
        const val TAG = "SearchFragment"
    }

    private lateinit var adapter:SearchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setView(SearchViewModel::class.java, inflater, container, R.layout.fragment_search).root
    }

    override fun initData() {

    }

    override fun initView() {
    }


    val onSearchClick = View.OnClickListener {
        viewModel.search(keywordsView.text.toString()){
            adapter = SearchAdapter(this@SearchFragment.context,it)
            listView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            listView.adapter = adapter
        }

    }
}