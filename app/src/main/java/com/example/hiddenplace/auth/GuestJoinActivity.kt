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
import com.example.hiddenplace.R
import com.example.hiddenplace.RetrofitClient
import com.example.hiddenplace.guest.GuestMainActivity
import com.example.hiddenplace.guide.OCRCheckActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GuestJoinActivity : AppCompatActivity() {
    private var isGuide: Boolean = false // 기본값은 false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_guest_join)

        isGuide = intent.getBooleanExtra("isGuide", false)


        val etUserId2 = findViewById<EditText>(R.id.etUserId2)
        val etPassword2 = findViewById<EditText>(R.id.etPassword2)
        val etUserName2 = findViewById<EditText>(R.id.etUserName2)
        val etPhoneNumber2 = findViewById<EditText>(R.id.etPhoneNumber2)
        val etBirthday2 = findViewById<EditText>(R.id.etBirthday2)
        val etEmail2 = findViewById<EditText>(R.id.etEmail2)
        val rgGender2 = findViewById<RadioGroup>(R.id.rgGender2)
        val btnJoin2 = findViewById<Button>(R.id.guestJoinAccept)

        btnJoin2.setOnClickListener {
            val userId = etUserId2.text.toString()
            val password = etPassword2.text.toString()
            val userName = etUserName2.text.toString()
            val phoneNumber = etPhoneNumber2.text.toString()
            val email = etEmail2.text.toString()
            val birthdayStr2 = etBirthday2.text.toString()

            val birthday6Numbers = if (birthdayStr2.length == 6) {
                birthdayStr2.toIntOrNull() ?: 0 // 숫자로 변환, 오류 시 0
            } else {
                0 // 길이가 6자리가 아니면 기본값 0
            }

            // 성별 선택: 여성(false), 남성(true)
            val gender = when (rgGender2.checkedRadioButtonId) {
                R.id.rbMale2 -> true  // 남성 선택 시 true
                R.id.rbFemale2 -> false // 여성 선택 시 false
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
            sendJoinRequest2(joinModel)
        }

    }

    private fun sendJoinRequest2(joinModel: JoinModel) {
        val apiService = RetrofitClient.joinService

        apiService.JoinUser(joinModel).enqueue(object : Callback<JoinResponseModel> {
            override fun onResponse(call: Call<JoinResponseModel>, response: Response<JoinResponseModel>) {
                if (response.isSuccessful) {
                    val responseData = response.body()

                    responseData?.let {
                        Log.d("GuestJoinActivity", "회원가입 성공: userId=${it.userId}")
                        Toast.makeText(this@GuestJoinActivity, "성공", Toast.LENGTH_SHORT).show()

                        // **회원가입 성공 후 페이지 이동**
                        val intent = Intent(this@GuestJoinActivity, GuestMainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }

                } else {
                    Toast.makeText(this@GuestJoinActivity, "실패", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<JoinResponseModel>, t: Throwable) {
                Toast.makeText(this@GuestJoinActivity, "서버 오류: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}