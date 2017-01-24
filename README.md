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

To build the project, run `mvn clean install` from this directory.

To run the project after building, run `mvn exec:java`

As a one-liner, you can build and run using `mvn clean install exec:java`

Reading
---

The bulk of the Clarifai-related code is in:

- The constructor in `ClarifaiSample` (which shows how to create a new instance of the `ClarifaiClient`)
- The `predictOnImageURL` method, which shows you how to get from a URL to tags returned by the API

Everything else in that file is a thin command-line prompt to retrieve and parse user-inputted URLs
