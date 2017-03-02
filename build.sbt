organization := "org.helianto"

crossPaths := false

scalaVersion := "2.11.8"

lazy val root = (project in file("."))
  .enablePlugins(JavaServerAppPackaging, UniversalDeployPlugin)
  .enablePlugins(DockerPlugin)
  .settings(commonSettings)
  .settings(
    name := "helianto-mailer",
    mainClass in Compile := Some("org.helianto.mailer.Application"),
    version := "1.1.1.RELEASE",
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-xml"              % "1.0.4",
      "org.helianto"            % "helianto-core"          % "0.7.0.11-BUILD",
      "com.iservport"          %% "iservport-message"      % "1.5.2.RELEASE",
      "com.coiney"             %% "akka-mailer-sendgrid"   % "0.2.0",
      "com.typesafe.akka"      %% "akka-kernel"            % "2.4.1",
      "com.typesafe.akka"      %% "akka-actor"             % "2.4.1",
      "com.typesafe.akka"      %% "akka-slf4j"             % "2.4.1",
      "com.typesafe.akka"      %% "akka-testkit"           % "2.4.1",
      "com.typesafe.akka"      %% "akka-http-experimental" % "2.0.1",
      "com.typesafe.akka"      %% "akka-stream-kafka"      % "0.12",
      "com.typesafe"            % "config"                 % "1.3.0",
      "io.circe"               %% "circe-generic"          % "0.3.0",
      "com.github.finagle"     %% "finch-core"             % "0.10.0",
      "com.github.finagle"     %% "finch-circe"            % "0.10.0",
      "com.github.finagle"     %% "finch-jackson"          % "0.10.0",
      "com.sendgrid"            % "smtpapi-java"           % "0.1.1",
      "javax.mail"              % "mail"                   %"1.4.7",
      "org.scalatest"          %% "scalatest"              % "3.0.0"   % "test",
      "org.mockito"             % "mockito-all"            % "1.10.19" % "test"
    ),
    packageName in Docker := "mvps-156214/helianto-mailer",
    dockerBaseImage := "anapsix/alpine-java:latest",
    dockerUpdateLatest := true,
    dockerExposedPorts := Seq(8082),
    dockerRepository := Some("us.gcr.io")
  )

lazy val commonSettings = Seq(
  resolvers in ThisBuild ++= Seq(
    "Typesafe Releases"   at "https://repo.typesafe.com/typesafe/releases/",
    "Helianto Releases"  at "s3://maven.helianto.org/release",
    "Helianto Snapshots" at "s3://maven.helianto.org/snapshot",
    "Helianto Development" at "s3://maven.helianto.org/devel"
  ),
  publishTo in ThisBuild := {
    val helianto = "s3://maven.helianto.org/"
    if (version.value.trim.endsWith("SNAPSHOT"))
      Some("Helianto Snapshots" at helianto + "snapshot")
    else if (version.value.trim.endsWith("RELEASE"))
      Some("Helianto Releases" at helianto + "release")
    else
      Some("Helianto Development"  at helianto + "devel")
  },
  credentials += Credentials(Path.userHome / ".sbt" / ".s3credentials"),
  publishMavenStyle := true
)

licenses in ThisBuild := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))
