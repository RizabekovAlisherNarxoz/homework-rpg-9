package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

public class CurseDetector implements ArtifactVisitor {

    private int cursedCount;

    public int getCursedCount() {
        return cursedCount;
    }

    @Override
    public void visit(Weapon weapon) {
        if (weapon.getAttackBonus() < 0) {
            cursedCount++;
            System.out.println("  [CurseDetector] WARNING: Weapon \"" + weapon.getName() + "\" is CURSED (attack penalty " + weapon.getAttackBonus() + ")");
        } else {
            System.out.println("  [CurseDetector] Weapon \"" + weapon.getName() + "\" is clean");
        }
    }

    @Override
    public void visit(Potion potion) {
        if (potion.getHealing() < 0) {
            cursedCount++;
            System.out.println("  [CurseDetector] WARNING: Potion \"" + potion.getName() + "\" is POISONOUS (healing " + potion.getHealing() + ")");
        } else {
            System.out.println("  [CurseDetector] Potion \"" + potion.getName() + "\" is safe");
        }
    }

    @Override
    public void visit(Scroll scroll) {
        String spell = scroll.getSpellName().toLowerCase();
        if (spell.contains("doom") || spell.contains("curse") || spell.contains("hex")) {
            cursedCount++;
            System.out.println("  [CurseDetector] WARNING: Scroll \"" + scroll.getName() + "\" carries a dark spell [" + scroll.getSpellName() + "]");
        } else {
            System.out.println("  [CurseDetector] Scroll \"" + scroll.getName() + "\" spell is benign");
        }
    }

    @Override
    public void visit(Ring ring) {
        if (ring.getMagicBonus() < 0) {
            cursedCount++;
            System.out.println("  [CurseDetector] WARNING: Ring \"" + ring.getName() + "\" is CURSED (magic penalty " + ring.getMagicBonus() + ")");
        } else {
            System.out.println("  [CurseDetector] Ring \"" + ring.getName() + "\" is clean");
        }
    }

    @Override
    public void visit(Armor armor) {
        if (armor.getDefenseBonus() < 0) {
            cursedCount++;
            System.out.println("  [CurseDetector] WARNING: Armor \"" + armor.getName() + "\" is CURSED (defense penalty " + armor.getDefenseBonus() + ")");
        } else {
            System.out.println("  [CurseDetector] Armor \"" + armor.getName() + "\" is clean");
        }
    }
}
