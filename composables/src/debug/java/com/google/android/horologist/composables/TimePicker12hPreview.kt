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

package com.google.android.horologist.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import androidx.wear.compose.ui.tooling.preview.WearPreviewFontScales
import androidx.wear.tooling.preview.devices.WearDevices
import com.google.android.horologist.compose.tools.copy
import java.time.LocalTime

@WearPreviewDevices
@WearPreviewFontScales
@Composable
fun TimePicker12hPreview() {
    TimePickerWith12HourClock(
        time = LocalTime.of(10, 10, 0),
        onTimeConfirm = {},
    )
}

@Composable
@Preview(
    device = WearDevices.SMALL_ROUND,
    showSystemUi = true,
    backgroundColor = 0xff000000,
    showBackground = true,
    group = "Fonts - Largest",
    fontScale = 1.24f,
)
fun TimePicker12hPreviewSmallDeviceWithLargeFontBold() {
    MaterialTheme(typography = MaterialTheme.typography.copy { this.copy(fontWeight = FontWeight.Bold) }) {
        TimePickerWith12HourClock(
            time = LocalTime.of(10, 0, 0),
            onTimeConfirm = {},
        )
    }
}

@Composable
@Preview(
    device = WearDevices.LARGE_ROUND,
    showSystemUi = true,
    backgroundColor = 0xff000000,
    showBackground = true,
    group = "Fonts - Small",
    fontScale = 0.94f,
)
fun TimePicker12hPreviewLargeDeviceSmallFont() {
    TimePickerWith12HourClock(
        time = LocalTime.of(10, 0, 0),
        onTimeConfirm = {},
    )
}
