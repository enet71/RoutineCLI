package org.routine.templates.input;

import java.util.Scanner;

public class TemplatesInputFrom extends TemplatesInput {
    @Override
    public void ask(TemplatesInputData data) {
        Scanner in = new Scanner(System.in);
        System.out.println("Template name:");
        data.from = in.nextLine();
        askNext(data);
    }
}
