package com.example.tutorialsproject.selectionfooter

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class SelectionFooterValue(val header: String?, val itemsCount: Int, val cta: Cta?) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readParcelable(Cta::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(header)
        parcel.writeInt(itemsCount)
        parcel.writeParcelable(cta, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SelectionFooterValue> {
        override fun createFromParcel(parcel: Parcel): SelectionFooterValue {
            return SelectionFooterValue(parcel)
        }

        override fun newArray(size: Int): Array<SelectionFooterValue?> {
            return arrayOfNulls(size)
        }
    }
}