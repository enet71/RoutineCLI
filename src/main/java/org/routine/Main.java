package org.routine;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //var path = in.nextLine();

        TemplatesManager templatesManager = new TemplatesManager();
        Path pathFrom = Paths.get("templates", "store");
        Path pathTo = Paths.get("store-copy");
        templatesManager.copyTemplate(pathFrom, pathTo);
    }
}
