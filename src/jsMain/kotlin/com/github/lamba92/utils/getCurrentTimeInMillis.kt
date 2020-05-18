package com.github.lamba92.utils

import kotlin.js.Date
import kotlin.math.roundToLong

actual fun getCurrentTimeInMillis() = Date.now().roundToLong()
