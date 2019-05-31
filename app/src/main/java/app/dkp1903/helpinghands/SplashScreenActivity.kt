package app.dkp1903.helpinghands

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager

class SplashScreenActivity : AppCompatActivity() {


    private val SLEEP_TIMER = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_splash_screen)
        supportActionBar!!.hide()
        val logoLauncher = LogoLauncher()
        logoLauncher.start()
    }


    private inner class LogoLauncher : Thread() {
        override fun run() {
            try {
                Thread.sleep((1000 * SLEEP_TIMER).toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            this@SplashScreenActivity.finish()

        }
    }


}
