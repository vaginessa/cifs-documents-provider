package com.wa2c.android.cifsdocumentsprovider.common

import android.system.ErrnoException
import android.system.OsConstants
import com.wa2c.android.cifsdocumentsprovider.common.utils.logE
import kotlinx.coroutines.CoroutineScope
import java.io.IOException

/**
 * Proxy Callback process
 */
@Throws(ErrnoException::class)
fun <T> CoroutineScope.processFileIo(process: () -> T): T {
    return try {
         process()
    } catch (e: IOException) {
        logE(e)
        if (e.cause is ErrnoException) {
            throw (e.cause as ErrnoException)
        } else {
            throw ErrnoException("I/O", OsConstants.EIO, e)
        }
    }
}

/**
 * Get throwable cause.
 */
fun Throwable.getCause(): Throwable {
    val c = cause
    return c?.getCause() ?: return this
}