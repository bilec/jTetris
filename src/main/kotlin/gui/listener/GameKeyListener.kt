package gui.listener

import game.Game
import game.isTetrominoMovableToLeft
import game.isTetrominoMovableToRight
import game.isTetrominoRotatable
import gui.label.InfoLabel
import gui.panel.GamePanel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.config.NumberOfPossibleRotations
import utils.config.TetrominoSpeed
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class GameKeyListener : KeyListener, KoinComponent {
    private val tetrominoSpeed: TetrominoSpeed by inject()
    private val numberOfPossibleRotations: NumberOfPossibleRotations by inject()

    private val gamePanel: GamePanel by inject()
    private val infoLabel: InfoLabel by inject()

    override fun keyTyped(e: KeyEvent?) { /* NOT NEEDED */ }

    override fun keyPressed(e: KeyEvent?) {
        if (Game.isRunning && gamePanel.timer.isRunning) {
            when (e?.keyCode) {
                KeyEvent.VK_LEFT -> if (isTetrominoMovableToLeft(Game.currentTetromino, Game.gameBoard)) Game.currentTetromino.point.x--
                KeyEvent.VK_RIGHT -> if (isTetrominoMovableToRight(Game.currentTetromino, Game.gameBoard)) Game.currentTetromino.point.x++
                KeyEvent.VK_DOWN -> gamePanel.timer.delay = tetrominoSpeed.fast
            }
            gamePanel.repaint()
        }
    }

    override fun keyReleased(e: KeyEvent?) {
        if (Game.isRunning) {
            if (gamePanel.timer.isRunning) {
                when (e?.keyCode) {
                    KeyEvent.VK_UP -> {
                        if (isTetrominoRotatable(Game.currentTetromino, Game.gameBoard)
                            && Game.rotationsMade < numberOfPossibleRotations.value) {
                            Game.currentTetromino.rotate()
                            Game.rotationsMade++
                        }
                    }
                    KeyEvent.VK_DOWN -> gamePanel.timer.delay = tetrominoSpeed.normal
                }
            }

            if (e?.keyCode == KeyEvent.VK_P) if (gamePanel.timer.isRunning) {
                gamePanel.timer.stop()
                infoLabel.setPaused()
            } else {
                gamePanel.timer.start()
                infoLabel.setScore(Game.score)
            }

            gamePanel.repaint()
        }
    }
}