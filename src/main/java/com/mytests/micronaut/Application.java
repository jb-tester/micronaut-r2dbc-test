package com.mytests.micronaut;

import io.micronaut.runtime.Micronaut;
import jakarta.inject.Inject;

public class Application {


    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
