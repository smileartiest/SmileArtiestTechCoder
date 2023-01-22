package smilearts.project.training2

import android.content.Context

class RememberUtil(private val mContext: Context) {

    companion object {
        const val NAME_TAG = "Name"
        const val USER_NAME_TAG = "UserName"
        const val PASSWORD_TAG = "Password"
        const val LOGIN_STATE_TAG = "LoginState"
    }

    var tempData = mContext.getSharedPreferences("SMILEDB", Context.MODE_PRIVATE)
    var editor = tempData.edit()

    fun getLoginState() : Boolean {
        return tempData.getBoolean(LOGIN_STATE_TAG , false)
    }

    fun setLoginState(status: Boolean) {
        editor.putBoolean(LOGIN_STATE_TAG, status).apply()
        editor.commit()
    }

    fun setDetails(name: String, userName: String, password: String) {
        editor.putString(NAME_TAG, name).apply()
        editor.putString(USER_NAME_TAG, userName).apply()
        editor.putString(PASSWORD_TAG, password).apply()
        editor.commit()
    }

    fun getRememberDetails() : ArrayList<String> {
        var userList = arrayListOf<String>()
        userList.add(tempData.getString(USER_NAME_TAG,"null")!!) //0
        userList.add(tempData.getString(PASSWORD_TAG,"null")!!) //1
        userList.add(tempData.getString(NAME_TAG,"null")!!) //2
        return userList
    }

}