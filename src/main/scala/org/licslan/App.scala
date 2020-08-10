package org.licslan

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * @author LICSLAN
 *
 */
object App{
 def main(args: Array[String]): Unit = {
    println("hi IDEA ")
   val person = new Person()
   println(person.age)
   println(person.name)
   println(person)
   println(person())
  }
}


@Component("pppp")
@Scope("prototype")
class Person {
  def apply(): Unit ={
    println("--------hello world!")
  }
  var name:String=_
  val age=29
  def person(): Unit ={
    print(age)
  }
}

/**
 * 数组  List Set Map  Option & Some & None
 * Set Tuple
 * */




