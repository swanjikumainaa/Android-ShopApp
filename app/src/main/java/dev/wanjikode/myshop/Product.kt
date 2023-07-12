package dev.wanjikode.myshop

data class Product(
    var id : Int,
    var title : String,
    var description : String,
    var price : Double,
    var rating : Double,
    var stock : Int,
    var brand : String,
    var category : String,
    var thumbnail : String

    )
