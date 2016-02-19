package arc4

/**
  * Created by heno on 2016/02/19.
  */
object C {
  def main(args: Array[String]) {
    val inputs = readLine.toString.split('/').map(BigInt.apply)
    val x = inputs(0)
    val y = inputs(1)
    List.tabulate(2)(i => 2 * x / y + i).map(n => (n, n * (n + 1) / 2 - x * n / y))
      .filter{case (n,m) => x * n % y == 0 && 1 <= m && m <= n} match {
      case lst if lst.isEmpty => println("Impossible")
      case lst => lst.foreach{case (n, m) => println(n + " " + m)}
    }
  }
}
