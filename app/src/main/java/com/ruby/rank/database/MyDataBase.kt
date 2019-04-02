package com.ruby.rank.database

import com.raizlabs.android.dbflow.annotation.Database

@Database(name = MyDataBase.NAME, version = MyDataBase.VERSION)
object MyDataBase {
    //数据库名称
    const val NAME = "rank"
    //数据库版本
    const val VERSION = 1
}
