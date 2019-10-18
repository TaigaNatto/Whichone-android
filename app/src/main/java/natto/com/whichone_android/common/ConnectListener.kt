package natto.com.whichone_android.common

import android.content.Context
import android.widget.Toast

class ConnectListener(private val context: Context) {
    fun onSuccess(): (() -> Unit) = {
        Toast.makeText(context, "成功", Toast.LENGTH_SHORT).show()
    }

    fun onFailed(): ((Exception) -> Unit) = {
        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
    }
}