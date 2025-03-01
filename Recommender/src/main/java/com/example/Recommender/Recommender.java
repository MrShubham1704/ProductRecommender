package com.example.Recommender;


import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class Recommender {
    public static void main(String[] args) {
        try {
            // Load data file (CSV format: userID, productID, rating)
            File file = new File("data.csv"); // Create this file with user ratings
            DataModel model = new FileDataModel(file);

            // Define similarity algorithm
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Define neighborhood
            UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);

            // Create recommender system
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            // Generate recommendations for a user (e.g., user 1)
            List<RecommendedItem> recommendations = recommender.recommend(1, 5);

            // Display recommendations
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Recommended Product ID: " + recommendation.getItemID() +
                        " - Score: " + recommendation.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
