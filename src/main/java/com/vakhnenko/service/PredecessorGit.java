package com.vakhnenko.service;

import static com.vakhnenko.App.logger;

public class PredecessorGit {
    private String[] commands;

    public PredecessorGit(String[] commands) {
        this.commands = commands;
    }

    public void run() {
        if (commands.length == 0) {
            logger.error("ERROR! Usage:");
            logger.error("pre-git <project_directory>");
        }
/*
        switch (commands[COMMAND_POSITION]) {
            case EXIT_COMMAND:
                return false;

        }*/
    }
}
