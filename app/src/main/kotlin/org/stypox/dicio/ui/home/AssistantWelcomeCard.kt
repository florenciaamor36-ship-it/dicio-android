package org.stypox.dicio.ui.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.stypox.dicio.R
import org.stypox.dicio.io.input.SttState

@Composable
fun AssistantWelcomeCard(
    sttState: SttState?,
    modifier: Modifier = Modifier,
) {
    val listening = sttState == SttState.Listening
    val scale by animateFloatAsState(
        targetValue = if (listening) 1.06f else 1f,
        animationSpec = spring(dampingRatio = 0.48f, stiffness = 360f),
        label = "assistant-avatar-listening-scale",
    )
    val status = when (sttState) {
        null -> stringResource(R.string.assistant_state_text_only)
        SttState.NoMicrophonePermission -> stringResource(R.string.assistant_state_microphone_permission)
        SttState.NotAvailable -> stringResource(R.string.assistant_state_voice_unavailable)
        SttState.NotDownloaded, SttState.Downloaded, SttState.NotLoaded -> stringResource(R.string.assistant_state_voice_setup)
        is SttState.Downloading, is SttState.Unzipping, is SttState.Loading, SttState.NotInitialized -> stringResource(R.string.assistant_state_preparing)
        is SttState.ErrorDownloading, is SttState.ErrorUnzipping, is SttState.ErrorLoading -> stringResource(R.string.assistant_state_voice_error)
        SttState.Listening -> stringResource(R.string.assistant_state_listening)
        SttState.Loaded -> stringResource(R.string.assistant_state_ready)
        SttState.WaitingForResult -> stringResource(R.string.assistant_state_waiting)
    }

    Row(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AssistantFace(
            modifier = Modifier
                .size(76.dp)
                .graphicsLayer(scaleX = scale, scaleY = scale),
            listening = listening,
        )
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = stringResource(R.string.assistant_home_title),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = status,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun AssistantFace(
    modifier: Modifier = Modifier,
    listening: Boolean,
) {
    val face = MaterialTheme.colorScheme.primaryContainer
    val features = MaterialTheme.colorScheme.onPrimaryContainer
    Canvas(modifier = modifier.clip(CircleShape)) {
        val unit = size.minDimension
        drawCircle(color = face, radius = unit * 0.49f)
        val eyeRadius = unit * 0.045f
        drawCircle(features, eyeRadius, center = androidx.compose.ui.geometry.Offset(unit * 0.36f, unit * 0.40f))
        drawCircle(features, eyeRadius, center = androidx.compose.ui.geometry.Offset(unit * 0.64f, unit * 0.40f))

        val mouth = Path().apply {
            moveTo(unit * 0.34f, unit * 0.61f)
            quadraticTo(
                unit * 0.50f,
                if (listening) unit * 0.80f else unit * 0.74f,
                unit * 0.66f,
                unit * 0.61f,
            )
        }
        drawPath(
            path = mouth,
            color = features,
            style = Stroke(width = unit * 0.045f, cap = androidx.compose.ui.graphics.StrokeCap.Round),
        )
    }
}
