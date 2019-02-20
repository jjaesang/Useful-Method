package db

/**
  * Created by jaesang on 2019-02-20.
  * 대용량 문서 데이터를 저장할 때, 지정한 DB 컬럼의 사이즈보다 큰 경우 Exception이 발생하기 때문에
  * Insert / Upsert 하기 전, 컬럼에 들어갈 byte 사이즈를 검사 후, 쪼개서 넣어주어야한다.
  *
  */
object ByteSubString {


  def byteSubString(str: String, maxSize: Int): String = {

    val resultByteArray = new Array[Byte](maxSize)

    try {
      if (str == null)
        return ""

      val inputByteArray: Array[Byte] = str.getBytes
      var _length = maxSize

      if (maxSize > inputByteArray.length)
        _length = inputByteArray.length

      System.arraycopy(inputByteArray, 0, resultByteArray, 0, _length)
    }
    catch {
      case e: Exception =>
        e.printStackTrace()
        System.arraycopy(str, 0, resultByteArray, 0, maxSize / 3)
    }
    new String(resultByteArray)

  }

  def main(args: Array[String]): Unit = {
    val input = "이 데이터는 아마도 DB 컬럼의 사이즈를 초과할 것이다"
    println(input.getBytes().length)
    val maxSize = 60

    println(s"Result : ${byteSubString(input, maxSize)}")
    //   30   Result : 이 데이터는 아마도 DB
    //   62   Result : 이 데이터는 아마도 DB 컬럼의 사이즈를 초과할
  }

}
