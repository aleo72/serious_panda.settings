

name := """serious-panda-settings"""

version := "0.2.1"

scalaVersion := "2.11.0"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.1.3" % "test"

unmanagedJars in Compile += Attributed.blank(baseDirectory.value / "lib/layouts.jar")

assemblySettings