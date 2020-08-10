package org.licslan

object ArrayApp extends App {

//  def main(args: Array[String]): Unit = {
//    println("hello scala")
//  }
  val test = new Array[String](5)
  val w =9
  //w=10
  var d = 1000
  d = 100
  //遍历
  for (elem <- test) {
    println(elem)
  }


  //if else
  var i =100
  if (i>100) println(100)
  else println(1000)


  def matchTest(x:Int): String = x match {
    case 1=>"a"
    case 2=>"b"
    case 3=>"c"
    case _=>"many"
  }

  def matchTestA(x:Int): Int = x match {
    case 1=>1
    case 2=>2
    case 3=>3
    case _=>100
  }
  println(matchTest(100))
  println(matchTestA(1000))

  print(test)
  println(test.length)
  //test(1)="hello"
  println(test.length)
  println(test(1))

  val b = Array("spark","hadoop")
  println(b.toString.charAt(1))

}

