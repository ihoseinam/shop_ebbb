package com.example.pasi

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val data = arrayOf(
        DataProduct(1, "حسین","اتمام موجودی", R.drawable.hosey,5.0),
        DataProduct(2, "معید", "20,000 $", R.drawable.psamo,4.5),
        DataProduct(3, "یونس", "1,000 $", R.drawable.sondi,0.5),
        DataProduct(4, " حجت", "5,000 $", R.drawable.hojat,2.5),
        DataProduct(5, " سجاد", "8,000 $", R.drawable.sardar,4.0),
        DataProduct(6, "محسن", "10,000 $", R.drawable.hazrat,3.0),
        DataProduct(7, "ممد ", "3,000 $", R.drawable.mmd,1.5),
        DataProduct(8, "جاسم ", "10 $", R.drawable.jasem,0.5),
        DataProduct(9,"امیر","7,000 $",R.drawable.amir,3.5)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = ""
        setSupportActionBar(binding.toolbar)
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Dialog()
            }
        })
        val adapter = ProductRecyclerAdapter(this, data)
        binding.RecyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        binding.RecyclerView.adapter = adapter

    }


    private fun Dialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setCancelable(false)
        dialog.setTitle("بستن برنامه ")
        dialog.setMessage("مایل به خروج از برنامه هستید؟")
        dialog.setNeutralButton("خروج") { _, _ -> finish() }
        dialog.setNegativeButton("بازگشت به اپ ") { _, _ -> }
        dialog.create().show()
    }


}