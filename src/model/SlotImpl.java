package model;

import model.enumeration.Color;
import model.interfaces.Slot;

import java.util.Objects;

public class SlotImpl implements Slot {
    private int position;
    private int number;
    private Color color;

    public SlotImpl(int position, int number, Color color) {
        this.position = position;
        this.number = number;
        this.color = color;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public boolean equals(Slot slot) {
        if (slot == null || getClass() != slot.getClass()) return false;
        SlotImpl slots = (SlotImpl) slot;
        return position == slots.position &&
                number == slots.number &&
                color == slots.color;
    }

    @Override
    public boolean equals(Object slot) {
        if (slot == null || getClass() != slot.getClass()) return false;
        SlotImpl slots = (SlotImpl) slot;
        return position == slots.position &&
                number == slots.number &&
                color == slots.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, number, color);
    }

    @Override
    public String toString() {
        return String.format("Position: %s, Color: %s, Number: %s", position, color, number);
    }
}
