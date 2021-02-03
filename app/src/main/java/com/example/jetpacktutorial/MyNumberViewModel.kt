package com.example.jetpacktutorial

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType {
    PLUS, MINUS
}

// 뷰모델 라이프싸이클에 있음
// 데이터의 변경을 가지고 있음
// 뷰모델은 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있음
// 데이터 변경의 관련된걸 뷰모델에서 설정
// 라이브 데이터는 이벤트를 전달

// 라이브 데이터의 종류
// 뮤터블 라이브 데이터 - 수정가능
// 라이브 데이터 - 수정 불가능(읽기전용)

class MyNumberViewModel : ViewModel() {
    companion object {
        private const val TAG = "로그"
    }
    
    // 클래스 안에서 값이 변경되는거는 _사용(내부에서 설정하는 자료형은 Mutable로)
    // 변경 가능하도록 설정
    private val _currentValue = MutableLiveData<Int>()

    // _currentValue 데이터를 보내줌 (getter)
    val currentValue : LiveData<Int>
        get() = _currentValue

    // 뷰모델이 생성 될 때, 초기값 설정
    init{
        Log.d(TAG, "MyNumberViewModel - 생성자 호출")
        // 라이브데이터에 매핑된 값으로 접근하려면 value
        // 일반적인 라이브데이터는 값 변경X Mutable이기때문에 가능
        _currentValue.value = 0
    }

    // 뷰모델이 가지고 있는 값(_currentValue)를 변경해주는 메소드
    fun updateValue(actionType: ActionType, input : Int){
        when(actionType){
            ActionType.PLUS -> { _currentValue.value = _currentValue.value?.plus(input) }
            ActionType.MINUS -> { _currentValue.value = _currentValue.value?.minus(input) }
        }
    }
}