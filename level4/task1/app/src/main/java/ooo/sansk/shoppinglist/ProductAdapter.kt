package ooo.sansk.shoppinglist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.product_data_view.view.*
import ooo.sansk.shoppinglist.model.ProductData

class ProductAdapter(val productDataList: List<ProductData>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.product_data_view, parent, false))
    }

    override fun getItemCount(): Int {
        return productDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.bind(productDataList[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(productData: ProductData) {
            itemView.tvCount.text = productData.count.toString()
            itemView.tvName.text = productData.name
        }

    }
}