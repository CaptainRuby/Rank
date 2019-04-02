package com.ruby.rank.database

import android.os.Parcelable
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Table(database = MyDataBase::class)
data class Subject(@PrimaryKey(autoincrement = true) var id: Long = 0,
                   @Column var name: String? = null,
                   @Column var imagePath: String? = null,
                   @Column var imageUrl: String? = null,
                   @Column var point: Int = 1000) : BaseModel(), Parcelable {
}