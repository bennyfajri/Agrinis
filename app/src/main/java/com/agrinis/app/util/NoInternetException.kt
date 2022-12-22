package com.agrinis.app.util

import java.io.IOException

/**
 * @author Created by Arca International on 21/11/2022
 */
class NoInternetException : IOException() {
    override val message: String
        get() =
            "No internet available, please check your connected WiFi or Data"
}