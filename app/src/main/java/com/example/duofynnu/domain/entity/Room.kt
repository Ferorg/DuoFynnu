package com.example.duofynnu.domain.entity

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Room(
    var id: String,
    var nameRoom: String? = null,
    var password: String? = null,
    var content: String? = null,
    var adminId:String
) : Serializable, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nameRoom)
        parcel.writeString(password)
        parcel.writeString(content)
        parcel.writeString(adminId)
    }

    companion object CREATOR : Parcelable.Creator<Room> {
        override fun createFromParcel(parcel: Parcel): Room {
            return Room(parcel)
        }

        override fun newArray(size: Int): Array<Room?> {
            return arrayOfNulls(size)
        }
    }
}