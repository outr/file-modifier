name := "file-modifier"
organization := "com.outr"
version := "1.0.0"

scalaVersion := "2.13.8"
fork := true

assembly / mainClass := Some("modifier.FileModifier")
assembly / assemblyJarName := "FileModifier.jar"