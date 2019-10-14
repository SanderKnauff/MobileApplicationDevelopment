package ooo.sansk.shoppinglist.database

import android.content.Context
import ooo.sansk.shoppinglist.model.ProductData

class ProductDataRepository(context: Context) {

    private val productDao: ProductDataDao

    init {
        val database =
            ShoppingListRoomDatabase.getDatabase(context)
        productDao = database!!.productDao()
    }

    suspend fun getAllProducts(): List<ProductData> {
        return productDao.getAllProducts()
    }

    suspend fun insertProduct(product: ProductData) {
        productDao.insertProduct(product)
    }

    suspend fun deleteProduct(product: ProductData) {
        productDao.deleteProduct(product)
    }

    suspend fun deleteAllProducts() {
        productDao.deleteAllProducts()
    }

}
