package org.routine.templates.input;

import org.routine.templates.TemplatesManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TemplatesInputCopy extends TemplatesInput {
    @Override
    public void ask(TemplatesInputData data) {
        if (data.from != null && data.to != null) {
            TemplatesManager templatesManager = new TemplatesManager();
            Path pathFrom = Paths.get("templates", data.from);
            Path pathTo = Paths.get(data.to);
            templatesManager.copyTemplate(pathFrom, pathTo);
        }
    }
}
