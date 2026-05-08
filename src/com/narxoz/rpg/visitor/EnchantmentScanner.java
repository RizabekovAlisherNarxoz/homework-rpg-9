package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

public class EnchantmentScanner implements ArtifactVisitor {

    @Override
    public void visit(Weapon weapon) {
        System.out.println("  [EnchantmentScanner] Weapon \"" + weapon.getName()
                + "\" carries an attack enchantment of +" + weapon.getAttackBonus());
    }

    @Override
    public void visit(Potion potion) {
        System.out.println("  [EnchantmentScanner] Potion \"" + potion.getName()
                + "\" radiates a healing aura of " + potion.getHealing() + " HP");
    }

    @Override
    public void visit(Scroll scroll) {
        System.out.println("  [EnchantmentScanner] Scroll \"" + scroll.getName()
                + "\" contains the spell [" + scroll.getSpellName() + "]");
    }

    @Override
    public void visit(Ring ring) {
        System.out.println("  [EnchantmentScanner] Ring \"" + ring.getName()
                + "\" pulses with magic bonus +" + ring.getMagicBonus());
    }

    @Override
    public void visit(Armor armor) {
        System.out.println("  [EnchantmentScanner] Armor \"" + armor.getName()
                + "\" is fortified with defense +" + armor.getDefenseBonus());
    }
}
