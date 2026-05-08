package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;
import com.narxoz.rpg.visitor.CurseDetector;
import com.narxoz.rpg.visitor.EnchantmentScanner;
import com.narxoz.rpg.visitor.GoldAppraiser;
import com.narxoz.rpg.visitor.WeightCalculator;
import java.util.List;

public class ChronomancerEngine {

    public VaultRunResult runVault(List<Hero> party) {
        System.out.println("\n--- Entering the Chronomancer's Vault ---");

        Inventory vaultInventory = new Inventory();
        vaultInventory.addArtifact(new Weapon("Flamebrand Sword", 200, 8, 12));
        vaultInventory.addArtifact(new Potion("Elixir of Mending", 50, 1, 80));
        vaultInventory.addArtifact(new Scroll("Arcane Codex", 150, 0, "Time Rift"));
        vaultInventory.addArtifact(new Ring("Obsidian Band", 100, 0, -3));
        vaultInventory.addArtifact(new Armor("Mithril Plate", 300, 20, 18));
        vaultInventory.addArtifact(new Scroll("Doombringer Scroll", 80, 0, "Hex Doom"));

        int artifactsAppraised = vaultInventory.size();

        System.out.println("\n[Appraisal Phase - GoldAppraiser]");
        GoldAppraiser goldAppraiser = new GoldAppraiser();
        vaultInventory.accept(goldAppraiser);
        System.out.println("  Total vault value: " + goldAppraiser.getTotalValue() + " gold");

        System.out.println("\n[Appraisal Phase - EnchantmentScanner]");
        EnchantmentScanner enchantmentScanner = new EnchantmentScanner();
        vaultInventory.accept(enchantmentScanner);

        System.out.println("\n[Appraisal Phase - CurseDetector]");
        CurseDetector curseDetector = new CurseDetector();
        vaultInventory.accept(curseDetector);
        System.out.println("  Cursed artifacts found: " + curseDetector.getCursedCount());

        System.out.println("\n[Appraisal Phase - WeightCalculator]");
        WeightCalculator weightCalculator = new WeightCalculator();
        vaultInventory.accept(weightCalculator);
        System.out.println("  Total carry weight: " + weightCalculator.getTotalWeight() + " lbs");

        Caretaker caretaker = new Caretaker();
        int mementosCreated = 0;
        int restoredCount = 0;

        for (Hero hero : party) {
            System.out.println("\n[Snapshot] Saving state for " + hero.getName()
                    + " -> HP=" + hero.getHp() + " Mana=" + hero.getMana() + " Gold=" + hero.getGold());
            HeroMemento snapshot = hero.createMemento();
            caretaker.save(snapshot);
            mementosCreated++;

            System.out.println("[Vault Trap] " + hero.getName() + " triggers a time-crystal trap!");
            hero.takeDamage(40);
            hero.spendMana(30);
            hero.spendGold(50);
            System.out.println("[After Trap] " + hero.getName()
                    + " -> HP=" + hero.getHp() + " Mana=" + hero.getMana() + " Gold=" + hero.getGold());

            System.out.println("[Rewind] Chronomancer restores " + hero.getName() + " to saved state...");
            HeroMemento saved = caretaker.undo();
            hero.restoreFromMemento(saved);
            restoredCount++;
            System.out.println("[After Rewind] " + hero.getName()
                    + " -> HP=" + hero.getHp() + " Mana=" + hero.getMana() + " Gold=" + hero.getGold());
        }

        System.out.println("\n--- Vault run complete ---");

        return new VaultRunResult(artifactsAppraised, mementosCreated, restoredCount);
    }
}
