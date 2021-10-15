package com.test.kumparan.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    val id : Int = 0,
    val name : String = "",
    @SerializedName("username")
    val userName: String = "",
    val email: String = "",
    val address: AddressModel? = null,
    val phone : String = "",
    val website: String = "",
    val company: CompanyModel? = null
)