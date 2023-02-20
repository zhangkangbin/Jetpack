package com.kang.repository

import android.util.Log
import com.kang.model.UserInfo
import kotlinx.coroutines.delay

class MainRepository {

    //模拟远程
    suspend fun getUserInfo(name:String): UserInfo {

        val isSummit = name.takeIf {
            Log.d("mytest", "println it:$it")
            it == "kang"
        }
        //延迟2秒
        delay(2000);
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
}