import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "3.2.4"
  id("io.spring.dependency-management") version "1.1.4"
  kotlin("jvm") version "1.9.23"
  kotlin("plugin.spring") version "1.9.23"
}
apply(plugin="org.springframework.boot")
apply(plugin = "io.spring.dependency-management")


group = "com.github.peco2282"
version = "0.0.1-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_21
//  languageVersion = JavaLanguageVersion.of(22)
//  compileCompatibility = JavaVersion.VERSION_22
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("com.mysql:mysql-connector-j:8.3.0")
  implementation("org.thymeleaf:thymeleaf")
  implementation("org.thymeleaf:thymeleaf-spring6")
  implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("com.auth0:java-jwt:4.4.0")

//  implementation("org.springdoc:springdoc-openapi-webmvc-core:1.8.0")
//  implementation("org.springdoc:springdoc-openapi-ui:1.8.0")
//  implementation("org.springdoc:springdoc-openapi-security:1.8.0")
//  implementation("org.springdoc:springdoc-openapi-data-rest:1.8.0")
//  implementation("org.springdoc:springdoc-openapi-javadoc:1.8.0")
//  runtimeOnly("org.springdoc:springdoc-openapi-kotlin:1.8.0")
//  compileOnly("javax.servlet:javax.servlet-api:4.0.1")
  implementation("org.springframework.security:spring-security-oauth2-resource-server")
  implementation("org.springframework.security:spring-security-oauth2-jose")

  implementation("com.nimbusds:nimbus-jose-jwt:9.39")
  implementation("org.projectlombok:lombok:1.18.28")

  implementation("org.springframework.boot:spring-boot-starter-jdbc")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.springframework.data:spring-data-rest-hal-explorer")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")

  developmentOnly("org.springframework.boot:spring-boot-devtools")

  testImplementation("io.projectreactor:reactor-test")
//  testImplementation("org.assertj:assertj-core")
  testImplementation("com.google.guava:guava:33.2.0-jre")
  testImplementation("org.thymeleaf.testing:thymeleaf-testing-spring6:3.1.2.RELEASE")
  testImplementation("org.thymeleaf.testing:thymeleaf-testing:3.1.2.RELEASE")
  testImplementation("org.assertj:assertj-core:3.25.3")

  testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += "-Xjsr305=strict"
    jvmTarget = "21"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
