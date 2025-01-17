package com.iccas.zen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PlayControlButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    background: Color,
    imageVector: ImageVector,
    iconColor: Color,
    iconSize: Dp,
    contentDescription: String
) {
    Box(
        modifier = modifier
            .background(background, RoundedCornerShape(50))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            modifier = Modifier.size(iconSize),
            tint = iconColor
        )
    }
}

@Composable
fun ReplayAndExitControlButtons(
    modifier: Modifier = Modifier,
    onReplay: () -> Unit,
    onExit: () -> Unit,
    replayButtonBackground: Color,
    exitButtonBackground: Color,
    replayIconColor: Color,
    exitIconColor: Color,
    iconSize: Dp
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayControlButton(
            modifier = modifier,
            onClick = { onReplay() },
            background = replayButtonBackground,
            imageVector = Icons.Filled.Refresh,
            contentDescription = "Replay",
            iconColor = replayIconColor,
            iconSize = iconSize
        )
        Spacer(modifier = Modifier.width(15.dp))

        PlayControlButton(
            modifier = modifier,
            onClick = { onExit() },
            background = exitButtonBackground,
            imageVector = Icons.Filled.ExitToApp,
            contentDescription = "Exit",
            iconColor = exitIconColor,
            iconSize = iconSize
        )
    }
}