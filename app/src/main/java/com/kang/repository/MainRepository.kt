package com.kang.repository

import android.util.Log
import com.kang.model.UserInfo
import kotlinx.coroutines.delay

class MainRepository {

    //模拟远程
    suspend fun getUserInfo(name:String): UserInfo {
        delay(1000);
        val isSummit = name.takeIf {
            Log.d("mytest", "println it:$it")
            it == "kang"
        }
        //延迟2秒

        if (isSummit != null) {
            Log.d("mytest", isSummit)
            //设置信息
            /*    mUserInfoLiveData.value?.apply {
                    isVip = true;
                    userName = isSummit
                }*/

          return UserInfo(true, "VVIP:$isSummit")


        } else {
            Log.d("mytest", "isSummit is null")
            return UserInfo(false,"非法会员")



        }


    }


    suspend fun doTask1():Int{

        delay(200)
        //由于1，延迟时间比2 要长，异步执行时，这里在task2后面输出
        Log.d("mytest","doTask1.....")

        return 10;

    }
    suspend fun doTask2():Int{
        delay(100)
        Log.d("mytest","doTask2.....")
        return 8;
    }
}