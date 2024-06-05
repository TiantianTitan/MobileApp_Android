package com.ndroid.dessert_shop.data

data class User (
    var name : String,
    var email : String,
    var password : String
){
    var id : Int = -1
    constructor(id:Int,name: String,email: String,password: String):this(name,email,password){
        this.id = id
    }
}

