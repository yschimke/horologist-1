/*
 * Copyright 2022 The Android Open Source Project
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

@file:Suppress("DEPRECATION")

package com.google.android.horologist.mediasample.data.auth

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.auth.data.common.model.AuthUser
import com.google.android.horologist.auth.data.common.repository.AuthUserRepository
import com.google.android.horologist.auth.data.googlesignin.GoogleSignInEventListener
import com.google.android.horologist.mediasample.BuildConfig
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

/**
 * An implementation of [AuthUserRepository] for the Google Sign-In authentication method.
 */
@ExperimentalHorologistApi
public class CredManAuthUserRepository(
    private val applicationContext: Context,
    private val credentialManager: CredentialManager,
) : AuthUserRepository, GoogleSignInEventListener {
    // simple way to trigger refreshes to the sync GoogleSignIn.getLastSignedInAccount
    private val _authTrigger = MutableStateFlow(0)

    public val authState: Flow<AuthUser?> = _authTrigger.map { getAuthenticated() }

    override suspend fun getAuthenticated(): AuthUser? = withContext(Dispatchers.IO) {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            .setServerClientId(BuildConfig.GSI_CLIENT_ID)
            .setAutoSelectEnabled(true)
        .build()

        try {
            val result = credentialManager.getCredential(
                request = GetCredentialRequest(listOf(googleIdOption)),
                context = applicationContext,
            )
            val credentials = GoogleIdTokenCredential.createFrom(result.credential.data)
            AuthUser(
                displayName = credentials.displayName,
                email = null,
                avatarUri = credentials.profilePictureUri.toString(),
            )
        } catch (e: NoCredentialException) {
            e.printStackTrace()
            null
        } catch (e: GetCredentialException) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun onSignedIn(account: GoogleSignInAccount) {
        _authTrigger.update { it + 1 }
    }

    public fun onSignedOut() {
        _authTrigger.update { it + 1 }
    }

    public suspend fun signOut() {
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
        onSignedOut()
    }
}
