package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class ControllerImpl implements Controller {

    private String str;
    private final List<String> list = new ArrayList<>();
    @Override
    public final void setNextToPrint(final String string) {
        this.str = Objects.requireNonNull(string);
    }

    @Override
    public final String getNextToPrint() {
        return this.str;
    }

    @Override
    public final List<String> history() {
        //returns a defensive copy
        return Collections.unmodifiableList(this.list);
    }

    @Override
    public final void printCurrentString() {
        if (!this.str.isBlank()) {
            System.out.println(this.str);
            this.list.add(this.str);
        } else {
            throw new IllegalStateException();
        }
    }

}
