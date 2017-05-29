package com.vakhnenko;

import com.vakhnenko.service.PredecessorGit;
import org.apache.log4j.Logger;

public class App {
    public static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("\nApplicaion start...");
        PredecessorGit predecessorGit = new PredecessorGit(args);
        predecessorGit.run();
        logger.info("\nApplicaion done...");
    }
}
