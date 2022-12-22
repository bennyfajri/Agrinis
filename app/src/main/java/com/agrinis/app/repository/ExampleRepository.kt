package com.agrinis.app.repository

import com.agrinis.app.data.models.response.FooResponse
import com.agrinis.app.network.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Created by Arca International on 21/11/2022
 */
interface ExampleRepository {
    // TODO: create function related to accessing data source

    suspend fun foo(): Flow<Resource<FooResponse>>

}