scalaVersion := "2.12.3"

name := "T.O.C."
organization := "br.unb.cic"
version := "1.0"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.4"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.4" % "test"

scalacOptions ++= Seq("-feature")
