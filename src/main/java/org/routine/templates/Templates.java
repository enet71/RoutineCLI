package org.routine.templates;

import org.routine.templates.input.*;

public class Templates {
    public static void run() {
        TemplatesInput input = createInputsChain();
        input.ask(new TemplatesInputData());
    }

    private static TemplatesInput createInputsChain() {
        return TemplatesInput.createInputsChain(
                new TemplatesInputFrom(),
                new TemplatesInputTo(),
                new TemplatesInputCopy()
        );
    }
}
