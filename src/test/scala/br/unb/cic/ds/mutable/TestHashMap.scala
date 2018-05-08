package br.unb.cic.ds.mutable

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfter

class TestHashMap extends FlatSpec with Matchers with GivenWhenThen with BeforeAndAfter {

  behavior of "A Hash Map"

  var hashMap: HashMap[String,String] = _ 

  before {
    hashMap = new ArrayHashMap[String,String](4)
  }

  it should "have hashMap(\"one\") = Some(\"um\") if hashMap(\"one\" -> \"um\") has been executed before" in {
    hashMap("one" -> "um")
    hashMap("one")   should be (Some("um"))
  }

  it should "return None after calling hashMap(\"two\") na estrutura hashMap(\"one\" -> \"um\")" in {
    hashMap("one" -> "um")
    hashMap("two")   should be (None)
  }

  it should "return None after calling hashMap(\"three\") na estrutura hashMap(\"one\" -> \"um\", \"two\" -> \"dois\")" in {
    hashMap("one" -> "um", "two" -> "dois")
    hashMap("three") should be (None)
  }

  it should "be able to retrive every value from hashMap(\"one\" -> \"um\", \"two\" -> \"dois\", \"three\" -> \"três\", \"four\" -> \"quatro\")" in {
    hashMap("one" -> "um", "two" -> "dois", "three" -> "três", "four" -> "quatro")
    hashMap("one")   should be (Some("um"))
    hashMap("two")   should be (Some("dois"))
    hashMap("three") should be (Some("três"))
    hashMap("four")  should be (Some("quatro"))
  }

  it should "remove the element (\"um\") after executing hashMap - (\"one\")" in {
    hashMap("one" -> "um")
    hashMap - ("one")
    hashMap("one")   should be (None)
  }

  it should "throw a Exception if you try to insert more elements than previously informed to the class' constructor" in {
    hashMap("one" -> "um", "two" -> "dois", "three" -> "três", "four" -> "quatro")
    
    hashMap("one")   should be (Some("um"))
    hashMap("two")   should be (Some("dois"))
    hashMap("three") should be (Some("três"))
    hashMap("four")  should be (Some("quatro"))

    intercept[InvalidArgument] {
        hashMap("five" -> "cinco")
    }

  }

  it should "be able to remove all the elements of the hashMap(\"one\" -> \"um\", \"two\" -> \"dois\", \"three\" -> \"três\", \"four\" -> \"quatro\")" in {
    hashMap("one" -> "um", "two" -> "dois", "three" -> "três", "four" -> "quatro")
    
    hashMap("one")   should be (Some("um"))
    hashMap("two")   should be (Some("dois"))
    hashMap("three") should be (Some("três"))
    hashMap("four")  should be (Some("quatro"))

    hashMap - ("one", "two", "three", "four")

  }

}
