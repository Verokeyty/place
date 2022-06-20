package com.place.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="place")
data class Place(
   @PrimaryKey(autoGenerate = true)
   val id:Int,
   @ColumnInfo(name="nombre")
   val nombre: String,
   @ColumnInfo(name="correo")
   val correo: String?,
   @ColumnInfo(name="telefono")
   val telefono: String,
   @ColumnInfo(name="link")
   val link: String,
   @ColumnInfo(name="latitud")
   val latidud: Double?,
   @ColumnInfo(name="longitud")
   val longitud: Double?,
   @ColumnInfo(name="altura")
   val altura: Double?,
   @ColumnInfo(name="rutaAudio")
   val rutaAudio: String?,
   @ColumnInfo(name="rutaImg")
   val rutaImg: String?,
): Parcelable