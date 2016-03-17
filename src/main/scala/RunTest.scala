import ai.{Board, MinMax, GameState, Player}

object RunTest {
  val player = new Player(0)
  val opponent = new Player(1)
  var game = new GameState(player, opponent, new Board(3, 3))
  def main(args: Array[String]): Unit = {
    var processedGame =
      game.move(2, 0)
        .move(1, 0)
        .move(1, 1)
        .move(0, 2)
        .move(2, 2)
    val calc = new MinMax(player, processedGame.board)
  }
}