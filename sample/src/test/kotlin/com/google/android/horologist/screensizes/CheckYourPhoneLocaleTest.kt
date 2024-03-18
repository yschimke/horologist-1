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

package com.google.android.horologist.screensizes

import androidx.compose.ui.test.DeviceConfigurationOverride
import androidx.compose.ui.test.Locales
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.wear.compose.material.MaterialTheme
import com.google.android.horologist.auth.composables.screens.CheckYourPhoneScreen
import com.google.android.horologist.screenshots.rng.WearDevice
import com.google.android.horologist.screenshots.rng.WearScreenshotTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner

@RunWith(ParameterizedRobolectricTestRunner::class)
class CheckYourPhoneLocaleTest(val localeString: String) : WearScreenshotTest() {
    override val device: WearDevice = WearDevice.GooglePixelWatchLargeFont

    @Test
    fun initial() = runTest(suffix = "_$localeString") {
        DeviceConfigurationOverride(
            override = DeviceConfigurationOverride.Locales(
                LocaleList(
                    Locale(
                        localeString
                    )
                )
            )
        ) {
            MaterialTheme(typography = MaterialTheme.typography.copy()) {
                CheckYourPhoneScreen()
            }
        }
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun locales() = listOf(
            "ar",
            "as",
            "b+es+419",
            "bg",
            "bn",
            "ca",
            "cs",
            "da",
            "de",
            "el",
            "en-rGB",
            "en-rIE",
            "en",
            "es-rUS",
            "es",
            "et",
            "eu",
            "fa",
            "fi",
            "fr-rCA",
            "fr",
            "gl",
            "gu",
            "hi",
            "hr",
            "hu",
            "hy",
            "in",
            "is",
            "it",
            "iw",
            "ja",
            "ka",
            "kk",
            "km",
            "kn",
            "ko",
            "ky",
            "lt",
            "lv",
            "mk",
            "ml",
            "mn",
            "mr",
            "ms",
            "my",
            "nb",
            "ne",
            "nl",
            "or",
            "pa",
            "pl",
            "pt-rBR",
            "pt-rPT",
            "ro",
            "ru",
            "si",
            "sk",
            "sl",
            "sq",
            "sr",
            "sv",
            "ta",
            "te",
            "th",
            "tr",
            "uk",
            "ur",
            "uz",
            "vi",
            "zh-rCN",
            "zh-rHK",
            "zh-rTW",
        )
    }
}
