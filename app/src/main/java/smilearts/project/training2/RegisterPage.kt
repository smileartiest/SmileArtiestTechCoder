package smilearts.project.training2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.log

class RegisterPage : AppCompatActivity() {

    private lateinit var registerBtn: Button
    lateinit var loginBtn: TextView
    lateinit var name: TextInputLayout
    lateinit var userName: TextInputLayout
    lateinit var password: TextInputLayout

    lateinit var rememberUtil: RememberUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)
        startInitialise()

        registerBtn.setOnClickListener {
            val nameStr = name.editText?.text.toString()
            val userNameStr = userName.editText?.text.toString()
            val passwordStr = password.editText?.text.toString()
            if (nameStr.isNotEmpty()) {
                if (userNameStr.isNotEmpty()) {
                    if (passwordStr.isNotEmpty()) {
                        rememberUtil.setDetails(nameStr,userNameStr,passwordStr)
                        finish()
                    } else {
                        password.editText?.error = "Please check password"
                    }
                } else {
                    userName.editText?.error = "Please check user name"
                }
            } else {
                name.editText?.error = "Please check name"
            }
        }

        loginBtn.setOnClickListener {
            finish()
        }

    }

    private fun startInitialise() {
        name = findViewById(R.id.register_page_name)
        userName = findViewById(R.id.register_page_user_name)
        password = findViewById(R.id.register_page_password)

        registerBtn = findViewById(R.id.register_page_register_action)
        loginBtn = findViewById(R.id.register_page_login_action)

        rememberUtil = RememberUtil(applicationContext)
    }

}