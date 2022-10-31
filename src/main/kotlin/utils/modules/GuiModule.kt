package utils.modules

import gui.frame.GameFrame
import gui.label.InfoLabel
import gui.listener.GameKeyListener
import gui.menubar.GameMenuBar
import gui.panel.GamePanel
import org.koin.dsl.module

val guiModule = module {
    single { GameFrame() }
    single { GamePanel() }
    single { GameMenuBar() }
    single { InfoLabel() }

    single { GameKeyListener() }
}