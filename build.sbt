ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

lazy val root = (project in file("."))
  .settings(
    name := "K-Means-Scala"
  )

libraryDependencies  ++= Seq(
  // Last stable release
  "org.scalanlp" %% "breeze" % "2.0.1-RC1",

  // The visualization library is distributed separately as well.
  // It depends on LGPL code
  "org.scalanlp" %% "breeze-viz" % "2.0.1-RC1"
)

// https://mvnrepository.com/artifact/com.github.haifengl/smile-plot
libraryDependencies += "com.github.haifengl" % "smile-plot" % "2.6.0"
libraryDependencies += "com.github.haifengl" % "smile-core" % "2.6.0"

// https://mvnrepository.com/artifact/org.slf4j/slf4j-api
libraryDependencies += "org.slf4j" % "slf4j-api" % "2.0.0-alpha7"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "2.0.0-alpha7"

// https://mvnrepository.com/artifact/org.bytedeco/openblas
libraryDependencies += "org.bytedeco" % "openblas" % "0.3.19-1.5.7"

// https://mvnrepository.com/artifact/org.bytedeco/openblas-platform
libraryDependencies += "org.bytedeco" % "openblas-platform" % "0.3.19-1.5.7"
