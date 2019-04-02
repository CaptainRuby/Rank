package com.ruby.rank.common

import com.raizlabs.android.dbflow.sql.language.Select
import com.ruby.rank.database.Subject
import com.ruby.rank.model.Rank
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

object MatchPool {

    private const val CACHE_COUNT = 10

    private var capacity = CACHE_COUNT

    private var pool: Stack<Rank> = Stack()

    fun produce() {
        val subjects = Select().from(Subject::class.java).queryList()
        val ranks = ELOUtils.match(subjects, CACHE_COUNT)
        if (ranks.size < CACHE_COUNT) {
            capacity = ranks.size
        } else {
            capacity = CACHE_COUNT
        }
        for (rank in ranks) {
            pool.push(rank)
        }
    }

    fun getRank(): Rank? {
        if (pool.size <= capacity / 2+1) {
            Observable.fromCallable {
                produce()
                return@fromCallable
            }
                    .subscribeOn(Schedulers.io())
                    .subscribe()
        }
        if (pool.empty()) {
            return null
        } else {
            return pool.pop()
        }
    }
}