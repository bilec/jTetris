package gui.panel

import game.Game
import gui.listener.GameKeyListener
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.config.GameMatrix
import utils.config.TetrominoSpeed
import java.awt.Graphics
import javax.swing.JPanel
import javax.swing.Timer

class GamePanel : JPanel(), KoinComponent {
    private val gameMatrix: GameMatrix by inject()
    private val tetrominoSpeed: TetrominoSpeed by inject()
    private val gameKeyListener: GameKeyListener by inject()

    val timer = Timer(tetrominoSpeed.normal) { onTimerTick() }

    init {
        isFocusable = true
        addKeyListener(gameKeyListener)
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        val maxX = (gameMatrix.cellSize * gameMatrix.width) + gameMatrix.padding
        val maxY = (gameMatrix.cellSize * gameMatrix.height) + gameMatrix.padding

        (0..gameMatrix.width).forEach {
            val x = gameMatrix.padding + (gameMatrix.cellSize * it)
            g?.drawLine(x, gameMatrix.padding, x, maxY)
        }

        (0..gameMatrix.height).forEach {
            val y = gameMatrix.padding + (gameMatrix.cellSize * it)
            g?.drawLine(gameMatrix.padding, y, maxX, y)
        }

        val gameBoard = Game.gameBoard
        gameBoard.indices.forEach { i ->
            gameBoard[i].indices.forEach { j ->
                if (gameBoard[i][j] != null) {
                    g?.color = gameBoard[i][j]
                    g?.fillRect((gameMatrix.cellSize * i) + gameMatrix.padding, (gameMatrix.cellSize * j) + gameMatrix.padding, gameMatrix.cellSize, gameMatrix.cellSize)
                }
            }
        }

        if (Game.isRunning) {
            val currentTetromino = Game.currentTetromino
            val currentRotation = currentTetromino.currentRotation()
            currentRotation.indices.forEach { i ->
                currentRotation[i].indices.forEach { j ->
                    if (currentRotation[i][j] == 1) {
                        g?.color = currentTetromino.color
                        g?.fillRect(
                            (gameMatrix.cellSize * j) + (gameMatrix.cellSize * currentTetromino.point.x) + gameMatrix.padding,
                            (gameMatrix.cellSize * i) + (gameMatrix.cellSize * currentTetromino.point.y) + gameMatrix.padding,
                            gameMatrix.cellSize,
                            gameMatrix.cellSize
                        )
                    }
                }
            }
        }
    }

    fun startNewGame() {
        Game.startNewGame()

        timer.delay = tetrominoSpeed.normal

        repaint()
        timer.restart()
    }

    private fun onTimerTick() {
        Game.onTimerTick()
        repaint()
    }
}