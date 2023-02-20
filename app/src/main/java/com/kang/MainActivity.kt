package com.kang

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kang.model.MainViewModel
import kotlinx.coroutines.*
import java.io.File

class MainActivity : AppCompatActivity() ,CoroutineScope by MainScope() {
    lateinit var mTvName :TextView;
    lateinit var mName :String;
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView();

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
            show();
        }
    }

    private fun show(){
        Toast.makeText(this@MainActivity,"show message",Toast.LENGTH_LONG).show();

    }

    private fun  initView(){
        mTvName=findViewById(R.id.main_tv_name);

        findViewById<View>(R.id.main_btn).setOnClickListener {

            mainViewModel.submitUserInfo(mName)
        }

        findViewById<EditText>(R.id.main_edt).addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {

                    mTvName.setText(s.toString())

                    mName=s.toString();
                }

            }

        )

        mainViewModel.mUserInfoLiveData.observe(this
        ) { t ->
            mTvName.text = t?.userName
            Log.d("mytest", "监听到数据变化：${t?.userName}")
        }
    }
}