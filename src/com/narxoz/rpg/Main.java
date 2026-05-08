package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        Hero warrior = new Hero("Thorin", 150, 40, 18, 12, 200, null);
        Hero mage = new Hero("Elara", 80, 120, 8, 5, 350, null);

        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult result = engine.runVault(List.of(warrior, mage));

        System.out.println("\n=== Vault Run Summary ===");
        System.out.println(result);
    }
}
