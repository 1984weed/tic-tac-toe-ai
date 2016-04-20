package client

import ai._

import scala.scalajs.js
import js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.jquery

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.scalajs.js.Date
import scala.scalajs.js.timers._
import scala.util.Random

object TicTacToe extends js.JSApp{
  val player = new Player(0)
  val opponent = new Player(1)
  val board = new Board(3, 3)
  var game = new GameState(player, opponent, board)

  def main(): Unit = {

    val $inputRadios = jquery.jQuery(".tic-tac-toe input[type='radio']")

    $inputRadios.addClass("player-1")
    $inputRadios.change({
      (e: dom.Event) =>
        val $spaceRadios = jquery.jQuery(".tic-tac-toe input[type='radio']:not(:checked)")
        val loading = jquery.jQuery(".loading")

        val xy: List[String] = jquery.jQuery(e.currentTarget).`val`().asInstanceOf[String].split("-").toList

        if(jquery.jQuery(e.currentTarget).hasClass("player-1")){
          game = game.move(Integer.parseInt(xy(1)), Integer.parseInt(xy(0)))
          loading.show()
          thinkAi(game)
          loading.hide()
        } else{
          playerConvert("player-2", "player-1")
        }
    })
    addEventListener()
  }
  def addEventListener(): Unit = {
    val $firstPlayer = jquery.jQuery(".tic-tac-toe .first-player")
    val $secondPlayer = jquery.jQuery(".tic-tac-toe .second-player")
    val disableModal = jquery.jQuery(".tic-tac-toe .disable")

    $firstPlayer.click({(e: dom.Event) =>
      game = new GameState(player, opponent, board)
      disableModal.hide()
    })

    $secondPlayer.click({(e: dom.Event) =>
      game = new GameState(opponent, player , board)
      disableModal.hide()
      Random.setSeed(new Date().getTime().toLong)
      game = moveGameState(game, Random.nextInt(3), Random.nextInt(3))
    })
  }
  def moveGameState(gameState: GameState, x: Int, y: Int): GameState ={
    val $player1Radios = jquery.jQuery(".tic-tac-toe input[type='radio'].player-1:checked")
    val $player2Radios = jquery.jQuery(".tic-tac-toe input[type='radio'].player-2:checked")
    val $spaceRadios = jquery.jQuery(".tic-tac-toe input[type='radio']:not(:checked)")

    playerConvert("player-1", "player-2")

    jquery.jQuery("#block" + y + "-" + x).prop("checked", true)
    game = game.move(x, y)

    val $spaceRadios2 = jquery.jQuery(".tic-tac-toe input[type='radio']:not(:checked)")

    playerConvert("player-2", "player-1")

    game
  }
  def thinkAi(game: GameState): GameState = {
    val calc = new MinMax(player, board)
    val choice = calc.getNextChoice (game)
    moveGameState (game, choice.x, choice.y)
  }
  def playerConvert(from: String, to: String): Unit = {
    val $spaceRadios = jquery.jQuery(".tic-tac-toe input[type='radio']:not(:checked)")

    $spaceRadios.removeClass(from)
    $spaceRadios.addClass(to)
  }
}
