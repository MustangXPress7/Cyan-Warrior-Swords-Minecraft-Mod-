package com.raptorbk.CyanWarriorSwordsRedux;

import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.IItemTier;

public class METEOR_CLASS_SWORD extends SWORD_CWSR{

    public METEOR_CLASS_SWORD(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    public boolean delayMeteor=false;
    public FireballEntity delayedMeteor=null;

    public void setDelayMeteor(boolean delayMeteor) {
        this.delayMeteor = delayMeteor;
    }

    public boolean getDelayMeteor(){
        return this.delayMeteor;
    }


    public FireballEntity getDelayedMeteor() {
        return this.delayedMeteor;
    }

    public void setDelayedMeteor(FireballEntity delayedMeteor) {
        this.delayedMeteor = delayedMeteor;
    }
}
