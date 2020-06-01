package net.main.data.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false) val id: Long = 0L,
    val movieImg: String?,
    val movieName: String?,
    val movieScore: String?,
    val movieOriginalName: String?,
    val movieRelease: String?,
    val movieDesc: String?
)
