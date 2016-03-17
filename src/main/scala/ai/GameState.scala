package ai

/**
  * Game State class
  * @param activePlayer
  * @param opponent
  */
class GameState(val activePlayer: Player,
           val opponent: Player,
           val board: Board){
  val WIN_NUMBER = 3

  def getAvailableMoves: List[Position] = {
    board.getAvailableSpaces
  }

  def isOver: Boolean = {
    isWinPlayer || getAvailableMoves.length == 0
  }

  def won(targetPlayer: Player) = isWinPlayer && opponent == targetPlayer

  def lose(targetPlayer: Player) = isWinPlayer && opponent != targetPlayer
  /**
    * todo refactor
    *
    * @return
    */
  def isWinPlayer: Boolean = {
    isLineMoves(board.getPlayerPositions(activePlayer)) || isLineMoves(board.getPlayerPositions(opponent))
  }
  def isLineMoves(moves: List[Position]): Boolean = {
    // 判定方法は議論の余地があるにせよとりまOK
    return moves.filter(a => {
      a.x == a.y
    }).length == WIN_NUMBER ||
      moves.filter(a => {
        a.y == 0
      }).length == WIN_NUMBER ||
      moves.filter(a => {
        a.y == 1
      }).length == WIN_NUMBER ||
      moves.filter(a => {
        a.y == 2
      }).length == WIN_NUMBER ||
      moves.filter(a => {
        a.x == 0
      }).length == WIN_NUMBER ||
      moves.filter(a => {
        a.x == 1
      }).length == WIN_NUMBER ||
      moves.filter(a => {
        a.x == 2
      }).length == WIN_NUMBER ||
      (moves.indexOf(Position(0, 2)) > -1 && moves.indexOf(Position(1, 1)) > -1 && moves.indexOf(Position(2, 0)) > -1 )
  }

  def move(x: Int, y: Int): GameState = {
    val newBoard = board.copy()
    newBoard.set(activePlayer, x, y)
    //ここで入れ替え
    new GameState(opponent, activePlayer, newBoard)
  }
}

