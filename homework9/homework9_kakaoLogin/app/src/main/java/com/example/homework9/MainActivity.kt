package com.example.homework9

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.homework9.databinding.ActivityMainBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding?=null
    private val binding get()=_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**HashKey**/
        val keyHash=Utility.getKeyHash(this)
        Log.e("key","keyHash: ${keyHash}")

        KakaoSdk.init(this,this.getString(R.string.kakao_app_key))

        binding.btnStartKakaoLogin.setOnClickListener{
            kakaoLogin()
        }

        binding.btnStartKakaoLogout.setOnClickListener{
            kakaoLogout()
        }

    }


    private fun kakaoLogin() {
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                TextMsg(this, "카카오계정으로 로그인 실패 : ${error}")
                setLogin(false)
            } else if (token != null) {
                UserApiClient.instance.me { user, error ->
                    if(user!=null){
                        TextMsg(this, "카카오계정으로 로그인 성공 \n\n " +
                                "이메일: ${user.kakaoAccount?.email} \n\n" +
                                "닉네임: ${user.kakaoAccount?.profile?.nickname}")
                        setLogin(true)
                    }

                }
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    TextMsg(this, "카카오톡으로 로그인 실패 : ${error}")

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    TextMsg(this, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    setLogin(true)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }


    private fun kakaoLogout(){
        UserApiClient.instance.logout { error ->
            if(error!=null){
                TextMsg(this,"로그아웃 실패: ${error}")
            }else{
                TextMsg(this, "로그아웃 성공")
                setLogin(false)
            }
        }
    }


    private fun TextMsg(act: Activity, msg: String){
        binding.tvHashKey.text=msg
    }

    private fun setLogin(bool:Boolean){
        binding.btnStartKakaoLogin.visibility=if(bool) View.GONE else View.VISIBLE
        binding.btnStartKakaoLogout.visibility=if(bool) View.VISIBLE else View.GONE
    }
}