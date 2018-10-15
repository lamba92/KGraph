package it.lamba.utils

import kotlin.js.Date
import kotlin.math.roundToLong

actual fun getCurrentTimeInMillis() = Date.now().roundToLong()