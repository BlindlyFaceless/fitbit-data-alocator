package com.fitbitdataalocator;

import java.io.FileReader;
import java.io.IOException;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        fitbitreader reader = new fitbitreader();

        try {
            reader.run();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
