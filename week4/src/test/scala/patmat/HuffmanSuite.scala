package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }
  
  test("times") {
    assert(times(string2Chars("aaa")) === List(('a',3)))
    assert(times(string2Chars("aabab")) === List(('a',3),('b',2)))    
  }  

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }
  
  test("combine of leaf list to be sorted") {
    val leaflist = List(Leaf('e', 2), Leaf('t', 3), Leaf('x', 4), Leaf('q', 6))
    assert(combine(leaflist) === List(Leaf('x',4), Fork(Leaf('e',2),Leaf('t',3),List('e', 't'),5), Leaf('q',6)))
  } 
  
  test("create code tree") {
    assert(createCodeTree(string2Chars("aaabbc")) === Fork(Leaf('a',3), Fork(Leaf('c',1), Leaf('b',2), List('c','b'),3), List('a','c','b'), 6))
  } 

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
  
  test("decode and encode long text should be identity") {
    new TestTrees {
      assert(decode(t2, encode(t2)("aaababdaaad".toList)) === "aaababdaaad".toList)
    }
  }
  
  test("createCodeTree efficiency") {
    testCodeTreeEfficiency("someText", 22)
    testCodeTreeEfficiency("Huffman est cool", 58)
    testCodeTreeEfficiency("Huffman coding is a compression algorithm that can be used to compress lists of characters.", 373)
  }

  private def testCodeTreeEfficiency(text: String, length: Int) {
    val someTextCodeTree = createCodeTree(text.toList)
    assert(encode(someTextCodeTree)(text.toList).length === length)
  }
  
}
