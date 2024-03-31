repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    project(":api")
    project(":common")

    compileOnly("com.velocitypowered:velocity-api:3.3.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.3.0-SNAPSHOT")
}