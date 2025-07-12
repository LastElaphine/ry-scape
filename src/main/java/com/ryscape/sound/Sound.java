package com.ryscape.sound;

public enum Sound {
    LOGIN("login.wav");

    private final String resourceName;

    Sound(String name) {
        resourceName = name;
    }

    public String getResourceName() {
        return resourceName;
    }
}
