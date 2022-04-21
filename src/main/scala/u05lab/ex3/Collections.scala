package u05lab.ex3

import java.util.concurrent.TimeUnit
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.concurrent.duration.FiniteDuration

object PerformanceUtils:
  case class MeasurementResults[T](result: T, duration: FiniteDuration) extends Ordered[MeasurementResults[_]]:
    override def compare(that: MeasurementResults[_]): Int = duration.toNanos.compareTo(that.duration.toNanos)

  def measure[T](msg: String)(expr: => T): MeasurementResults[T] =
    val startTime = System.nanoTime()
    val res = expr
    val duration = FiniteDuration(System.nanoTime() - startTime, TimeUnit.NANOSECONDS)
    if (msg.nonEmpty) println(msg + " -- " + duration.toNanos + " nanos; " + duration.toMillis + "ms")
    MeasurementResults(res, duration)

  def measure[T](expr: => T): MeasurementResults[T] = measure("")(expr)

@main def checkPerformance: Unit =

  /* Linear sequences: List, ListBuffer */
  import scala.List
  val immutList = List(10, 20, 30)
  println(immutList)
  println(immutList.reverse)
  println(immutList.takeRight(1))
  println(immutList.indexOf(20))
  println(immutList(2))
  println(immutList.filter(i => i % 3 != 0))
  println(5 :: immutList)
  println(immutList :+ 12)
  println((immutList :+ 12).sorted)

  val mutList = ListBuffer[String]()
  println(mutList)
  mutList += "ciao"
  mutList += "sono"
  println(mutList += "giorgio")
  println(mutList)
  println(mutList.last)
  mutList -= "sono"
  //mutList.foreach(el => el + "bello")
  println(mutList.map(el => el + " bello"))
  println(mutList)
  /* Indexed sequences: Vector, Array, ArrayBuffer */
  import scala.Vector
  val vect =  Vector(2, 3, 5)
  println(vect)
  println(vect.reduce(_ + _))
  println(vect.last)
  println(vect.toList)
  println(vect.reverse)
  println(vect(1))

  import scala.Array
  val arry = Array("hi","there")
  println(arry)
  println(arry.length)
  println(arry.toList)
  println(arry :+ "my Name is Markiplier")
  println(arry.toList)
  println((arry :+ "my name is Markiplier").toList)

  val buffArr = ArrayBuffer[Int]()
  println(buffArr.getClass)
  println(buffArr)
  buffArr += 3
  buffArr += 5
  buffArr += 1
  println(buffArr)
  buffArr.addAll(immutList)
  println(buffArr)
  buffArr -= 5
  buffArr.remove(1)
  println(buffArr)
  /* Sets */
  import scala.collection.immutable.Set
  val immSet = Set(13, 23, 45)
  println(immSet)
  println(immSet + 12)
  println(immSet + 13 + 23 + 40)
  println(immSet(13))
  println(immSet & Set(13, 50, 23, 12))

  val mutSet = scala.collection.mutable.Set[String]()
  mutSet += "ohi"
  mutSet += "Boi"
  println(mutSet)
  mutSet += "you Bitch"
  println(mutSet)
  println(mutSet.head)
  println(mutSet.tail)
  mutSet.remove("you bitch")
  mutSet.addAll(arry)
  println(mutSet)
  /* Maps */
  val imMap = Map(1 -> "a", 2 -> "b")
  println(imMap)
  println(imMap.toSet)
  println(imMap.keys)
  println(imMap.values)
  println(imMap(1))

  val mutMap = scala.collection.mutable.Map[String,Int]()
  println(mutMap)
  mutMap += ("a" -> 3)
  mutMap += ("b" -> 5)
  mutMap += ("c" -> 7)
  println(mutMap)
  println(mutMap.toSet)
  println(mutMap.toMap)
  mutMap.addOne("e", 4)
  mutMap.addOne("b", 9)
  println(mutMap)
  mutMap.remove("b")

  /* Comparison */
  import PerformanceUtils.*
  val lst = (1 to 10000000).toList
  val vec = (1 to 10000000).toVector
  val arr = (1 to 10000000).toArray
  val sett = (1 to 10000000).toSet
  assert(measure("lst last")(lst.last) > measure("vec last")(vec.last))
  assert(measure("arr last")(arr.last) > measure("vec last")(vec.last))
  assert(measure("lst last")(lst.last) > measure("arr last")(arr.last))
  assert(measure("sett last")(sett.last) > measure("arr last")(arr.last))
