/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author stabeeb
 */

public abstract class FoodComponent {

    protected String name;

    public FoodComponent(String name) {
        this.name = name;
    }

    public abstract double getPrice();
    public abstract void print(int level);
}
