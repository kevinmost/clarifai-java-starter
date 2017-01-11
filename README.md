Clarifai Java Starter
======

This is a minimal starter project that shows how to use the [Clarifai Java API client](https://github.com/Clarifai/clarifai-java)
to predict tags in images.

Getting Started
---

The first thing you need to do is get an app ID and secret by signing up for an account on the 
[Clarifai dev portal](https://developer.clarifai.com/login/) and creating an app.

Place the app ID and secret in the [ClarifaiSample.java](src/main/java/com/clarifai/starter/ClarifaiSample.java) file's
constructor, in place of the two placeholder strings that are passed to the `ClarifaiBuilder` (there is a `TODO` comment
right above this line).

Building
---

This project uses the handy [maven-wrapper](https://github.com/takari/maven-wrapper) plugin to ease getting started;
all you need is the JDK 6 or greater installed on your computer. maven-wrapper provides an embedded instance of Maven,
similar to Gradle's `gradlew`, so users don't need to have Maven installed on their computer to build this project.

To build the project, run `./mvnw clean install` from this directory (or `./mvnw.cmd clean install` on Windows).

To run the project after building, run `./mvnw exec:java`

As a one-liner, you can build and run using `./mvnw clean install exec:java`

If you have Maven installed locally, you can also replace all usages of `./mvnw` with a call to your local Maven, which
is usually available as `mvn`.

Reading
---

The bulk of the Clarifai-related code is in:

- The constructor in `ClarifaiSample` (which shows how to create a new instance of the `ClarifaiClient`)
- The `predictOnImageURL` method, which shows you how to get from a URL to tags returned by the API

Everything else in that file is a thin command-line prompt to retrieve and parse user-inputted URLs
