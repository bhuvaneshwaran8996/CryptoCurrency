package com.example.cryptocurrency.mvvm.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.example.cryptocurrency.mvvm.db.CryptoDataConverter


@Entity(tableName = "CryptoModel")
data class CryptoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    public val `data`: List<Data>? = null,
    val timestamp: Long = 12900
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.createTypedArrayList(Data),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeTypedList(data)
        parcel.writeLong(timestamp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CryptoModel> {
        override fun createFromParcel(parcel: Parcel): CryptoModel {
            return CryptoModel(parcel)
        }

        override fun newArray(size: Int): Array<CryptoModel?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return super.toString()
    }
}

