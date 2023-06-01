import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jmailen.kotlinter")
    kotlin("jvm")
}

group = "tw.wsa"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

val kotlinVersion: String by project
val kotlinCoroutineVersion: String by project
val junitVersion: String by project

dependencies {
    implementation(project(":libs:jvm"))

    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$kotlinCoroutineVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kotlinter {
    reporters = arrayOf("html")
}
