package forcomp

object scwork {
  type Word = String
  type Sentence = List[Word]
  type Occurrences = List[(Char, Int)]
  val dictionary: List[Word] = loadDictionary     //> dictionary  : List[forcomp.scwork.Word] = List(Aarhus, Aaron, Ababa, aback, 
                                                  //| abaft, abandon, abandoned, abandoning, abandonment, abandons, abase, abased,
                                                  //|  abasement, abasements, abases, abash, abashed, abashes, abashing, abasing, 
                                                  //| abate, abated, abatement, abatements, abater, abates, abating, Abba, abbe, a
                                                  //| bbey, abbeys, abbot, abbots, Abbott, abbreviate, abbreviated, abbreviates, a
                                                  //| bbreviating, abbreviation, abbreviations, Abby, abdomen, abdomens, abdominal
                                                  //| , abduct, abducted, abduction, abductions, abductor, abductors, abducts, Abe
                                                  //| , abed, Abel, Abelian, Abelson, Aberdeen, Abernathy, aberrant, aberration, a
                                                  //| berrations, abet, abets, abetted, abetter, abetting, abeyance, abhor, abhorr
                                                  //| ed, abhorrent, abhorrer, abhorring, abhors, abide, abided, abides, abiding, 
                                                  //| Abidjan, Abigail, Abilene, abilities, ability, abject, abjection, abjections
                                                  //| , abjectly, abjectness, abjure, abjured, abjures, abjuring, ablate, ablated,
                                                  //|  ablates, ablating, abla
                                                  //| Output exceeds cutoff limit.
  def wordOccurrences(w: Word): Occurrences = {
    w.toLowerCase.toList.groupBy((letter: Char) => letter).map{
      case(letter:Char, charlist: List[Char]) => (letter, charlist.length)
    }.toList.sorted
  }                                               //> wordOccurrences: (w: forcomp.scwork.Word)forcomp.scwork.Occurrences
  def sentenceOccurrences(s: Sentence): Occurrences = {
    wordOccurrences(s.mkString)
  }                                               //> sentenceOccurrences: (s: forcomp.scwork.Sentence)forcomp.scwork.Occurrences
  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = {
    dictionary.groupBy((dictionary_word: Word) => wordOccurrences(dictionary_word))
                                                  //> dictionaryByOccurrences: => Map[forcomp.scwork.Occurrences,List[forcomp.scwo
                                                  //| rk.Word]]
  }
  def wordAnagrams(word: Word): List[Word] = {
    dictionaryByOccurrences(wordOccurrences(word))
  }                                               //> wordAnagrams: (word: forcomp.scwork.Word)List[forcomp.scwork.Word]
  def combinations(occurrences: Occurrences): List[Occurrences] = occurrences match {
    case List() => List(List())
    case (letter: Char, n: Int) :: tail => {
      val tail_combos = combinations(tail)
      for {
        combox <- tail_combos
        i <- 0 until (n + 1)
      } yield (if (i > 0) (letter, i) :: combox else combox )
    }
  }                                               //> combinations: (occurrences: forcomp.scwork.Occurrences)List[forcomp.scwork.
                                                  //| Occurrences]
def subtract(x: Occurrences, y: Occurrences): Occurrences = {
    val ymap = y.toMap
    val nocc = for{
      (k,v) <- x
      if ((v - ymap.getOrElse(k,0)) > 0)
    } yield (k, v - ymap.getOrElse(k,0))
    if (nocc.isEmpty) List() else nocc
  }                                               //> subtract: (x: forcomp.scwork.Occurrences, y: forcomp.scwork.Occurrences)for
                                                  //| comp.scwork.Occurrences
  subtract(List(),List())                         //> res0: forcomp.scwork.Occurrences = List()
  subtract( List(('a',2)), List(('b',2)) )        //> res1: forcomp.scwork.Occurrences = List((a,2))
/*
  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
    val combos = combinations(sentenceOccurrences(sentence))
    for {
      lo <- List(sentence)
      cmb <- combos
      if (dictionaryByOccurrences.contains(cmb))
    }yield dictionaryByOccurrences.getOrElse(cmb,List())
  }
  */
  
  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
    def possible_words(occ: Occurrences): List[Word] = dictionaryByOccurrences.getOrElse(occ,List())
   
    def find_words(occs: Occurrences, n: Int): List[Sentence] = {
       val wordlist = for{
          occ <- combinations(occs)
          word <- possible_words(occ)
          if !word.isEmpty
       } yield word
       
       if (wordlist.isEmpty)
          List(List())
       else {
	       for{
	         word <- wordlist
	         sentence <- find_words(subtract(occs, wordOccurrences(word)), n - word.length)
	         if ((sentence).flatten.length == (n - word.length))
	       }yield word :: sentence
       }
    }
  
    val occs = sentenceOccurrences(sentence)
    find_words(occs, sentence.flatten.length)
  }                                               //> sentenceAnagrams: (sentence: forcomp.scwork.Sentence)List[forcomp.scwork.Se
                                                  //| ntence]
  val testsentence = List("Yes", "man")           //> testsentence  : List[String] = List(Yes, man)
  sentenceAnagrams(testsentence)                  //> res2: List[forcomp.scwork.Sentence] = List(List(en, as, my), List(en, my, a
                                                  //| s), List(man, yes), List(men, say), List(as, en, my), List(as, my, en), Lis
                                                  //| t(sane, my), List(Sean, my), List(my, en, as), List(my, as, en), List(my, s
                                                  //| ane), List(my, Sean), List(say, men), List(yes, man))
}