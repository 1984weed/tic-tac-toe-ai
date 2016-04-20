package ai

import utest._

/**
  * Created by kunihiro on 2016/04/13.
  */
object BoardTest extends TestSuite {
  val tests = this {
    'set_test {
      val player1 = new Player(0)
      val player2 = new Player(1)
      val board = new Board(3, 3)
      board.set(player1, 0, 0)

      assertMatch(board.getPlayerPositions(player1)) {
        case List(Position(0, 0)) =>
      }
    }
    'getPositionInstance_test{
      val player1 = new Player(0)
      val player2 = new Player(1)
      val board = new Board(3, 3)

      assert(board.getPositionInstance(1,2) == Some(Position(1,2)))
    }
    'getPlayerPositions_test {
      val player1 = new Player(0)
      val player2 = new Player(1)
      val board = new Board(3, 3)

      board.set(player1, 0, 1)
      board.set(player2, 1, 2)

      assertMatch(board.getPlayerPositions(player1)) {
        case List(Position(0, 1)) =>
      }
      assertMatch(board.getPlayerPositions(player2)) {
        case List(Position(1, 2)) =>
      }
    }
    'getAvailableSpaces_test{
      val player1 = new Player(0)
      val player2 = new Player(1)
      val board = new Board(3, 3)

      board.set(player1, 0, 1)
      board.set(player2, 1, 2)

      assertMatch(board.getAvailableSpaces) {
        case List(Position(0, 0),
                  Position(0, 2),
                  Position(1, 0),
                  Position(1, 1),
                  Position(2, 0),
                  Position(2, 1),
                  Position(2, 2)) =>
      }
    }
    'copy_test{
      val player1 = new Player(0)
      val player2 = new Player(1)
      val board = new Board(3, 3)
      board.set(player1, 1, 2)

      val newBoard = board.copy

      board.set(player1, 0, 2)
      assertMatch(board.getPlayerPositions(player1)) {
        case List(Position(0, 2), Position(1,2)) =>
      }
      assertMatch(newBoard.getPlayerPositions(player1)) {
        case List(Position(1,2)) =>
      }
    }
  }
}
