package app.dkp1903.helpinghands

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar


class MainActivity : AppCompatActivity() {

    //var admloginbutton: Button? = null
    var internloginbutton: Button? = null
    var aboutusbutton: Button?= null
    var tasklistbutton: Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this@MainActivity, "Firebase Connection Success", Toast.LENGTH_LONG).show();



        internloginbutton = findViewById(R.id.LoginIntern)
        internloginbutton?.setOnClickListener({
            var clickIntent3 = Intent(this@MainActivity, InternLogin::class.java)
            startActivity(clickIntent3)
        })
        aboutusbutton = findViewById(R.id.AboutUs)
        aboutusbutton?.setOnClickListener({
            var clickIntent4 = Intent(this@MainActivity, AboutUs::class.java)
            startActivity(clickIntent4)
        })
        tasklistbutton = findViewById(R.id.Openings)
        tasklistbutton?.setOnClickListener({
            var clickIntent5 = Intent(this@MainActivity, Careers::class.java)
            startActivity(clickIntent5)
        })
    }

}


