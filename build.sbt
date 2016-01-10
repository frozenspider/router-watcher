name         := "router-watcher"
version      := "1.3"
scalaVersion := "2.11.7"

EclipseKeys.withSource := true
EclipseKeys.createSrc  := EclipseCreateSrc.Default + EclipseCreateSrc.Resource + EclipseCreateSrc.Managed

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies ++= Seq(
  // Logging
  "org.slf4s"               %% "slf4s-api"            % "1.7.12",
  "ch.qos.logback"          %  "logback-classic"      % "1.1.2",
  // Database
  "com.typesafe.slick"      %% "slick"                % "3.1.0",
  "mysql"                   %  "mysql-connector-java" % "5.1.36",
  // Other
  "com.github.frozenspider" %  "scala-web-utils"      % "0.1",
  "org.apache.commons"      %  "commons-lang3"        % "3.4",
  "com.github.nscala-time"  %  "nscala-time_2.11"     % "2.2.0",
  "com.typesafe"            %  "config"               % "1.3.0",
  // Test
  "junit"                   %  "junit"                % "4.11"  % "test",
  "org.scalatest"           %% "scalatest"            % "2.2.4" % "test"
)

lazy val root = (project in file("."))
  .enablePlugins(BuildInfoPlugin)
  .settings(
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion, buildInfoBuildNumber),
    buildInfoPackage := "org.fs.rw"
  )
