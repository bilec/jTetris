package gui.menubar

import gui.frame.HelpFrame
import gui.panel.GamePanel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.data.IsHelpVisible
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import kotlin.system.exitProcess

class GameMenuBar : JMenuBar(), KoinComponent {
    private val gamePanel: GamePanel by inject()
    private val isHelpVisible: IsHelpVisible by inject()

    init {
        val gameMenu = JMenu("Game")
        val helpMenu = JMenuItem("Help")

        val newGameItem = JMenuItem("New game")
        val exitGameItem = JMenuItem("Exit game")

        gameMenu.add(newGameItem)
        gameMenu.add(exitGameItem)

        newGameItem.addActionListener {
            gamePanel.startNewGame()
        }

        exitGameItem.addActionListener {
            exitProcess(0)
        }

        helpMenu.addActionListener {
            if (!isHelpVisible.value) {
                isHelpVisible.value = true
                HelpFrame()
            }
        }

        add(gameMenu)
        add(helpMenu)
    }
}