package com.vladgrushevoy.homework3_1

import java.io.Serializable

data class SSD(
    var name: String,
    var description: String,
    var image: Int
) : Serializable