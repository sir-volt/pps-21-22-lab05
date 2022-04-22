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

object ConferenceReviewing:
  /*
  def apply: ConferenceReviewing
    ConferenceReviewingImpl()
  case class ConferenceReviewingImpl() extends ConferenceReviewing:*/
  private var reviews: List[(Int, Map[Question, Int])] = List.empty

  def loadReview(article: Int, scores: Map[Question, Int]): Unit =
    if(scores.size < Question.values.length) then
      throw IllegalArgumentException()
    else
      reviews = reviews :+ (article, scores)

  def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit =
    var values: Map[Question, Int] = Map(Question.RELEVANCE -> relevance, Question.SIGNIFICANCE -> significance,
      Question.CONFIDENCE -> confidence, Question.FINAL -> fin)
    reviews = reviews :+ (article, values)

  def orderedScores(article: Int, question: Question): List[Int] =
    reviews.filter(art => art._1 == article).map(art => art._2.get(question).get).sorted




