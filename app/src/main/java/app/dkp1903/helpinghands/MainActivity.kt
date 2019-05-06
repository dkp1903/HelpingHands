package app.dkp1903.helpinghands

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {

    var admloginbutton: Button? = null
    var internloginbutton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        admloginbutton = findViewById(R.id.AdminLogin)
        admloginbutton?.setOnClickListener({
            var clickIntent2 = Intent(this@MainActivity, AdminLogin::class.java)
            startActivity(clickIntent2)
        })

        internloginbutton = findViewById(R.id.LoginIntern)
        internloginbutton?.setOnClickListener({
            var clickIntent3 = Intent(this@MainActivity, InternLogin::class.java)
            startActivity(clickIntent3)
        })
    }

}

