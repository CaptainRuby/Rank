package com.ruby.rank.database

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = MyDataBase::class)
data class Record(@PrimaryKey(autoincrement = true) var id: Long = 0,
                  @Column var aId: Long? = 0,
                  @Column var aName: String? = null,
                  @Column var aPoint: String? = null,
                  @Column var bId: Long? = 0,
                  @Column var bName: String? = null,
                  @Column var bPoint: String? = null,
                  @Column var victoryPoint: Int = 0) : BaseModel() {
}