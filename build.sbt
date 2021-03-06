name               := "ScalaCollider-Trace"
version            := "0.3.0"
organization       := "at.iem"
description        := "A library for debugging ScalaCollider UGen graphs by tracing their values"
homepage           := Some(url(s"https://github.com/iem-projects/${name.value}"))
licenses           := Seq("lgpl" -> url("https://www.gnu.org/licenses/lgpl-2.1.txt"))
scalaVersion       := "2.12.4"
crossScalaVersions := Seq("2.12.4", "2.11.12")

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature", "-encoding", "utf8", "-Xfuture", "-Xlint")

lazy val scalaColliderVersion = "1.23.0"
lazy val ugensVersion         = "1.17.1"

libraryDependencies ++= Seq(
  "de.sciss" %% "scalacollider"           % scalaColliderVersion,
  "de.sciss" %  "scalacolliderugens-spec" % ugensVersion
)

initialCommands in console :=
  """import de.sciss.synth._
    |import Ops._
    |import ugen._
    |import trace.ugen._
    |import trace.BundleBuilder
    |import trace.TraceOps._
    |def s = Server.default
    |""".stripMargin

// ---- publishing ----

publishMavenStyle := true

publishTo :=
  Some(if (isSnapshot.value)
    "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  else
    "Sonatype Releases"  at "https://oss.sonatype.org/service/local/staging/deploy/maven2"
  )

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := { val n = name.value
<scm>
  <url>git@github.com:iem-projects/{n}.git</url>
  <connection>scm:git:git@github.com:iem-projects/{n}.git</connection>
</scm>
<developers>
  <developer>
    <id>sciss</id>
    <name>Hanns Holger Rutz</name>
    <url>http://www.sciss.de</url>
  </developer>
</developers>
}
