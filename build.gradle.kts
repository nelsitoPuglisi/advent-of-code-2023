plugins {
    kotlin("jvm") version "1.9.20"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
    test {
        kotlin.srcDir("test")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}

dependencies {
    testImplementation("com.google.truth:truth:1.1.4")
}