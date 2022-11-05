package org.routine.templates.input;

import java.util.Scanner;

public class TemplatesInputTo extends TemplatesInput {
    @Override
    public void ask(TemplatesInputData data) {
        Scanner in = new Scanner(System.in);
        System.out.println("Copy path:");
        data.to = in.nextLine();
        askNext(data);
    }
}
