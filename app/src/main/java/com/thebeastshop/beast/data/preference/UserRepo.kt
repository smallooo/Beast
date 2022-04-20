package com.thebeastshop.beast.data.preference

import kotlinx.coroutines.flow.Flow

interface UserRepo {
    suspend fun saveUserLoggedInState(state:Boolean)
    suspend fun getUserLoggedInState(): Flow<Boolean>
}