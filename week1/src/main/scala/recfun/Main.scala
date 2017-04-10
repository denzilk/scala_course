package recfun
import common._

object Main extends App{
  def main() {
    println("Exercise 1: Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
 		print(pascal(col, row) + " ")
      println()
    }    
    println()
    
    println("Exercise 2: Parentheses Balancing")
    val strings = List(
      "(if (zero? x) max (/ 1 x))",
      "I told him (that it's not (yet) done). (But he wasn't listening)",
      ":-)",
      "())(",
      "no parens",
      "(dsfdsfsf",
      ")sdfss",
      "((()))"
	)	
	for (i <- 0 to (strings.size-1)) {	  
	  val result = if (balance(strings(i).toList)) 
	    " >>> IS balanced" 
	  else 
	    " >>> is NOT balanced"
	  println(strings(i) + result)
	}
    println()
    
    println("Exercise 3: Counting Change")
    val amounts = List(0,1,2,3,4,5,6,7) 
    val coins = List(
      List(2,3,5)
    )
    for (i <- 0 to (amounts.size-1)) {
      for (j <- 0 to (coins.size-1)) {
        println("Calculating "+ amounts(i) +" with coin sizes of " + coins(j).mkString(","))
        println(countChange(amounts(i), coins(j)))
      }
    }    
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {    
    def legal(c: Int, r: Int): Boolean = {
      ((c >= 0) && (r >= 0) && (c <= r))
    }
    if (!legal(c, r)) 0
    else if (c == 0) 1
	else pascal(c-1, r-1) + pascal(c, r-1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
	def check_head(chars: List[Char], acc: Int): Int = {	  	  
	  if ((chars.isEmpty) || (acc < 0)) acc
	  else {
		val head = chars.head
		val opens = if (head == '(') 1 else if (head == ')') -1 else 0
		check_head(chars.tail, acc + opens)
	  }
	}
	(0 == check_head(chars, 0))
  }
  
  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def loop(coin_list: List[Int], i: Int, acc: Int): Int = {
       if (i < 0) acc
       else {
	     val remaining = (money - (i * coin_list.head))
	     val addition = if (0 == remaining) 1 
	     else countChange(remaining, coin_list.tail)	       
	     loop(coin_list, i-1, acc + addition)
       }
    }
    
	if ((money <= 0) || (coins.isEmpty)) 0
	else {
	  val coin_list = coins.toSet.toList.sortWith(_ > _)
	  val head = coin_list.head
	  if (head <= 0) 0 
	  else loop(coin_list, money/head, 0)	        
    }
  }
}
