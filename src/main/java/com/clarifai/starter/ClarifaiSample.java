package com.clarifai.starter;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class ClarifaiSample {

  public static void main(String[] args) {
    new ClarifaiSample();
  }

  private final ClarifaiClient clarifaiClient;

  private ClarifaiSample() {
    // Build an instance of the ClarifaiClient and hold onto it for use later
    clarifaiClient = new ClarifaiBuilder(
        // TODO: REPLACE WITH YOUR APP ID + SECRET. Register at https://developer.clarifai.com/login/ to get one
        "APP_ID",
        "APP_SECRET"
    ).buildSync();

    Scanner scanner = new Scanner(System.in);
    promptForURL();
    while (scanner.hasNextLine()) {
      try {
        URL imageURL = new URL(scanner.nextLine());
        // Now take our image URL (which we have parsed and know is valid) and give it to Clarifai to predict
        // Note that this URL could still point to a non-image-file or could be unavailable publicly. In those cases,
        // the Clarifai API will return a failed prediction result
        predictOnImageURL(imageURL);
      } catch (MalformedURLException e) {
        System.out.println("Invalid URL! Try again");
      }
      promptForURL();
    }
  }

  /**
   * Prints a prompt asking for an image URL to stdout
   */
  private void promptForURL() {
    System.out.println();
    System.out.println("===============================");
    System.out.println(
        "Please enter a publicly-accessible URL to an image to receive predictions for it, or press Ctrl + C to terminate.");
  }

  /**
   * Takes an image URL and predicts using the Clarifai API, and prints the results to stdout
   */
  private void predictOnImageURL(URL imageURL) {
    if (imageURL == null) {
      throw new NullPointerException("imageURL must not be null");
    }
    ClarifaiResponse<List<ClarifaiOutput<Concept>>> response = clarifaiClient
        .getDefaultModels().generalModel() // gets the core concept-recognizing Clarifai model
        .predict() // Start building a request to predict
        .withInputs(ClarifaiInput.forImage(ClarifaiImage.of(imageURL))) // add an image to tag. More images could be predicted within this same request if desired
        .executeSync();

    // API calls could fail for a number of reasons (network issues, bad inputs, Clarifai server issues, etc)
    if (!response.isSuccessful()) {
      System.out.println("Response wasn't successful! Error: " + response.getStatus());
      return;
    }

    // If we got to here, the API call was successful, so we can retrieve the value of the response safely
    List<ClarifaiOutput<Concept>> outputs = response.get();

    // If you had predicted with more than one image, you would have one output for each of the input images, with the
    // order of your inputs preserved. Since we only predicted with one image, we can safely retrieve the first output
    ClarifaiOutput<Concept> output = outputs.get(0);
    System.out.println();
    System.out.println("Predicted tags and their associated probabilities");
    System.out.println("-----------");

    // .data() returns the predictions output by the model for this image (Concepts for a ConceptModel, Colors for a ColorModel, etc)
    for (Concept predictedConcept : output.data()) {
      System.out.println(predictedConcept.name() + ": " + predictedConcept.value());
    }
  }
}
