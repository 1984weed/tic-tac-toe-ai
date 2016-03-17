package ai

import scala.collection.mutable.ListBuffer
import scala.util.Random
import scala.util.control.Breaks



/**
  * Minmax方で次の一手を計算する
  *
  * @param player
  */
class MinMax(player: Player, board: Board){
  case class ScoreMove(score: Int, move: Position)
  private var choice: Position  = null
  val baseScore = board.playersMemo.length + 1
  val border = baseScore + 1
  def getNextChoice(game: GameState): Position = {
    alphabeta(game, -border, border, 0).move
  }
  private def alphabeta(game: GameState, floor: Int, upper: Int, depth: Int): ScoreMove = {
    if (game.isOver) return ScoreMove(score(game, depth), game.board.getLastMove)

    var alpha = floor
    var beta = upper

    val scoreList = (for(move <- util.Random.shuffle(game.getAvailableMoves))yield{
      ScoreMove(alphabeta(game.move(move.x, move.y), alpha, beta, depth + 1).score, move)
    }).filter{a =>
      if (game.activePlayer == player) {
        alpha = Math.max(a.score, alpha)
      } else {
        beta = Math.min(a.score, beta)
      }
      alpha < beta
    }
    if (game.activePlayer == player) {
      scoreList.maxBy(_.score)
    }else{
      scoreList.minBy(_.score)
    }
  }
  def score(game: GameState, depth: Int): Int = {
    if(game.won(player)){
      baseScore - depth
    }else if(game.lose(player)){
      depth - baseScore
    } else{
      0
    }
  }
}
class Player(val num: Int){
  var doneMoves: List[List[Int]] = List[List[Int]]()
}
case class Position(x: Int, y: Int){
  var player: Option[Player] = None
  def copy: Position = {
    val p = new Position(x, y)
    p.player =player
    p
  }
}
