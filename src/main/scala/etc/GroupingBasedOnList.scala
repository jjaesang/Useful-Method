package etc

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks.{break, breakable}

/**
  * Created by jaesang on 2019-05-31.
  */
case class GroupingInfo(
                         key: String,
                         value: String
                       )

class GroupingBasedOnSeq {

  /**
    * 예시로 jaccard Similarity 구현
    * 상황에 맞는 유사도 공식을 구현할 예정
    *
    * @param similarStr1
    * @param similarStr2
    * @return
    */
  private def jaccardSimilarity(similarStr1: String, similarStr2: String): Double = {
    val _s1Token = similarStr1.split(" ")
    val _s2Token = similarStr2.split(" ")
    val _unionToken = (_s1Token ++ _s2Token).distinct
    val _intersetToken = _s1Token.intersect(_s2Token)

    _intersetToken.size.toDouble / _unionToken.size.toDouble
  }

  /**
    * 가중치가 높은 것으로 정렬된 하나의 리스트를 유사도 함수를 통해 GroupBy 하는 메소드
    *
    * @param unGroupedLists
    * @param threshold
    * @return
    */
  def groupingOneListBasedOnSimilarity(unGroupedLists: List[String], threshold: Double)
  : Seq[(String, ArrayBuffer[String])] = {

    val groupingList = ArrayBuffer[GroupingInfo]()

    try {
      val size = unGroupedLists.size
      for (i <- 0 until size) {
        val source = unGroupedLists(i)
        breakable {
          if (groupingList.map(_.value).contains(source))
            break
          else {
            val simList = ArrayBuffer[(String, String, Double)]()
            for (j <- i until size) {
              val dest = unGroupedLists(j)
              breakable {
                if (groupingList.map(_.value).contains(dest))
                  break
                else {
                  val sim = jaccardSimilarity(source, dest)
                  if (sim >= threshold) {
                    simList += ((dest, dest, sim))
                  }
                }
              }
            }
            simList.foreach(dest => {
              groupingList += GroupingInfo(source, dest._1)
            })
          }
        }
      }
    }
    catch {
      case e: Exception => {
        e.printStackTrace()
      }
    }

    /**
      * Key값으로 그룹화 해, 리턴해줌
      */
    groupingList
      .groupBy(_.key)
      .mapValues(_.map(_.value))
      .toSeq

  }

}
