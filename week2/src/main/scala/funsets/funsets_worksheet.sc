package funsets

import FunSets._

object funsets_worksheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val i1 = singletonSet(2)                        //> i1  : Int => Boolean = <function1>
  val i2 = singletonSet(3)                        //> i2  : Int => Boolean = <function1>
  val o1 = singletonSet(-1)                       //> o1  : Int => Boolean = <function1>
  val o2 = singletonSet(-2)                       //> o2  : Int => Boolean = <function1>
    
  val ii = union(i1,i2)                           //> ii  : Int => Boolean = <function1>
  val io = union(i1,o1)                           //> io  : Int => Boolean = <function1>
  val oo = union(o1,o2)                           //> oo  : Int => Boolean = <function1>
        
  def square(x: Int) = x * x                      //> square: (x: Int)Int

  def squared = map(ii, square)                   //> squared: => Int => Boolean
  squared(2)                                      //> res0: Boolean = false
  squared(3)                                      //> res1: Boolean = false
  squared(4)                                      //> res2: Boolean = true
  squared(9)                                      //> res3: Boolean = true
  squared(1)                                      //> res4: Boolean = false
  squared(5)                                      //> res5: Boolean = false
}