package com.digitcreativestudio.dzulfikar68.submissiononekade

import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class SecondActivity : AppCompatActivity(){
    lateinit var nameTextView: TextView
    lateinit var imageImageView: ImageView
    lateinit var descriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout{
            padding = dip(20)
            nameTextView = textView{
                textSize = 25f
                gravity = Gravity.CENTER
                typeface = Typeface.DEFAULT_BOLD
            }
            imageImageView = imageView().lparams {
                height = dip(200)
                width = dip(200)
                topMargin = dip(20)
                bottomMargin = dip(20)
                gravity = Gravity.CENTER
            }
            descriptionTextView = textView{
                textSize = 20f
            }
        }

        val intent = intent
        val data = intent.getParcelableExtra<Item>("item")
        nameTextView.text = data.name
        data.image.let {
            Picasso.get().load(it).into(imageImageView)
        }
        descriptionTextView.text = data.description
    }
}