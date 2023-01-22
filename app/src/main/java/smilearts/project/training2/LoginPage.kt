package smilearts.project.training2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class LoginPage : AppCompatActivity() {

    private lateinit var loginBtn: Button
    lateinit var signUpBtn: TextView
    lateinit var userName: TextInputLayout
    lateinit var password: TextInputLayout
    lateinit var rememberCheck: CheckBox
    lateinit var rememberUtil: RememberUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        startInitialise()

        rememberCheck.setOnClickListener {
            if (rememberCheck.isChecked) {
                setRememberUIDetails()
            } else {
                clearUserDetails()
            }
        }

        loginBtn.setOnClickListener {
            val userStr = userName.editText?.text.toString()
            val passStr = password.editText?.text.toString()
            var currentUser = arrayListOf<String>()
            if (userStr.isNotEmpty()) {
                if (passStr.isNotEmpty()) {
                    currentUser.add(userStr)
                    currentUser.add(passStr)
                    validationUser(currentUser)
                } else {
                    password.editText?.error = "Please check password"
                }
            } else {
                userName.editText?.error = "Please check user name"
            }
        }

        signUpBtn.setOnClickListener {
            startActivity(Intent(this, RegisterPage::class.java))
        }

    }

    override fun onStart() {
        super.onStart()

        if (rememberUtil.getLoginState()) {
            moveMainPage()
        }

    }

    private fun setRememberUIDetails() {
        val oldUser = rememberUtil.getRememberDetails()
        userName.editText?.setText(oldUser[0])
        password.editText?.setText(oldUser[1])
    }

    private fun clearUserDetails() {
        userName.editText?.setText("")
        password.editText?.setText("")
    }

    private fun validationUser(_currentUser: ArrayList<String>) {
        val oldUser = rememberUtil.getRememberDetails()
        if (oldUser[0] == _currentUser[0]) {
            if (oldUser[1] == _currentUser[1]) {
                showMessage("Login successful")
                rememberUtil.setLoginState(true)
                moveMainPage()
            } else {
                showMessage("password incorrect")
            }
        } else {
            showMessage("User not valid")
        }
    }

    private fun moveMainPage() {
        startActivity(Intent(this, MainPage::class.java))
        finish()
    }

    private fun showMessage(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * Initialise widgets
     */
    private fun startInitialise() {
        userName = findViewById(R.id.login_screen_user_name)
        password = findViewById(R.id.login_screen_password)
        rememberCheck = findViewById(R.id.login_screen_remember_check)
        loginBtn = findViewById(R.id.login_screen_login_action)
        signUpBtn = findViewById(R.id.login_screen_signup_action)
        rememberUtil = RememberUtil(applicationContext)
    }

}