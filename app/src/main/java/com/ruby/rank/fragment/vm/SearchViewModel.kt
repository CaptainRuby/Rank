package com.ruby.rank.fragment.vm

import android.util.Log
import com.ruby.rank.activity.vm.BaseViewModel
import com.ruby.rank.common.net.RetrofitHelper
import com.ruby.rank.common.net.ServiceAPI
import com.ruby.rank.model.SearchResult
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchViewModel : BaseViewModel() {

    override fun initData() {

    }

    fun search(keywords: String, onSuccess: (data: List<SearchResult.Anime>) -> Unit) {
        val service = RetrofitHelper.instance.create(ServiceAPI.Search::class.java)
        service.get(keywords, 2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<SearchResult> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: SearchResult) {
                        Log.e("ruby", "搜索数量：" + t.results)
                        onSuccess(t.list.filterNot {
                            it.images == null
                        })
                    }

                    override fun onError(e: Throwable) {
                    }

                })
    }

}