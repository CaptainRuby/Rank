package com.ruby.rank.fragment.vm

import com.raizlabs.android.dbflow.sql.language.OrderBy
import com.raizlabs.android.dbflow.sql.language.Select
import com.ruby.rank.activity.vm.BaseViewModel
import com.ruby.rank.database.Subject
import com.ruby.rank.database.Subject_Table

class BoardViewModel : BaseViewModel() {

    override fun initData() {}

    fun getSubjects():List<Subject>{
       return Select().from(Subject::class.java).orderBy(Subject_Table.point,false).queryList()
    }


}