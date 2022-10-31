import org.koin.core.context.startKoin
import utils.TetrisApp
import utils.modules.allModules

fun main() {
    startKoin {
        modules(allModules)
    }
    TetrisApp.gameFrame
}