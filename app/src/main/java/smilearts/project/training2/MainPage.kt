package smilearts.project.training2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainPage : AppCompatActivity() {

    lateinit var rememberUtil: RememberUtil
    lateinit var toolbar: Toolbar
    lateinit var bottomNav: BottomNavigationView

    private val navigationCLick: BottomNavigationView.OnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.main_bottom_nav_menu_home -> {
                    loadScreen(FragHomePage())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.main_bottom_nav_menu_profile -> {
                    loadScreen(FragProfilePage())
                    return@OnNavigationItemSelectedListener true
                }
            }
            return@OnNavigationItemSelectedListener false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        startInitialise()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Welcome"
        supportActionBar?.subtitle = rememberUtil.getRememberDetails()[2]
        bottomNav.setOnNavigationItemSelectedListener(navigationCLick)
        loadScreen(FragHomePage())
    }

    private fun loadScreen(fragment: Fragment) {
        val screenFrag = supportFragmentManager.beginTransaction()
        screenFrag.replace(R.id.main_page_frame , fragment)
        screenFrag.addToBackStack(null)
        screenFrag.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_screen_toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.main_screen_toolbar_menu_logout) {
            rememberUtil.setLoginState(false)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startInitialise() {
        toolbar = findViewById(R.id.main_page_toolbar)
        bottomNav = findViewById(R.id.main_page_bottom_nav_menu)
        rememberUtil = RememberUtil(applicationContext)
    }

}