package com.example.envelops.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Green2,
    inversePrimary = Green0,
    secondary = Green1,
    tertiary = Gold1,
    onPrimary = Cream,
    onSecondary = Cream,
    onTertiary = Cream,
    surface = Gold2,
    background = Green0,
    onBackground = Cream,
    onSurface = Cream,
    primaryContainer = Green0,
    secondaryContainer = Green1
)

private val LightColorScheme = lightColorScheme(
    primary = Green2,
    inversePrimary = Green0,
    secondary = Green1,
    tertiary = Gold1,
    onPrimary = Cream,
    onSecondary = Cream,
    onTertiary = Cream,
    surface = Gold2,
    background = Green0,
    onBackground = Cream,
    primaryContainer = Green0,
    onPrimaryContainer = Green2,
    secondaryContainer = Green1,
    onSecondaryContainer = Cream,
    tertiaryContainer = Green00,
)

@Composable
fun EnvelopsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // can provide a different value for Spacing depending on the device being used
//    CompositionLocalProvider(LocalSpacing provides Spacing()) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
//    }
}