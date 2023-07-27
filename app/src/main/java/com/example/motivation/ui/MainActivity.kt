package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL
    val mock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleUsername()
        handleFilter(R.id.image_all)
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        generatePhrase()
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_new_phrase){
            generatePhrase()
        }else if(view.id in listOf<Int>(R.id.image_all,R.id.image_happy,R.id.image_sunny)){
            handleFilter(view.id)
        }
    }

    fun generatePhrase(){

        binding.textPhrase.text = mock.getPhrase(categoryId,Locale.getDefault().language)
    }


    private fun handleFilter(id:Int){
        binding.imageAll.setColorFilter(ContextCompat.getColor(this,R.color.dark_purple))//1
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this,R.color.dark_purple))//3
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this,R.color.dark_purple))//3
        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this,R.color.white))
                categoryId = MotivationConstants.FILTER.ALL //magic number
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this,R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this,R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
        generatePhrase()
    }
   private fun  handleUsername(){
       binding.buttonNewPhrase.setOnClickListener(this)
       binding.textOla.text = "Ola, ${SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)}"
    }
}