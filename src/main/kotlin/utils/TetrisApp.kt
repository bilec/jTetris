package utils

import gui.frame.GameFrame
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object TetrisApp : KoinComponent {
    val gameFrame: GameFrame by inject()
}