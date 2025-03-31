package com.example.hiddenplace.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hiddenplace.R
import com.example.hiddenplace.RetrofitClient
import com.example.hiddenplace.guest.GuestMainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GuestJoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_guest_join)

        val etUserId2 = findViewById<EditText>(R.id.etUserId2)
        val etPassword2 = findViewById<EditText>(R.id.etPassword2)
        val etUserName2 = findViewById<EditText>(R.id.etUserName2)
        val etPhoneNumber2 = findViewById<EditText>(R.id.etPhoneNumber2)
        val etBirthday2 = findViewById<EditText>(R.id.etBirthday2)
        val etEmail2 = findViewById<EditText>(R.id.etEmail2)
        val rgGender2 = findViewById<RadioGroup>(R.id.rgGender2)
        val btnJoin2 = findViewById<Button>(R.id.guestJoinAccept)

        btnJoin2.setOnClickListener {
            val userAccountName = etUserId2.text.toString()
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

            val guestJoinModel = GuestJoinModel(
                userAccountName = userAccountName,
                password = password,
                userName = userName,
                phoneNumber = phoneNumber,
                birthday6Numbers = birthday6Numbers, // 생년월일 6자리 추가
                gender = gender,
                email = email,
                regionId = 0, // 기본값
                //guide = false, // 바꾸기
            )
            sendJoinRequest2(guestJoinModel)
        }

    }

    private fun sendJoinRequest2(guestJoinModel: GuestJoinModel) {
        val apiService = RetrofitClient.guestJoinService
        Log.d("GuestJoinActivity", "회원가입 요청 시작: $guestJoinModel") // 요청 데이터 로그 출력

        apiService.GuestJoinUser(guestJoinModel).enqueue(object : Callback<GuestJoinResponseModel> {
            override fun onResponse(call: Call<GuestJoinResponseModel>, response: Response<GuestJoinResponseModel>) {
                Log.d("GuestJoinActivity", "응답 코드: ${response.code()}")
                Log.d("GuestJoinActivity", "응답 성공 여부: ${response.isSuccessful}")
                if (response.isSuccessful) {
                    val responseData = response.body()

                    responseData?.let {
                        if (it.userId != null) {
                            Log.d("GuestJoinActivity", "회원가입 성공: userId=${it.userId}")
                            Toast.makeText(this@GuestJoinActivity, "성공", Toast.LENGTH_SHORT).show()

                            // **회원가입 성공 후 페이지 이동**
                            val intent = Intent(this@GuestJoinActivity, GuestMainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        } else {
                            // userId가 null인 경우 예외 처리
                            Log.e("GuestJoinActivity", "서버 응답에 userId 없음")
                            Toast.makeText(this@GuestJoinActivity, "회원가입 실패: userId 없음", Toast.LENGTH_SHORT).show()
                        }
                    } ?: run {
                        // responseData가 null일 경우 예외 처리
                        Log.e("GuestJoinActivity", "서버 응답이 null입니다.")
                        Toast.makeText(this@GuestJoinActivity, "회원가입 실패: 응답 데이터 없음", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    val errorBody = response.errorBody()?.string() // 오류 응답 본문 읽기
                    Log.e("GuestJoinActivity", "회원가입 실패! 응답 코드: ${response.code()}, 오류 내용: $errorBody")
                    Toast.makeText(this@GuestJoinActivity, "실패: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GuestJoinResponseModel>, t: Throwable) {
                Log.e("GuestJoinActivity", "네트워크 오류: ${t.message}")
                t.printStackTrace() // 오류의 자세한 스택 트레이스를 로그로 찍어봄
                Toast.makeText(this@GuestJoinActivity, "서버 오류: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

