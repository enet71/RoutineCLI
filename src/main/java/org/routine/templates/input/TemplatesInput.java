package org.routine.templates.input;

public abstract class TemplatesInput {
    private TemplatesInput next;

    public static TemplatesInput createInputsChain(TemplatesInput... inputs) {
        TemplatesInput currentInput = inputs[0];

        for (var i = 1; i < inputs.length; i++) {
            TemplatesInput nextInput = inputs[i];
            currentInput.next = nextInput;
            currentInput = nextInput;
        }

        return inputs[0];
    }

    public abstract void ask(TemplatesInputData data);

    protected void askNext(TemplatesInputData data) {
        if (next != null) {
            next.ask(data);
        }
    }
}
