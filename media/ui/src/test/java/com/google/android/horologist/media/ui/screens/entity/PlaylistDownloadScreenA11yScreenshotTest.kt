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

package com.google.android.horologist.media.ui.screens.entity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.MaterialTheme
import com.google.android.horologist.compose.layout.AppScaffold
import com.google.android.horologist.compose.layout.ResponsiveTimeText
import com.google.android.horologist.compose.pager.PagerScreen
import com.google.android.horologist.images.base.util.rememberVectorPainter
import com.google.android.horologist.media.ui.state.model.DownloadMediaUiModel
import com.google.android.horologist.media.ui.state.model.PlaylistUiModel
import com.google.android.horologist.screenshots.FixedTimeSource
import com.google.android.horologist.screenshots.rng.WearLegacyA11yTest
import org.junit.Test

class PlaylistDownloadScreenA11yScreenshotTest :
    WearLegacyA11yTest() {
    @Test
    fun playlistDownloadScreenPreviewLoading() {
        runScreenTest {
            PlaylistDownloadScreen(
                playlistName = "Playlist name",
                playlistDownloadScreenState = PlaylistDownloadScreenState.Loading,
                onDownloadButtonClick = { },
                onCancelDownloadButtonClick = { },
                onDownloadItemClick = { },
                onDownloadItemInProgressClick = { },
                onShuffleButtonClick = { },
                onPlayButtonClick = { },
                onDownloadItemInProgressClickActionLabel = "cancel",
            )
        }
    }

    @Test
    fun playlistDownloadScreenPreviewLoadedNoneDownloaded() {
        runScreenTest {
            PlaylistDownloadScreen(
                playlistName = "Playlist name",
                playlistDownloadScreenState = createPlaylistDownloadScreenStateLoaded(
                    playlistModel = playlistUiModel,
                    downloadMediaList = notDownloaded,
                ),
                onDownloadButtonClick = { },
                onCancelDownloadButtonClick = { },
                onDownloadItemClick = { },
                onDownloadItemInProgressClick = { },
                onShuffleButtonClick = { },
                onPlayButtonClick = { },
                downloadItemArtworkPlaceholder = rememberVectorPainter(
                    image = Icons.Default.MusicNote,
                    tintColor = Color.Blue,
                ),
                onDownloadItemInProgressClickActionLabel = "cancel",
            )
        }
    }

    @Test
    fun playlistDownloadScreenPreviewLoadedNoneDownloadedDownloading() {
        runScreenTest {
            PlaylistDownloadScreen(
                playlistName = "Playlist name",
                playlistDownloadScreenState = createPlaylistDownloadScreenStateLoaded(
                    playlistModel = playlistUiModel,
                    downloadMediaList = notDownloadedAndDownloading,
                ),
                onDownloadButtonClick = { },
                onCancelDownloadButtonClick = { },
                onDownloadItemClick = { },
                onDownloadItemInProgressClick = { },
                onShuffleButtonClick = { },
                onPlayButtonClick = { },
                downloadItemArtworkPlaceholder = rememberVectorPainter(
                    image = Icons.Default.MusicNote,
                    tintColor = Color.Blue,
                ),
                onDownloadItemInProgressClickActionLabel = "cancel",
            )
        }
    }

    @Test
    fun playlistDownloadScreenPreviewLoadedPartiallyDownloaded() {
        runScreenTest {
            PlaylistDownloadScreen(
                playlistName = "Playlist name",
                playlistDownloadScreenState = createPlaylistDownloadScreenStateLoaded(
                    playlistModel = playlistUiModel,
                    downloadMediaList = downloadedNotDownloaded,
                ),
                onDownloadButtonClick = { },
                onCancelDownloadButtonClick = { },
                onDownloadItemClick = { },
                onDownloadItemInProgressClick = { },
                onShuffleButtonClick = { },
                onPlayButtonClick = { },
                downloadItemArtworkPlaceholder = rememberVectorPainter(
                    image = Icons.Default.MusicNote,
                    tintColor = Color.Blue,
                ),
                onDownloadItemInProgressClickActionLabel = "cancel",
            )
        }
    }

    @Test
    fun playlistDownloadScreenPreviewLoadedPartiallyDownloadedDownloadingUnknownSize() {
        runScreenTest {
            PlaylistDownloadScreen(
                playlistName = "Playlist name",
                playlistDownloadScreenState = createPlaylistDownloadScreenStateLoaded(
                    playlistModel = playlistUiModel,
                    downloadMediaList = downloadedAndDownloadingUnknown,
                ),
                onDownloadButtonClick = { },
                onCancelDownloadButtonClick = { },
                onDownloadItemClick = { },
                onDownloadItemInProgressClick = { },
                onShuffleButtonClick = { },
                onPlayButtonClick = { },
                downloadItemArtworkPlaceholder = rememberVectorPainter(
                    image = Icons.Default.MusicNote,
                    tintColor = Color.Blue,
                ),
                onDownloadItemInProgressClickActionLabel = "cancel",
            )
        }
    }

    @Test
    fun playlistDownloadScreenPreviewLoadedPartiallyDownloadedDownloadingWaiting() {
        runScreenTest {
            PlaylistDownloadScreen(
                playlistName = "Playlist name",
                playlistDownloadScreenState = createPlaylistDownloadScreenStateLoaded(
                    playlistModel = playlistUiModel,
                    downloadMediaList = downloadedAndDownloadingWaiting,
                ),
                onDownloadButtonClick = { },
                onCancelDownloadButtonClick = { },
                onDownloadItemClick = { },
                onDownloadItemInProgressClick = { },
                onShuffleButtonClick = { },
                onPlayButtonClick = { },
                downloadItemArtworkPlaceholder = rememberVectorPainter(
                    image = Icons.Default.MusicNote,
                    tintColor = Color.Blue,
                ),
                onDownloadItemInProgressClickActionLabel = "cancel",
            )
        }
    }

    @Test
    fun playlistDownloadScreenPreviewLoadedFullyDownloaded() {
        runScreenTest {
            PlaylistDownloadScreen(
                playlistName = "Playlist name",
                playlistDownloadScreenState = createPlaylistDownloadScreenStateLoaded(
                    playlistModel = playlistUiModel,
                    downloadMediaList = downloaded,
                ),
                onDownloadButtonClick = { },
                onCancelDownloadButtonClick = { },
                onDownloadItemClick = { },
                onDownloadItemInProgressClick = { },
                onShuffleButtonClick = { },
                onPlayButtonClick = { },
                downloadItemArtworkPlaceholder = rememberVectorPainter(
                    image = Icons.Default.MusicNote,
                    tintColor = Color.Blue,
                ),
                onDownloadItemInProgressClickActionLabel = "cancel",
            )
        }
    }

    @Test
    fun playlistDownloadScreenPreviewFailed() {
        runScreenTest {
            PlaylistDownloadScreen(
                playlistName = "Playlist name",
                playlistDownloadScreenState = PlaylistDownloadScreenState.Failed,
                onDownloadButtonClick = { },
                onCancelDownloadButtonClick = { },
                onDownloadItemClick = { },
                onDownloadItemInProgressClick = { },
                onShuffleButtonClick = { },
                onPlayButtonClick = { },
                onDownloadItemInProgressClickActionLabel = "cancel",
            )
        }
    }

    @Composable
    override fun TestScaffold(content: @Composable () -> Unit) {
        AppScaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            timeText = { ResponsiveTimeText(timeSource = FixedTimeSource) },
        ) {
            PagerScreen(
                state = rememberPagerState {
                    2
                },
            ) {
                if (it == 0) {
                    content()
                }
            }
        }
    }
}

private val playlistUiModel = PlaylistUiModel(
    id = "id",
    title = "Playlist name",
)

private val notDownloaded = listOf(
    DownloadMediaUiModel.NotDownloaded(
        id = "id",
        title = "Song name",
        artist = "Artist name",
        artworkUri = "artworkUri",
    ),
    DownloadMediaUiModel.NotDownloaded(
        id = "id 2",
        title = "Song name 2",
        artist = "Artist name 2",
        artworkUri = "artworkUri",
    ),
)

private val notDownloadedAndDownloading = listOf(
    DownloadMediaUiModel.NotDownloaded(
        id = "id",
        title = "Song name",
        artist = "Artist name",
        artworkUri = "artworkUri",
    ),
    DownloadMediaUiModel.Downloading(
        id = "id 2",
        title = "Song name 2",
        progress = DownloadMediaUiModel.Progress.InProgress(78f),
        size = DownloadMediaUiModel.Size.Known(sizeInBytes = 123456L),
        artworkUri = "artworkUri",
    ),
)

private val downloadedAndDownloadingUnknown = listOf(
    DownloadMediaUiModel.Downloaded(
        id = "id",
        title = "Song name",
        artist = "Artist name",
        artworkUri = "artworkUri",
    ),
    DownloadMediaUiModel.Downloading(
        id = "id 2",
        title = "Song name 2",
        progress = DownloadMediaUiModel.Progress.InProgress(78f),
        size = DownloadMediaUiModel.Size.Unknown,
        artworkUri = "artworkUri",
    ),
)

private val downloadedAndDownloadingWaiting = listOf(
    DownloadMediaUiModel.Downloaded(
        id = "id",
        title = "Song name",
        artist = "Artist name",
        artworkUri = "artworkUri",
    ),
    DownloadMediaUiModel.Downloading(
        id = "id 2",
        title = "Song name 2",
        progress = DownloadMediaUiModel.Progress.Waiting,
        size = DownloadMediaUiModel.Size.Unknown,
        artworkUri = "artworkUri",
    ),
)

private val downloadedNotDownloaded = listOf(
    DownloadMediaUiModel.Downloaded(
        id = "id",
        title = "Song name",
        artist = "Artist name",
        artworkUri = "artworkUri",
    ),
    DownloadMediaUiModel.NotDownloaded(
        id = "id 2",
        title = "Song name 2",
        artist = "Artist name 2",
        artworkUri = "artworkUri",
    ),
)

private val downloaded = listOf(
    DownloadMediaUiModel.Downloaded(
        id = "id",
        title = "Song name",
        artist = "Artist name",
        artworkUri = "artworkUri",
    ),
    DownloadMediaUiModel.Downloaded(
        id = "id 2",
        title = "Song name 2",
        artist = "Artist name 2",
        artworkUri = "artworkUri",
    ),
)
