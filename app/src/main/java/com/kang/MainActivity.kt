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

class MainActivity : AppCompatActivity() ,CoroutineScope by MainScope() {
    lateinit var mTvName :TextView;
    lateinit var mName :String;
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView();

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

/*

    private fun show(){
        Toast.makeText(this@MainActivity,"show message",Toast.LENGTH_LONG).show();

    }
*/
}