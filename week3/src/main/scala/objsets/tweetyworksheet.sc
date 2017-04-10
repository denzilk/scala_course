package objsets

object tweetyworksheet {
  GoogleVsApple.google                            //> res0: List[String] = List(android, Android, galaxy, Galaxy, nexus, Nexus)
  val x = new Tweet("a","werwerwe",23)            //> x  : objsets.Tweet = User: a
                                                  //| Text: werwerwe [23]
 val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
                                                  //> google  : List[String] = List(android, Android, galaxy, Galaxy, nexus, Nexus
                                                  //| )
  

  val set1 = new Empty                            //> set1  : objsets.Empty = objsets.Empty@2393385d
  val set2 = new Empty                            //> set2  : objsets.Empty = objsets.Empty@165973ea
	val set3 = set1.incl(new Tweet("a", "1 galaxy", 21))
                                                  //> set3  : objsets.TweetSet = objsets.NonEmpty@4ac9131c
	val set4 = set3.incl(new Tweet("a", "2 nexus", 22))
                                                  //> set4  : objsets.TweetSet = objsets.NonEmpty@5705b99f
	val set5 = set4.incl(new Tweet("a", "3hekko nexus", 20))
                                                  //> set5  : objsets.TweetSet = objsets.NonEmpty@38dda25b
	val set6 = set5.incl(new Tweet("a", "4 a bodyb", 24))
                                                  //> set6  : objsets.TweetSet = objsets.NonEmpty@5ece2187
	val set7 = set2.incl(new Tweet("a", "5 a bodyc", 25))
                                                  //> set7  : objsets.TweetSet = objsets.NonEmpty@2efb56b1
	val set8 = set7.incl(new Tweet("a", "6 a android", 26))
                                                  //> set8  : objsets.TweetSet = objsets.NonEmpty@76f8968f
	val set9 = set8.incl(new Tweet("a", "7 a bodye", 27))
                                                  //> set9  : objsets.TweetSet = objsets.NonEmpty@3a64c34e
	val set10 = set9.incl(new Tweet("a", "8 a bodyf", 28))
                                                  //> set10  : objsets.TweetSet = objsets.NonEmpty@2d5253d5
  val set11 = set6.incl(new Tweet("a", "9 a bodyg", 29))
                                                  //> set11  : objsets.TweetSet = objsets.NonEmpty@77fddc31
  val set12 = set10.incl(new Tweet("a", "10 a nexus", 20))
                                                  //> set12  : objsets.TweetSet = objsets.NonEmpty@3b835282
  
  val settest = new NonEmpty(new Tweet("a", "11 headded", 10), set11, set12)
                                                  //> settest  : objsets.NonEmpty = objsets.NonEmpty@2a9df354
  
  val google_tweets =  settest.filter((tw: Tweet) => {
      google.exists((st:String) => tw.text.contains(st))
    })                                            //> filteracc 11 headded
                                                  //| filteracc 1 galaxy
                                                  //| filteracc empty
                                                  //| filteracc 2 nexus
                                                  //| filteracc empty
                                                  //| filteracc 3hekko nexus
                                                  //| filteracc empty
                                                  //| filteracc 4 a bodyb
                                                  //| filteracc empty
                                                  //| filteracc 9 a bodyg
                                                  //| filteracc empty
                                                  //| filteracc empty
                                                  //| filteracc 5 a bodyc
                                                  //| filteracc 10 a nexus
                                                  //| filteracc empty
                                                  //| filteracc empty
                                                  //| filteracc 6 a android
                                                  //| filteracc empty
                                                  //| filteracc 7 a bodye
                                                  //| filteracc empty
                                                  //| filteracc 8 a bodyf
                                                  //| filteracc empty
                                                  //| filteracc empty
                                                  //| google_tweets  : objsets.TweetSet = objsets.NonEmpty@509f5011
                                       
  

   google_tweets.descendingByRetweet foreach println
                                                  //> union
                                                  //| filteracc 10 a nexus
                                                  //| filteracc empty
                                                  //| filteracc empty
                                                  //| union
                                                  //| filteracc 10 a nexus
                                                  //| filteracc empty
                                                  //| filteracc empty
                                                  //| User: a
                                                  //| Text: 6 a android [26]
                                                  //| User: a
                                                  //| Text: 2 nexus [22]
                                                  //| User: a
                                                  //| Text: 1 galaxy [21]
                                                  //| User: a
                                                  //| Text: 3hekko nexus [20]
                                                  //| User: a
                                                  //| Text: 10 a nexus [20]
   
  println("boo")                                  //> boo
  
  
   
                                                  
}