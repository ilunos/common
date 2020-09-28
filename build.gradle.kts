plugins {
    kotlin("jvm") version "1.4.10"

    id("maven-publish")
    id("com.jfrog.bintray") version "1.8.5"
    `java-library`
    java
}

group = "com.ilunos.common"
version = "1.0.1"

repositories {
    maven("https://dl.bintray.com/nanabell/ilunos")
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

publishing {
    publications {
        create<MavenPublication>("ilunos-common") {
            from(components["java"])

            pom {
                name.set("Ilunos Common")
                description.set("Common Library used by other Ilunos components")

                developers {
                    developer {
                        id.set("nanabell")
                        name.set("Nanabell")
                        organization.set("Ilunos")
                    }
                }
            }
        }
    }
}

tasks {

    java {
        withJavadocJar()
        withSourcesJar()
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "13"
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "13"
    }
}

bintray {
    user = project.findProperty("bintray.user").toString()
    key = project.findProperty("bintray.key").toString()
    setPublications("ilunos-common")
    publish = false
    pkg.apply {
        repo = "ilunos"
        name = "ilunos-common"
        version.apply {
            name = "v${project.version}"
            vcsTag = "v${project.version}"
        }
    }
}