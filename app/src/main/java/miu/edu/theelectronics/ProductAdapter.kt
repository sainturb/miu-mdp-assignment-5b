package miu.edu.theelectronics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ProductAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var onClickListener: OnClickListener? = null

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val description: TextView = itemView.findViewById(R.id.description)
        val image: ImageView = itemView.findViewById(R.id.image)
        val price: TextView = itemView.findViewById(R.id.price)
        val logo: ImageView = itemView.findViewById(R.id.logo)
        val add: Button = itemView.findViewById(R.id.add)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        holder.name.text = product.name
        holder.description.text = product.description
        holder.image.setImageResource(product.image)
        holder.logo.setImageResource(product.logo)
        holder.price.text = "${product.price}$"

        holder.add.setOnClickListener {
            if(onClickListener != null) {
                onClickListener!!.onClick(position, product, "ADD")
            }
        }

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, product, "VIEW")
            }
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, product: Product, action: String)
    }

    override fun getItemCount(): Int {
        return products.size
    }
}