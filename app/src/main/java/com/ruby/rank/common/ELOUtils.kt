package com.ruby.rank.common

import com.ruby.rank.database.Subject
import com.ruby.rank.model.Rank
import java.util.ArrayList

object ELOUtils {

    val K = 32

    /**
     * 匹配出n组进行rank的对象
     *
     * @param n
     */
    fun match(subjects: List<Subject>, n: Int): List<Rank> {
        val result = ArrayList<Rank>()
        for (i in subjects.indices) {
            for (j in i + 1 until subjects.size) {
                val Ea = winRate(subjects[i], subjects[j])
                val Da = Math.abs(Ea - 0.5)
                if (result.size < n) {
                    result.add(Rank(subjects[i], subjects[j], Ea))
                }else {
                    result.sortBy { it.Da }
                    for (k in 0 until n) {
                        if (Da < result[k].Da) {
                            System.arraycopy(result, k, result, k + 1, n - k - 1)
                            result[k] = Rank(subjects[i], subjects[j], Ea)
                            break
                        }
                    }
                }
            }
        }
        return result
    }


    /**
     * 计算a与b进行rank的胜率,为避免精度问题，由积分高的一方计算胜率
     *
     * @param a Ranker A
     * @param b Ranker B
     * @return a的胜率
     */
    private fun winRate(a: Subject, b: Subject): Double {
        return if (a.point > b.point) {
            1 / (1 + Math.pow(10.0, (b.point - a.point) / 400.0))
        } else {
            1 - 1 / (1 + Math.pow(10.0, (a.point - b.point) / 400.0))
        }
    }

}