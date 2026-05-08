package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.Armor;
import com.narxoz.rpg.artifact.ArtifactVisitor;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.artifact.Ring;
import com.narxoz.rpg.artifact.Scroll;
import com.narxoz.rpg.artifact.Weapon;

public class GoldAppraiser implements ArtifactVisitor {

    private int totalValue;

    public int getTotalValue() {
        return totalValue;
    }

    @Override
    public void visit(Weapon weapon) {
        int appraisedValue = weapon.getValue() + weapon.getAttackBonus() * 10;
        totalValue += appraisedValue;
        System.out.println("  [GoldAppraiser] Weapon \"" + weapon.getName() + "\" -> " + appraisedValue + " gold");
    }

    @Override
    public void visit(Potion potion) {
        int appraisedValue = potion.getValue();
        totalValue += appraisedValue;
        System.out.println("  [GoldAppraiser] Potion \"" + potion.getName() + "\" -> " + appraisedValue + " gold");
    }

    @Override
    public void visit(Scroll scroll) {
        int appraisedValue = scroll.getValue() * 2;
        totalValue += appraisedValue;
        System.out.println("  [GoldAppraiser] Scroll \"" + scroll.getName() + "\" -> " + appraisedValue + " gold (scrolls are rare)");
    }

    @Override
    public void visit(Ring ring) {
        int appraisedValue = ring.getValue() + ring.getMagicBonus() * 15;
        totalValue += appraisedValue;
        System.out.println("  [GoldAppraiser] Ring \"" + ring.getName() + "\" -> " + appraisedValue + " gold");
    }

    @Override
    public void visit(Armor armor) {
        int appraisedValue = armor.getValue() + armor.getDefenseBonus() * 5;
        totalValue += appraisedValue;
        System.out.println("  [GoldAppraiser] Armor \"" + armor.getName() + "\" -> " + appraisedValue + " gold");
    }
}
