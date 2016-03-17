package ai

class Board(height: Int, width: Int){
  var playersMemo: List[Position] = (for(i <- 0 to height - 1; j <- 0 to width - 1) yield Position(i, j)).toList
  private var lastMove:  Option[Position] = None;

  def set(player: Player, x: Int, y: Int): Unit ={
    lastMove = getPositionInstance(x, y)
    lastMove.foreach{a =>
      a.player = Some(player)
    }
  }
  def getPositionInstance(x: Int, y: Int): Option[Position] = {
    playersMemo.find{a =>
      a.x == x && a.y == y
    }
  }
  def getLastMove(): Position = {
    lastMove.getOrElse(Position(Int.MaxValue, Int.MinValue))
  }
  def getPlayerPositions(player: Player): List[Position] = {
    playersMemo.filter(a => a.player.flatMap(b => Some(b == player)).getOrElse(false))
  }
  def getAvailableSpaces: List[Position] = {
    playersMemo.filter(a => a.player.isEmpty)
  }
  def copy(): Board = {
    val b = new Board(height, width)
    b.playersMemo = playersMemo.map(a => a.copy)
    b
  }
}
