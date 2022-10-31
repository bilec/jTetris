package utils.random

import java.awt.Color

fun randomColor(): Color = listOf(
    Color.BLUE,
    Color.PINK,
    Color.ORANGE,
    Color.GREEN,
    Color.RED,
    Color.YELLOW
).random()