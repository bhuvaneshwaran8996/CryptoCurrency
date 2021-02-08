package com.example.cryptocurrency.mvvm.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


data class Data(


    val changePercent24Hr: String? ="",
    val explorer: String?="",
    val marketCapUsd: String?="",
    val maxSupply: String?="",
    val name: String?="",
    val priceUsd: String?="",

    val rank: String?="",
    val supply: String?="",
    val symbol: String?="",
    val volumeUsd24Hr: String?="",
    val vwap24Hr: String?=""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(changePercent24Hr)
        parcel.writeString(explorer)
        parcel.writeString(marketCapUsd)
        parcel.writeString(maxSupply)
        parcel.writeString(name)
        parcel.writeString(priceUsd)
        parcel.writeString(rank)
        parcel.writeString(supply)
        parcel.writeString(symbol)
        parcel.writeString(volumeUsd24Hr)
        parcel.writeString(vwap24Hr)
    }

    public fun priceUsd(): String {
        return "$"+String.format("%.2f", priceUsd?.toFloat());
    }

    public fun priceUsd24(): String {
        return String.format("%.1f", changePercent24Hr?.toFloat());
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Data> {
        override fun createFromParcel(parcel: Parcel): Data {
            return Data(parcel)
        }

        override fun newArray(size: Int): Array<Data?> {
            return arrayOfNulls(size)
        }
    }
}
