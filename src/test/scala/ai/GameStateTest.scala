package ai

import utest._

object GameStateTest extends TestSuite {
  val player = new Player(0)
  val opponent = new Player(1)
  var game = new GameState(player, opponent, new Board(3, 3))
  val tests = this{
    'best_chioce_test{
      var processedGame =
        game.move(2, 0)
          .move(0, 0)
          .move(2, 1)
      val calc = new MinMax(player, processedGame.board)
      val hoge = calc.getNextChoice(processedGame)
      assertMatch(hoge) {
        case Position(2,2) =>
      }
    }
    'best_chioce_test{
      var processedGame =
        game.move(1, 1)
          .move(2, 2)
          .move(0, 1)
          .move(2, 1)
          .move(0, 2)
      val calc = new MinMax(player, processedGame.board)
      val hoge = calc.getNextChoice(processedGame)
      assertMatch(hoge) {
        case Position(2, 0) =>
      }
    }
    'move_test{
      var proccesedGame =
        game.move(2, 0)
          .move(0, 0)
          .move(2, 1)
      proccesedGame.getAvailableMoves.foreach(move => {
        val possibleGame = proccesedGame.move(move.x, move.y)
      })
      val players = proccesedGame.board.playersMemo.map(_.player)
      assert(proccesedGame.board.getPositionInstance(0, 0).flatMap( a => Some(a.player.isDefined)).getOrElse(false))
      assert(proccesedGame.board.getPositionInstance(2, 0).flatMap( a => Some(a.player.isDefined)).getOrElse(false))
      assert(proccesedGame.board.getPositionInstance(2, 1).flatMap( a => Some(a.player.isDefined)).getOrElse(false))
    }
    'move_test{
      var gameInstance = game
      gameInstance.isLineMoves(List(Position(2, 0), Position(1, 1), Position(0, 0)))
      assert(gameInstance.isLineMoves(List(Position(2, 0), Position(1, 1), Position(0, 2))) == true)
      assert(gameInstance.isLineMoves(List(Position(0, 0), Position(1, 1), Position(2, 2))) == true)
      assert(gameInstance.isLineMoves(List(Position(2, 0), Position(1, 1), Position(2, 1))) == false)
    }
  }
}

