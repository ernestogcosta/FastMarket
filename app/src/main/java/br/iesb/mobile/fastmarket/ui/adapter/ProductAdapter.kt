package br.iesb.mobile.fastmarket.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.iesb.mobile.fastmarket.R
import br.iesb.mobile.fastmarket.databinding.ItemProductBinding
import br.iesb.mobile.fastmarket.repository.Product

class ProductAdapter(private val lista: List<Product>) : RecyclerView.Adapter<ProductViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = lista[position]
        holder.binding.product = product
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = lista.size
}

class ProductViewHolder(v: View): RecyclerView.ViewHolder(v) {
    val binding: ItemProductBinding = ItemProductBinding.bind(v)
}