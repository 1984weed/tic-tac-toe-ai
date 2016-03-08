object MinMax{
  def main(args: Array[String]): Unit ={

  }

  def score(game: Game): Int = {
    if(game.win){
      return 10
    }else if(game.loose){
      return -10
    }
    0
  }
  def minmax(game: Game): Int = {
    if(score(game) == 0 ) return game.over
    var scores: Array[Int] = new Array[Int](100)
    var moves: Array[Int] = new Array[Int](100)

    game.getAvailableMoves
    1
  }
}
class Game{
  val win: Boolean = true
  val loose: Boolean = true
  val over: Int = -10

  def getAvailableMoves: List[Int] = {
    List(1,3,4)
  }
}
