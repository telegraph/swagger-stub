resolvers += "mvn-artifacts" at "s3://mvn-artifacts/release"

addSbtPlugin("com.frugalmechanic" % "fm-sbt-s3-resolver"   % "0.9.0")
addSbtPlugin("com.eed3si9n"       % "sbt-assembly"         % "0.14.4")
