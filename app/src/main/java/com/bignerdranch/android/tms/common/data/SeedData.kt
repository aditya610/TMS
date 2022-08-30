package com.bignerdranch.android.tms.common.data

object SeedData {

    fun gettablepostion(row:Int, column:Int):Map<Int,String>{
        var position = 0;
        var map = mutableMapOf<Int,String>()
        for ( i in 1..row)
            for (j in 1..column) {
                map.put(position, i.toString() + j.toString())
                position += 1
            }
        if (map != null && map.size != 0)
            return map
        else
            return mutableMapOf<Int,String>(0 to "11")
    }

}