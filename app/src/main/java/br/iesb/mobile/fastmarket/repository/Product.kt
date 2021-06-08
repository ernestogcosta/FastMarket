package br.iesb.mobile.fastmarket.repository

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [
    Index(value = ["name"], unique = true)
])
data class Product(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String? = null
)
