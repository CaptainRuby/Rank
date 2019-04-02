package com.ruby.rank.model

import android.os.Parcel
import android.os.Parcelable
import com.ruby.rank.common.ELOUtils
import com.ruby.rank.database.Subject

class Rank : Parcelable {

    var A: Subject
    var B: Subject
    var Ea: Double = 0.0//A的胜率
    var Eb: Double = 0.0//B的胜率
    var Sa: Double = 0.0//A的胜负
    var k: Int = 0
    var Da:Double = 0.0//A的胜率与0.5的偏差值
    var finish: Boolean = false

    constructor(a: Subject, b: Subject, ea: Double) {
        A = a
        B = b
        Ea = ea
        Eb = 1 - Ea
        Da = Math.abs(ea-0.5)
    }

    /**
     * 进行rank并结算
     *
     * @param sa   A的实际胜负值，胜=1，平=0.5，负=0
     */
    fun account(sa: Double) {
        this.Sa = sa
        k = ELOUtils.K
        if (A.point > B.point) {
            A.point += (k * (Sa - Ea)).toInt()
            B.point -= (k * (Sa - Ea)).toInt()
        } else {
            B.point += (k * (1 - Sa - Eb)).toInt()
            A.point -= (k * (1 - Sa - Eb)).toInt()
        }
        A.update()
        B.update()
        finish = true
    }

    constructor(parcel: Parcel) {
        A = parcel.readParcelable(Subject::class.java.classLoader)
        B = parcel.readParcelable(Subject::class.java.classLoader)
        Ea = parcel.readDouble()
        Eb = parcel.readDouble()
        Sa = parcel.readDouble()
        k = parcel.readInt()
        finish = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeParcelable(A, flags)
        dest?.writeParcelable(B, flags)
        dest?.writeDouble(Ea)
        dest?.writeDouble(Eb)
        dest?.writeDouble(Sa)
        dest?.writeInt(k)
        dest?.writeByte((if (finish) 1 else 0))
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rank> {
        override fun createFromParcel(parcel: Parcel): Rank {
            return Rank(parcel)
        }

        override fun newArray(size: Int): Array<Rank?> {
            return arrayOfNulls(size)
        }
    }

}