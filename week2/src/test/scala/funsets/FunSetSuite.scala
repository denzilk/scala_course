package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }
  
  trait union_sets {
	val i1 = singletonSet(1)                        //> i1  : Int => Boolean = <function1>
	val i2 = singletonSet(2)                        //> i2  : Int => Boolean = <function1>
	val o1 = singletonSet(-1)                       //> o1  : Int => Boolean = <function1>
	val o2 = singletonSet(-2)                       //> o2  : Int => Boolean = <function1>
	    
	val ii = union(i1,i2)                           //> ii  : Int => Boolean = <function1>
	val io = union(i1,o1)                           //> io  : Int => Boolean = <function1>
	val oo = union(o1,o2)                           //> oo  : Int => Boolean = <function1>
	        
	def positive(test: Int) = (test >= 0)           //> positive: (test: Int)Boolean
	def negative(test: Int) = (test < 0)            //> negative: (test: Int)Boolean
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(contains(s2, 2), "Singleton")
      assert(contains(s3, 3), "Singleton")
      assert(!contains(s1, 4), "not includes")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }
  
  test("intersection of singletons contains no elements") {
    new TestSets {
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "Intersect 1")
      assert(!contains(s, 2), "Intersect 2")
    }
  }

  test("intersection of diffs contain 1 element") {
    new TestSets {
      val s = diff(s1, s2)
      assert(contains(s, 1), "Diff 1")
      assert(!contains(s, 2), "Diff 2")
    }
  }
  
  test("filter tests") {
    new TestSets {
      val s12 = union(s1, s2)
      val s23 = union(s2, s3)
      def contains_1(test: Int) = (test == 1)
      val f12 = filter(s12, contains_1)
      val f23 = filter(s23, contains_1)            
      assert(contains(f12,1), "filter 12")
      assert(!contains(f23,1), "filter 23")
    }
  } 
  test("test for all") {
    new union_sets {      
	  assert(forall(ii, positive), "test 1")                    //> res0: Boolean = true
	  assert(!forall(ii, negative), "test 2")                   //> res1: Boolean = true
	
	  assert(!forall(io, positive), "test 3")                   //> res2: Boolean = true
	  assert(!forall(io, negative), "test 4")                   //> res3: Boolean = true
	                                                  
	  assert(!forall(oo, positive), "test 5")                   //> res4: Boolean = true
	  assert(forall(oo, negative), "test 6")                    //> res5: Boolean = true
    }
  }
  test("exists") {
    new union_sets{
	  assert(exists(ii, positive), "exist test 1")                    
	  assert(!exists(ii, negative), "exist test 2")                   
	
	  assert(exists(io, positive), "exist test 3")                   
	  assert(exists(io, negative), "exist test 4")                   
	                                                  
	  assert(!exists(oo, positive), "exist test 5")                   
	  assert(exists(oo, negative), "exist test 6")                         
    }
  }
  
  test("test maps") {
    val i1 = singletonSet(2)                        //> i1  : Int => Boolean = <function1>
	val i2 = singletonSet(3)                        //> i2  : Int => Boolean = <function1>
    val ii = union(i1,i2)                           //> ii  : Int => Boolean = <function1>
        
    def square(x: Int) = x * x                      //> square: (x: Int)Int

    def squared = map(ii, square)                   
    assert(!squared(2),"square 2")
    assert(!squared(3),"square 3")
    assert(squared(4),"square 4")
    assert(squared(9),"square 9")
    assert(!squared(1),"square 1")
    assert(!squared(5),"square 5")
  }
  
}
