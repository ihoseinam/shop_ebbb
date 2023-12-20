package com.example.pasi

import android.app.Activity
import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pasi.databinding.ListItemBinding

class ProductRecyclerAdapter(
    private val context: Activity,
    private val product: Array<DataProduct>,
) : RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =ListItemBinding.inflate(context.layoutInflater,parent,false)
        return ProductViewHolder(binding)


    }

    override fun getItemCount(): Int = product.size


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.setDataProduct(product[position])
    }

    inner class ProductViewHolder(private val binding: ListItemBinding, ) : RecyclerView.ViewHolder(binding.root) {
        fun setDataProduct(product: DataProduct) {
            binding.txtName.text = product.name
            binding.txtPrice.text = product.price
            binding.imageView.setImageResource(product.imgAddresss)

            binding.button.setOnClickListener {
                val intent = Intent(context, MainActivity2::class.java)
                intent.putExtra("name", product.name)
                intent.putExtra("price", product.price)
                intent.putExtra("img", product.imgAddresss)
                intent.putExtra("rating",product.rating)
                context.startActivity(intent)
            }
        }


    }
}