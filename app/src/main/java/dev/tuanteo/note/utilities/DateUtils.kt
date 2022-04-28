package dev.tuanteo.note.utilities

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Lớp tính năng liên quan đến thời gian
 */
class DateUtils {
    companion object {

        @RequiresApi(Build.VERSION_CODES.O)
        fun getCurrentDateAsString() : String {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            return current.format(formatter)
        }
    }
}