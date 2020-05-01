import kotlin.collections.mapOf
val kotlinVersion = "1.3.61"

plugins {
    java
    id("com.google.cloud.tools.jib") version "1.8.0"
    kotlin("jvm") version "1.3.61"
}

group = "dev.apstn"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

}
dependencies {
    implementation(group="com.google.cloud", name="google-cloud-bigquery", version="1.108.1")
    // implementation(group= "com.google.zetasql", name= "zetasql-client", version= "2020.03.2")
    // implementation(group= "com.google.zetasql", name= "zetasql-types", version= "2020.03.2")
    // implementation(group= "com.google.zetasql", name= "zetasql-jni-channel", version= "2020.03.2")
    implementation(files(
        "../bazel-bin/java/com/google/zetasql/client_jar.jar",
        "../bazel-bin/java/com/google/zetasql/types_jar.jar",
        "../bazel-bin/java/com/google/zetasql/jni_channel_jar.jar",
        "../bazel-bin/java/com/google/zetasql/jni_channel_linux_jar.jar"
    ))
    implementation(group= "io.netty", name= "netty-transport", version= "4.1.34.Final")
    implementation(group= "io.netty", name= "netty-common", version= "4.1.34.Final")
    implementation(group= "io.netty", name= "netty-resolver", version= "4.1.34.Final")
    implementation(group= "io.netty", name= "netty-handler", version= "4.1.34.Final")
    implementation(group= "io.netty", name= "netty-buffer", version= "4.1.34.Final")
    implementation(group= "io.netty", name= "netty-codec", version= "4.1.34.Final")
    implementation(group= "io.netty", name= "netty-codec-http", version= "4.1.34.Final")
    implementation(group= "io.netty", name= "netty-codec-http2", version= "4.1.34.Final")
    implementation(group= "com.google.protobuf", name= "protobuf-java", version= "3.6.1")
    implementation(group= "com.google.protobuf", name= "protobuf-java-util", version= "3.6.1")
    implementation(group= "com.google.api.grpc", name= "proto-google-common-protos", version= "1.12.0")
    implementation(group= "com.google.guava", name= "guava", version= "26.0-android")
    implementation(group= "io.opencensus", name= "opencensus-api", version= "0.21.0")
    implementation(group= "io.opencensus", name= "opencensus-contrib-grpc-metrics", version= "0.21.0")
    implementation(group= "javax.annotation", name= "javax.annotation-api", version= "1.2")
    implementation(group= "com.google.code.findbugs", name= "jsr305", version= "3.0.2")
    implementation(group= "com.google.errorprone", name= "error_prone_annotations", version= "2.3.2")
    implementation(group= "joda-time", name= "joda-time", version= "2.3")
    // implementation(file("../bazel-bin/java/com/google/zetasql/types_jar.jar"))
    // implementation(file("../bazel-bin/java/com/google/zetasql/jni_channel_jar.jar"))
    // implementation(file("../bazel-bin/java/com/google/zetasql/jni_channel_linux_jar.jar"))
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("junit:junit:4.12")
    testImplementation(group="org.jetbrains.kotlin", name="kotlin-test-junit",version="1.3.61")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

jib {
    container {
        mainClass = "zetasql.Main"
        environment = mapOf(
            Pair("SUPPRESS_GCLOUD_CREDS_WARNING", "true"),
            Pair("JAVA_TOOL_OPTIONS", "-Dfile.encoding=UTF-8")
        )
    }
}
