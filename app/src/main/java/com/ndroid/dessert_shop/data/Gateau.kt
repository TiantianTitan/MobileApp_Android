package com.ndroid.dessert_shop.data

class Gateau(
    var titre : String,
    var description : String ,
    var image : ByteArray,
    var prix : String,
    var description_complet: String
){
    var id : Int = -1
    constructor(id:Int,titre: String,description: String,image: ByteArray,prix: String,description_complet: String):this(titre,description,image,prix,description_complet){
        this.id = id
    }
}