package u05lab.ex2

import scala.collection.immutable.{Map, List, Set}

enum Question:
  case RELEVANCE
  case SIGNIFICANCE
  case CONFIDENCE
  case FINAL
/*
trait ConferenceReviewing:
  def loadReview(article: Int,scores: Map[Question, Int]): Unit
  def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit
  def orderedScores(article: Int, question: Question): List[Int]
  def averageFinalScore(article: Int): Double
  def acceptedArticles(): Set[Int]
  def sortedAcceptedArticle(): List[(Int, Double)]
*/

class ConferenceReviewing:
  /*
  def apply: ConferenceReviewing
    ConferenceReviewingImpl()
  case class ConferenceReviewingImpl() extends ConferenceReviewing:*/
  private var reviews: List[(Int, Map[Question, Int])] = List.empty

  def loadReview(article: Int, scores: Map[Question, Int]): Unit =
    if(scores.keys.size < Question.values.length) then
      throw IllegalArgumentException()
    else
      reviews = reviews :+ (article, scores)

  def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit =
    var values: Map[Question, Int] = Map(Question.RELEVANCE -> relevance, Question.SIGNIFICANCE -> significance,
      Question.CONFIDENCE -> confidence, Question.FINAL -> fin)
    reviews = reviews :+ (article, values)

  def orderedScores(article: Int, question: Question): List[Int] =
    reviews.filter(art => art._1 == article).map(art => art._2.get(question).get).sorted

  def averageFinalScore(article: Int): Double =
    var els = reviews.filter(art => art._1 == article)
    var nEls = els.size
    els.map(art => art._2.get(Question.FINAL).get).foldRight(0.0)(_ + _) / nEls

  def acceptedArticles(): Set[Int] =
    reviews.filter(el => averageFinalScore(el._1) > 5).filter(el => el._2.get(Question.RELEVANCE).get >= 8)
      .map(art => art._1).toSet

  def sortedAcceptedArticles(): List[(Int, Double)] =
    var articles: List[(Int, Double)] = List.empty
    acceptedArticles().foreach(el => articles = articles :+ (el, averageFinalScore(el)))
    articles.sorted.reverse

object ConferenceReviewing:
  def apply(): ConferenceReviewing =
    new ConferenceReviewing()






