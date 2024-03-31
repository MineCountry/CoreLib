repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    project(":api")
    project(":common")

    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
}