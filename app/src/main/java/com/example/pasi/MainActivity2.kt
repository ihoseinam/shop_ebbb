package com.example.pasi

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.example.pasi.Api.ApiRepository
import com.example.pasi.Api.MainModel
import com.example.pasi.Api.Request
import com.example.pasi.databinding.ActivityMain2Binding
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_SLIDE
import com.google.android.material.snackbar.Snackbar

class MainActivity2 : AppCompatActivity(), Request {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        title = ""

        if (!isInternetAvailable(this.applicationContext)) {
            snack(text = "اتصال اینترنت وجود ندارد")
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val img = intent.getIntExtra("img", 0)
        val rating = intent.getDoubleExtra("rating", 0.0)
        val info = intent.getStringExtra("info")

        setSupportActionBar(binding.toolbar)

        binding.ratingbar.rating = rating.toFloat()
        binding.textView4.text = name
        binding.imgItemprodoct.setImageResource(img)
        binding.txtNameItemp.text = name
        binding.txtPriceItemprr.text = price

        binding.textView.text = info


        val zori = binding.edt.text
        val boji = binding.edtSenderName.text
        binding.btnSendMessage.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            ApiRepository.instance.sendText(
                "0GiVMeIQWwaEXHuAEEV053a4IJ1bUBj7XjkFxNAR",
                "پیام دریافتی برای:\n نام محصول : ${name}\n قیمت فعلی:${price}\nامتیاز فعلی:${rating}\nنام فرستنده: ${boji}\nپیام فرستنده:${zori}",
                this
            )

        }
        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.btnBuyItem.setOnClickListener {
            openTelegram("tg://resolve?domain=i_hoseinam")
        }
    }

    private fun snack(text: String) {
        val snake = Snackbar.make(binding.root, "no internet ", Snackbar.LENGTH_SHORT)
        snake.animationMode = ANIMATION_MODE_SLIDE
        snake.setText(text)
        snake.setBackgroundTint(Color.DKGRAY)
        snake.setTextColor(Color.WHITE)
        ViewCompat.setLayoutDirection(snake.view, ViewCompat.LAYOUT_DIRECTION_RTL)
        snake.show()
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

    private fun openTelegram(url: String) {
        val address = url
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(address))
        try {
            startActivity(intent)
        } catch (_: ActivityNotFoundException) {
            toast("Not Find Telegram")
        }

    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }


    override fun onSuccess(data: MainModel) {
        binding.edt.text?.clear()
        binding.edtSenderName.text?.clear()
        binding.progressBar.visibility = View.INVISIBLE
        snack(data.message)
    }

    override fun onNotSuccess(message: String) {
        snack(message)
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun onError(error: String) {
        snack(error)
        binding.progressBar.visibility = View.INVISIBLE
    }


}
