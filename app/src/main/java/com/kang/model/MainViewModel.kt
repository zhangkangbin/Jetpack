package com.kang.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kang.repository.MainRepository
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    val mUserInfoLiveData: MutableLiveData<UserInfo> by lazy {
        MutableLiveData<UserInfo>()
    }
    private val mUserRepository=MainRepository();

    fun submitUserInfo(name: String) {

        viewModelScope.launch {


           val userInfo= mUserRepository.getUserInfo(name)
            mUserInfoLiveData.value=userInfo



        }
    }

    private fun mainScopeTask() {


        //MainScope 在activity中使用，可以在onDestroy中取消。
        val mainScope = MainScope().launch {
            doSomeThing();
            //log("处理完成 withContext 函数中的逻辑才会执行到这里！");
            // show();
        }


        //任务被取消，防止任务泄露。
        // mainScope.cancel();
    }

    private suspend fun doSomeThing() {

        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
        }
        Log.d("mytest", "任务处理完成！")

    }

}

data class UserInfo(
    var isVip: Boolean? = false,
    var userName: String?,
)