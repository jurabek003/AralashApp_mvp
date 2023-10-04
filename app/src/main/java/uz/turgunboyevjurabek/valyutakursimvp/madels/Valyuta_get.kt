package uz.turgunboyevjurabek.valyutakursimvp.madels


import com.google.gson.annotations.SerializedName

data class Valyuta_get(
    @SerializedName("Ccy")
    val ccy: String,
    @SerializedName("CcyNm_EN")
    val ccyNmEN: String,
    @SerializedName("CcyNm_RU")
    val ccyNmRU: String,
    @SerializedName("CcyNm_UZ")
    val ccyNmUZ: String,
    @SerializedName("CcyNm_UZC")
    val ccyNmUZC: String,
    @SerializedName("Code")
    val code: String,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Diff")
    val diff: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("Nominal")
    val nominal: String,
    @SerializedName("Rate")
    val rate: String
)