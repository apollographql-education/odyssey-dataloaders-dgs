# (Odyssey Course) GraphQL Federation with Java & DGS

Welcome to the starter code for **GraphQL Federation with Java & DGS**. This is the second course in our GraphQL and DGS series. You can find the [course lessons and instructions](https://apollographql.com/tutorials/dgs-federation) on Odyssey, [Apollo](https://apollographql.com)'s learning platform.

## How to use this repo

The course will walk you step by step on what to do. The code in this repository picks up where the project from [Intro to GraphQL with Java & DGS](http://apollographql.com/tutorials/intro-dgs) leaves off; if you'd like, follow along with course using your own repo! If you need a refresher on GraphQL concepts, jump back into the first course and join us here when you are ready.

This project uses Java and requires JDK 17. To start the project, open a new terminal and run:

`./gradlew bootRun`

Or navigate to the `SoundtracksApplication.java` file and click `Run` in your IDE.

The `final` branch of this repo contains the final stage of the course, with all of the steps and code completed! If you get stuck, you can refer to it and compare your code.

## Getting Help

This repo is _not regularly monitored_.

For any issues or problems concerning the course content, please refer to the [Odyssey topic in our community forums](https://community.apollographql.com/tags/c/help/6/odyssey). You can also [join the Apollo Discord](https://discord.gg/graphos).


# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.0/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.0/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.0/reference/htmlsingle/index.html#web)

### Guides

Please revisit the first course in this series anytime if you get stuck or need a refresher.
* [Intro to GraphQL with Java & DGS](https://apollographql.com/tutorials/intro-dgs)

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)


### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

## GraphQL code generation with DGS

This project has been configured to use the Netflix DGS Codegen plugin.
This plugin can be used to generate client files for accessing remote GraphQL services.
The default setup assumes that the GraphQL schema file for the remote service is added to the `src/main/resources/schema/` location.

You can learn more about the [plugin configuration options](https://netflix.github.io/dgs/generating-code-from-schema/#configuring-code-generation) and
[how to use the generated types](https://netflix.github.io/dgs/generating-code-from-schema/) to adapt the default setup.


