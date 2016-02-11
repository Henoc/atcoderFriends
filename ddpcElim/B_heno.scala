package discoveryContest

import java.io.PrintWriter
import java.util
import scala.collection.mutable
import scala.util.control.Breaks.{break, breakable}
import scala.collection.mutable.{Map => MuMap, Set => MuSet}
import scala.collection.JavaConversions._

/**
 * Created by heno on 2016/01/30.
 */
object B {
  val reader = new InputReader
  val writer = new PrintWriter(System.out)

  def main(args: Array[String]){
    val n = reader.readInt()
    val rooms = Array.tabulate(n)(x => reader.readInt())

    // key:部屋の価値, value:対応する部屋番号の集合
    val indexMap = {
      val treeMap = new util.TreeMap[Int,util.TreeSet[Int]]()
      for((v, i) <- rooms.zipWithIndex) {
        if(treeMap.containsKey(v)){
          treeMap.get(v) += i
        }else {
          val ts = new util.TreeSet[Int]()
          ts.add(i)
          treeMap.put(v, ts)
        }
      }
      treeMap
    }

    var ret = 0
    //val startIndex = indexMap.firstEntry().getValue.min
    var preIndex = indexMap.firstEntry().getValue.first()
    for((v, indexSet) <- indexMap){
      if(preIndex > indexSet.first()) {
        ret += 1
      }
      preIndex = reachedIndex(preIndex, indexSet)
    }
    if(0 < indexMap.lastEntry().getValue.max) ret += 1
    Console println ret

  }

  def reachedIndex(preIndex : Int, indexSet : util.TreeSet[Int]): Int = {
    val leftSideSet = indexSet.headSet(preIndex)
    if(leftSideSet.isEmpty) {
      indexSet.last()
    }else {
      leftSideSet.last()
    }
  }

  class InputReader {
  	import java.io.IOException
  	import java.util.InputMismatchException
  	import java.lang.StringBuilder

  	private[this] val stream = System.in
  	private[this] val buf:Array[Byte] = new Array(102400)
  	private[this] var curChar:Int = 0
  	private[this] var numChars:Int = 0
  	private[this] var c:Int = 0
  	private[this] var sgn:Int = 1
  	private[this] var num:Long = 0

  	def read():Int = {
  			if (numChars == -1)
  				throw new InputMismatchException
  			if (curChar >= numChars) {
  				curChar = 0
  				try {
  					numChars = stream.read(buf)
  				}catch{
  					case e:IOException => throw new InputMismatchException
  				}
  				if (numChars <= 0)
  					return -1
  			}
  			c = buf(curChar)
  			curChar += 1
  			c
  	}
  	def readInt():Int = {
  			spaces
  			sign
  			number
  			num.toInt * sgn
  	}
  	def readLong():Long = {
  			spaces
  			sign
  			number
  			num * sgn
  	}
  	def readString():String = {
  		spaces
  		val res = new StringBuilder
  		do {
  			res.appendCodePoint(c)
  			read
  		} while (!isSpaceChar(c))
  		res.toString
  	}
  	def isSpaceChar(c:Int):Boolean = c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1
  	def next():String = readString
  	def spaces() = do read while(isSpaceChar(c))
  	def sign() = {
  		sgn = 1
  		if(c=='-'){
  			sgn = -1
  			read
  		}
  	}
  	def number() = {
  		num = 0L
  		do {
  			if (c < '0' || c > '9') throw new InputMismatchException
  			num *= 10
  			num += c - '0'
  			read
  		} while (!isSpaceChar(c))
  	}
  }
}
