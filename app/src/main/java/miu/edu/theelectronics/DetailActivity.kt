package miu.edu.theelectronics

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val home = findViewById<Button>(R.id.home)
        home.setOnClickListener {
            this.finish()
        }
        attemptToFill()
    }

    private fun attemptToFill() {
        val name = findViewById<TextView>(R.id.dname)
        val description = findViewById<TextView>(R.id.ddescription)
        val price = findViewById<TextView>(R.id.dprice)
        val image = findViewById<ImageView>(R.id.dimage)
        if (intent.getStringExtra("name") != null) {
            name.text = intent.getStringExtra("name").toString()
        }
        if (intent.getStringExtra("description") != null) {
            description.text = intent.getStringExtra("description").toString()
        }
        if (intent.getStringExtra("price") != null) {
            price.text = intent.getStringExtra("price").toString()
        }
        image.setImageResource(intent.getIntExtra("image", R.drawable.unknown))
//        if (intent.getStringExtra("image") != null) {
//            image.setImageResource(intent.getStringExtra("image")!!.toInt())
//        }
    }
}