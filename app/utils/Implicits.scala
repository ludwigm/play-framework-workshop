package utils


import org.joda.time.DateTime

object Implicits {


  implicit def dateTimeOrdering: Ordering[DateTime] = Ordering.fromLessThan(_ isBefore _)

}
