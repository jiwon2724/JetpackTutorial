package com.example.jetpacktutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jetpacktutorial.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


// ViewModel관련은 lifecycle관련
// ViewModel이 LiveDate를 가지고 있음
// ViewModel이 가지고 있는 데이터를  Activity나 Fragment에 알려줘야함

// LiveData와 ViewMdel은 같이 세트루 묶여다님 데이터가 변경되었을 때 관찰
// ex) 친구라는 뷰모델이 있을 때 그 안에 이름, 나이데이터는 라이브데이터로 매핑
// 라이브데이터로 감싸져 있어서 뷰모델이 가지고 있는 이름이 변경이 되었을 때 액티비티나 프래그먼트는 변경사항을 알 수 있음
// -> 옵저버(라이브 데이터)

// 뷰 바인딩을 설정하면 바인딩 클래스를 자동으로 만들어줌
// 액티비티 기준으로 이름을 반전시켜서 만들어줌 -> 액티비티 메인 바인딩(바인딩 클래스)
class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var myNumberViewModel: MyNumberViewModel

    // 자동으로 완성된 액티비티 메인 바인딩 클래스 인스턴스를 가져옴
    // 뷰 바인딩을 사용하게되면 xml id형식이 camelCase로 바뀌게 된당
    val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    companion object {
        const val TAG = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰 바인딩과 연결
        setContentView(activityMainBinding.root)


        // 뷰모델 프로바이더를 통하여 뷰모델을 가지고 올 수 있음
        // get(클래스이름)으로 가지고온다
        myNumberViewModel = ViewModelProvider(this).get(MyNumberViewModel::class.java)
        myNumberViewModel.currentValue.observe(this, Observer {
            // Observer를 통해서 값이 변경이되면 바로 알 수 있음
            Log.d(TAG, "MainActivity - myNumberViewModel - currentValue 라이브 데이터 값 변경 : $it")
            activityMainBinding.numberTextview.text = it.toString()
        })
        activityMainBinding.plusBtn.setOnClickListener(this)
        activityMainBinding.minusBtn.setOnClickListener(this)
     }

    override fun onClick(view: View?) {
        Log.d(TAG, "클릭이벤트")
        val userinput = activityMainBinding.userInputEdittext.text.toString().toInt()
        // 뷰모델에 라이브데이터 값을 변경하는 메소드 실행
        when(view){
            activityMainBinding.plusBtn -> myNumberViewModel.updateValue(ActionType.PLUS, userinput)
            activityMainBinding.minusBtn -> myNumberViewModel.updateValue(ActionType.MINUS, userinput)
        }
    }
}