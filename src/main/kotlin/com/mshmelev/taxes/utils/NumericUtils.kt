package com.mshmelev.taxes.utils

fun String.parseAsInt() =
        try {
            this.toInt()
        } catch (e: Exception) {
            0
        }

fun String.parseAsDouble() =
        try {
            this.toDouble()
        } catch (e: Exception) {
            0.0
        }

fun String.parseAsLong() =
        try {
            this.toLong()
        } catch (e: Exception) {
            0L
        }