package com.agrinis.app.data.models.response

import com.agrinis.app.data.models.Bar
import com.agrinis.app.data.models.Foo
import com.squareup.moshi.Json

/**
 * @author Created by Muhamad Jalaludin on 21/11/2022
 */
data class FooResponse(
    @Json(name = "foo")
    val foo: Foo,
    @Json(name = "bar")
    val bar: Bar
)
