package com.example.hiddenplace.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hiddenplace.R
import com.example.hiddenplace.RetrofitClient
import com.example.hiddenplace.guide.OCRCheckActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GuideJoinActivity : AppCompatActivity() {

    private var isGuide: Boolean = false // 기본값은 false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide_join)

        isGuide = intent.getBooleanExtra("isGuide", false)

        val etUserId = findViewById<EditText>(R.id.etUserId)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etUserName = findViewById<EditText>(R.id.etUserName)
        val etPhoneNumber = findViewById<EditText>(R.id.etPhoneNumber)
        val etBirthday = findViewById<EditText>(R.id.etBirthday)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)
        val btnJoin1 = findViewById<Button>(R.id.btnJoin1)

        btnJoin1.setOnClickListener {
            val userId = etUserId.text.toString()
            val password = etPassword.text.toString()
            val userName = etUserName.text.toString()
            val phoneNumber = etPhoneNumber.text.toString()
            val email = etEmail.text.toString()
            val birthdayStr = etBirthday.text.toString()

            val birthday6Numbers = if (birthdayStr.length == 6) {
                birthdayStr.toIntOrNull() ?: 0 // 숫자로 변환, 오류 시 0
            } else {
                0 // 길이가 6자리가 아니면 기본값 0
            }

            // 성별 선택: 여성(false), 남성(true)
            val gender = when (rgGender.checkedRadioButtonId) {
                R.id.rbMale -> true  // 남성 선택 시 true
                R.id.rbFemale -> false // 여성 선택 시 false
                else -> false // 기본값은 false
            }

            val joinModel = JoinModel(
                userAccountName = userId,
                password = password,
                userName = userName,
                phoneNumber = phoneNumber,
                birthday6Numbers = birthday6Numbers, // 생년월일 6자리 추가
                gender = gender,
                email = email,
                regionId = 0, // 기본값
                isGuide = isGuide // 가이드 회원가입이므로 true
            )
            sendJoinRequest(joinModel)
        }
    }

    private fun sendJoinRequest(joinModel: JoinModel) {
        val apiService = RetrofitClient.joinService

        apiService.JoinUser(joinModel).enqueue(object : Callback<JoinResponseModel> {
            override fun onResponse(call: Call<JoinResponseModel>, response: Response<JoinResponseModel>) {
                if (response.isSuccessful) {
                    val responseData = response.body()

                    responseData?.let {
                        Log.d("GuideJoinActivity", "회원가입 성공: userId=${it.userId}")
                        Toast.makeText(this@GuideJoinActivity, "성공", Toast.LENGTH_SHORT).show()

                        // **회원가입 성공 후 페이지 이동**
                        val intent = Intent(this@GuideJoinActivity, OCRCheckActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }

                } else {
                    Toast.makeText(this@GuideJoinActivity, "실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<JoinResponseModel>, t: Throwable) {
                Toast.makeText(this@GuideJoinActivity, "서버 오류: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}