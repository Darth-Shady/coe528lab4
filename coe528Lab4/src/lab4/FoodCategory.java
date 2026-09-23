/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4;

/**
 *
 * @author stabeeb
 */

import java.util.ArrayList;

public class FoodCategory extends FoodComponent {

    private ArrayList<FoodComponent> components;

    public FoodCategory(String name) {
        super(name);
        components = new ArrayList<>();
    }

    public void add(FoodComponent fc) {
        components.add(fc);
    }

    public void remove(FoodComponent fc) {
        components.remove(fc);
    }

    @Override
    public double getPrice() {
        double total = 0;
        for (FoodComponent fc : components) {
            total += fc.getPrice();
        }
        return total;
    }

    @Override
    public void print(int level) {

        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }

        System.out.println("FoodCategory (" + name + ", " + getPrice() + ") contains:");

        for (FoodComponent fc : components) {
            fc.print(level + 1);
        }
    }
}
