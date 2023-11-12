package miu.edu.theelectronics

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var toastMessage: Toast? = null
    private val products = listOf<Product>(
        Product(
            "iPad", "iPad Pro 11-inch", R.drawable.ipad, 400.0,
            R.drawable.apple
        ),
        Product(
            "MacBook M3 Pro", "12-core CPU\n18-core GPU", R.drawable.mbp14_spacegray, 2500.00,
            R.drawable.apple
        ),
        Product(
            "Dell Inspiron", "13th Gen Intel® CoreTM i7", R.drawable.dell_inspiron, 1499.00,
            R.drawable.dell
        ),
        Product(
            "Logitech Keyboard",
            "Logitech - PRO X\nTKL LIGHTSPEED Wireless",
            R.drawable.logintech_keyboard,
            199.00,
            R.drawable.logitech
        ),
        Product(
            "MacBook M3 Max", "14-core CPU\n30-core GPU", R.drawable.mbp16_spaceblack, 3499.00,
            R.drawable.apple
        )
    )

    val cartItems = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cart = findViewById<Button>(R.id.cart)

        cart.setOnClickListener {
            showMessage(cartItems.joinToString(", ") { it.name })
        }

        val productAdapter = ProductAdapter(products)
        productAdapter.setOnClickListener(object :
            ProductAdapter.OnClickListener {
            override fun onClick(position: Int, product: Product, action: String) {
                if (action == "ADD") {
                    cartItems.add(product)
                    cart.text = "Go to cart (${cartItems.size})"
                    showMessage("Item is added successfully! ${cartItems.size}")
                } else {
                    showDetail(product)
                }
            }
        })
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = productAdapter
    }

    private fun showMessage(message: String) {
        toastMessage?.cancel()
        toastMessage = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toastMessage?.show()
    }

    private fun showDetail(product: Product) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("name", product.name)
        intent.putExtra("description", product.description)
        intent.putExtra("price", "${product.price}$")
        intent.putExtra("image", product.image)
        startActivity(intent)
    }
}