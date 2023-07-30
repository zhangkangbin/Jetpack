package com.kang.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kang.repository.MainRepository
import kotlinx.coroutines.*
import java.io.File

/**
 *
 */
class MainViewModel : ViewModel() {
    //委托
    val mUserInfoLiveData: MutableLiveData<UserInfo> by lazy {
        MutableLiveData<UserInfo>()
    }
    private val mUserRepository=MainRepository();

    fun submitUserInfo(name: String) {
        //start 为协程的启动模式
        val  scope=viewModelScope.launch (start = CoroutineStart.LAZY){

           val userInfo= mUserRepository.getUserInfo(name)
            mUserInfoLiveData.value=userInfo

            //异步执行下面两个任务
            val one=async { mUserRepository.doTask1() }
            val two=async { mUserRepository.doTask2() }


            Log.d("mytest", "二者结果：${one.await() +two.await()}");

            //不加 async ，下面会先执行完task1,再到task2
            val one2=mUserRepository.doTask1()
            val two2=mUserRepository.doTask2()

            Log.d("mytest", "二者结果：${one2 +two2}");

        }
       //协程工作状态
        Log.d("mytest","我最先被执行！${scope.isActive } ${scope.isCompleted }" );

        //- 阻塞当前线程，直到内部的所有协程执行完毕
        runBlocking {
            //超时函数
            withTimeout(1000L){

            }

        }

        //CoroutineContext 的 plus函数， 进行对 + 号运算符进行了重载。

        viewModelScope.launch (Dispatchers.Default+CoroutineName("随便")){  }


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
    //比 给handle 发一个消息，回到主线程，这样方便多了。没有回调，巴适的不得了。

    private fun globalScopeTask(){
        GlobalScope.launch(Dispatchers.Main) {

            delay(2000)

            withContext(Dispatchers.IO){
                //子线程处理文件
                val file= File("");
            }

            //log("处理完成 withContext 函数中的逻辑才会执行到这里！");
            //show();
        }
    }
}

data class UserInfo(
    var isVip: Boolean? = false,
    var userName: String?,
)