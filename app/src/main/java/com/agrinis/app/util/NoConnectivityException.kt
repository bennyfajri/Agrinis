package com.agrinis.app.util

import java.io.IOException

/**
 * @author Created by Arca International on 21/11/2022
 */
class NoConnectivityException : IOException() {
    override val message: String
        get() =
            "No network available, please check your WiFi or Data connection"
}