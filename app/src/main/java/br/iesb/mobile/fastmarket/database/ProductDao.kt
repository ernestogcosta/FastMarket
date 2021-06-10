package br.iesb.mobile.fastmarket.database

import androidx.room.*

@Dao
interface ProductDao {
    @Query("Select * from product where name like '%' || :text || '%'")
    suspend fun getProduct(text: String): List<Product>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(p: Product)

    @Update
    suspend fun updateProduct(p: Product)

    @Query("delete from product")
    suspend fun deleteAll()
}