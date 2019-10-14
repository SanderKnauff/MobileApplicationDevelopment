package ooo.sansk.shoppinglist.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ooo.sansk.shoppinglist.model.ProductData

@Dao
interface ProductDataDao {

    @Query("SELECT * FROM product_table")
    suspend fun getAllProducts(): List<ProductData>

    @Insert
    suspend fun insertProduct(product: ProductData)

    @Delete
    suspend fun deleteProduct(product: ProductData)

    @Query("DELETE FROM product_table")
    suspend fun deleteAllProducts()

}
