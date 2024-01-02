package kr.ac.duksung.hackathon_y

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kr.ac.duksung.hackathon_y.R  // 여기에 프로젝트의 R 클래스 패키지를 정확히 지정해야 합니다.
import kr.ac.duksung.hackathon_y.databinding.ActivityCheckBinding
import kr.ac.duksung.hackathon_y.databinding.FragmentMeetingBinding


class CheckActivity : AppCompatActivity() {

    private lateinit var container: LinearLayout
    private lateinit var viewBinding:ActivityCheckBinding
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)

        viewBinding = ActivityCheckBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.actionbar.ivSidemenu.visibility = View.INVISIBLE
        viewBinding.actionbar.ivSidemenu2.visibility = View.VISIBLE
        viewBinding.actionbar.ivSidemenu2.setOnClickListener {
            onBackPressed()
        }
        container = findViewById(R.id.container)
        button = findViewById(R.id.btn)

        button.setOnClickListener(View.OnClickListener {
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val subLayer = inflater.inflate(R.layout.sub_layer, container, true)

            // Find the ImageViews with ids imageView4 and imageView5 in the inflated subLayer
            val imageView4 = subLayer.findViewById<ImageView>(R.id.imageView4)
            val imageView5 = subLayer.findViewById<ImageView>(R.id.imageView5)

            // Set a click listener for imageView4
            imageView4.setOnClickListener {
                // Make imageView5 visible and imageView4 invisible
                imageView5.visibility = View.VISIBLE
                imageView4.visibility = View.INVISIBLE
            }

            // Set a click listener for imageView5
            imageView5.setOnClickListener {
                // Make imageView4 visible and imageView5 invisible
                imageView4.visibility = View.VISIBLE
                imageView5.visibility = View.INVISIBLE
            }
        })
    }
}
