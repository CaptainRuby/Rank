package com.ruby.rank.common

object Tools {

    fun factorial(n:Int):Int{
        var result = n
        for (i in n-1 downTo 2){
            result *= i
        }
        return result
    }

    fun combination(m:Int,n:Int):Int{
        return factorial(n) /(factorial(m) * factorial(n - m))
    }
}