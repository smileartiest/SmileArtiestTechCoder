package smilearts.project.training2

class RememberUtil {

    private var userName: String = ""
    private var password: String = ""

    public fun getRememberDetails() : ArrayList<String> {
        var setString = arrayListOf<String>()
        if (userName.isNotEmpty()) setString.add(userName)
        else setString.add("Smile")
        if (password.isNotEmpty()) setString.add(password)
        else setString.add("123456")
        return setString
    }

    public fun setUserDetails(userName: String, password: String) {
        this.userName = userName
        this.password = password
    }

}