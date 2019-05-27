package url

import java.net.{URL, URLDecoder}
import java.util.regex.Pattern

/**
  * Created by jaesang on 2019-02-21.
  */
object ParameterExtractor {
  val DEFAULT_ENCODING = "UTF-8"

  def getParameterValue(url: String, param: String): Option[String] = {
    try {

      if (url == null) None

      val urlObject = new URL(url.toLowerCase())
      val host = urlObject.getHost
      if (host == null) None

      var queryString = urlObject.getQuery
      val index = url.indexOf(urlObject.getHost)
      /**
        * getQuery() 로 query 관련 파라미터들이 없는 경우가 있어
        * Host가 끝나는 지점 이후의 파라미터들을 모두 사용
        */
      if (index > -1) {
        val start = index + urlObject.getHost.length
        queryString = url.substring(start)
      }
      println(queryString.split("\\?")(0))
      val queryMatcher = Pattern.compile("\\b" + param + "=([^&]+)")
        .matcher(queryString)

      if (!queryMatcher.find) None

      val query = queryMatcher.group(1)
      Some(URLDecoder.decode(query, DEFAULT_ENCODING))

    } catch {
      case ex: Throwable => {
        ex.printStackTrace()
        None
      }
    }


  }

  def main(args: Array[String]): Unit = {
    /**
      * 검색 쿼리관련한 파라미터는 각 포털마다 다르다
      * search.zum.com / query
      * search.naver.com / query
      * search.daum.net / q
      * google.com / q
      */

      val i="1박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌31박 2일 시즌3"
    println(i.getBytes().length)
    /*    val url = "http://search.zum.com/search.zum?method=uni&option=accu&qm=f_typing&rd=1&query=4%EC%9B%94+11%EC%9D%BC+%EC%9E%84%EC%8B%9C%EA%B3%B5%ED%9C%B4%EC%9D%BC"
        val param = "query"

        getParameterValue(url, param) match {
          case Some(query) => println(query)
          case _ => println("error")
        }*/

    val url = "http://auto.zum.com/guide_model.jsp?id_carbrand=12"
    val param = "id_carbrand"
    getParameterValue(url, param) match {
      case Some(query) => println(query)
      case _ => println("error")
    }
  }

}
