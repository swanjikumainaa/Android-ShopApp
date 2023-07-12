package dev.wanjikode.myshop

data class ProductsResponse(
    var products: List<Product>,
    var total: Int,
    var skip : Int,
    var payment : Int

)
