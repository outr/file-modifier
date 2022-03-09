import scala.scalanative.build._

name := "file-modifier"
organization := "com.outr"
version := "1.0.0"

scalaVersion := "3.1.1"
nativeLinkStubs := true
fork := true

enablePlugins(ScalaNativePlugin)

nativeConfig ~= {
  _.withLTO(LTO.thin)
    .withMode(Mode.releaseFast)
    .withGC(GC.commix)
}

Compile / nativeLink / artifactPath := file("file-modifier")