package u05lab.ex1

import u05lab.ex1.List.*
import org.junit.Test
import org.junit.Before
import org.junit.Assert.*

class ListTest {

  val elements = 10 :: 20 :: 30 :: 40 :: Nil()
  val el2 = List(5, 7, 9, 12)
  val el3 = List(1, 2, 3, 4)

  @Test
  def testZipRight(): Unit =
    assertEquals(List((10, 0), (20, 1), (30, 2), (40, 3)), elements.zipRight)
    assertEquals((5,0) :: (7, 1) :: (9, 2) :: (12, 3) :: Nil(), el2.zipRight)

  @Test
  def testPartition(): Unit =
    assertEquals((List(12), List(5, 7, 9)), el2.partition(_ % 2 == 0))
    assertNotEquals((List(10, 20), List(30, 40)), elements.partition(_ % 10 == 0))
    assertEquals((List(10, 20, 30, 40), Nil()), elements.partition(_ % 2 == 0))

  /*
  @Test
  def testSpan(): Unit =
    assertEquals((List(1), List(2, 3, 4)), el3.span(_ % 2 != 0))
  */

  @Test
  def testReduce(): Unit =
    assertEquals(100, elements.reduce(_ + _))
    assertEquals(24, el3.reduce(_ * _))
    assertEquals(-23, el2.reduce(_ - _))

  @Test
  def testTakeRight(): Unit =
    assertEquals(List(30, 40), elements.takeRight(2))
    assertEquals(Nil(), el3.takeRight(0))
    assertEquals(List(5, 7, 9, 12), el2.takeRight(4))

}
