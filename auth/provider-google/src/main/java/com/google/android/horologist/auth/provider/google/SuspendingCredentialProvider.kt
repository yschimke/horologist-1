/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.horologist.auth.provider.google

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CreateCredentialRequest
import androidx.credentials.CreateCredentialResponse
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.ClearCredentialUnsupportedException
import androidx.credentials.exceptions.CreateCredentialUnsupportedException
import androidx.credentials.exceptions.GetCredentialUnsupportedException
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

abstract class SuspendingCredentialProvider {
    abstract val types: List<String>?

    open suspend fun isAvailableOnDevice(): Boolean {
        return true
    }

    open suspend fun onClearCredential(
        request: ClearCredentialStateRequest,
    ) {
        throw ClearCredentialUnsupportedException()
    }

    open suspend fun onCreateCredential(
        context: Context,
        request: CreateCredentialRequest,
    ): CreateCredentialResponse {
        throw CreateCredentialUnsupportedException()
    }

    open suspend fun getExistingCredential(
        context: Context,
        request: GetCredentialRequest,
    ): GetCredentialResponse {
        throw GetCredentialUnsupportedException()
    }

    open suspend fun getPromptedCredential(
        context: Context,
        request: GetCredentialRequest,
    ): GetCredentialResponse {
        throw GetCredentialUnsupportedException()
    }

    abstract val startRoute: Any?

    open fun NavGraphBuilder.defineRoutes(
        navController: NavHostController,
        onCompletion: (Result<GetCredentialResponse>) -> Unit,
    ) {
    }

    open fun supportedRoutes(
        request: GetCredentialRequest,
        onNavigate: (Any) -> Unit,
    ): List<MenuChip> {
        return listOf()
    }

    @Stable
    class MenuChip(
        val route: Any,
        val chip: @Composable () -> Unit,
    )
}

val GetCredentialRequest.types: List<String>
    get() = this.credentialOptions.map { it.type }
