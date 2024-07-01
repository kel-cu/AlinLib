package ru.kelcuprum.alinlib.gui.components;

public interface Resetable {
    void resetValue();
    default boolean resettable(){
        return true;
    };
}
