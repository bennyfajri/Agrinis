package com.agrinis.app.repository

import com.agrinis.app.di.persistence.dao.FooDao
import com.agrinis.app.network.ApiService
import com.agrinis.app.network.request
import kotlinx.coroutines.flow.flow

/**
 * @author Created by Arca International on 21/11/2022
 */
class ExampleRepositoryImpl(
    private val service: ApiService,
    private val dao: FooDao
) : ExampleRepository {
    // TODO: override/implements function from ExampleRepository

    override suspend fun foo() = flow {
        request { service.foo() }
            .collect { result ->
                emit(result)
            }
    }


}