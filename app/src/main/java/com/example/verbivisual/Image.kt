package com.example.verbivisual

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    val id:String,
    @SerializedName("gallery")
    val gallery:String,
    @SerializedName("src")
    val src:String,
    @SerializedName("srcSmall")
    val srcSmall:String,
    @SerializedName("prompt")
    val prompt:String,
    @SerializedName("width")
    val width:Int,
    @SerializedName("height")
    val height:Int,
    @SerializedName("seed")
    val seed:String,
    @SerializedName("grid")
    val grid:Boolean,
    @SerializedName("model")
    val model:String,
    @SerializedName("guidance")
    val guidance:String,
    @SerializedName("promptid")
    val promptid:String,
    @SerializedName("nsfw")
    val nsfw:Boolean

)
