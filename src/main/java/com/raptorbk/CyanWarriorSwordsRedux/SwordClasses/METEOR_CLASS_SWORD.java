package com.raptorbk.CyanWarriorSwordsRedux.SwordClasses;

import com.raptorbk.CyanWarriorSwordsRedux.SWORD_CWSR;
import net.minecraft.entity.projectile.EntityLargeFireball;

public class METEOR_CLASS_SWORD extends SWORD_CWSR {
    public METEOR_CLASS_SWORD(String name, ToolMaterial material) {
        super(name, material);
    }

    public boolean delayMeteor=false;
    public EntityLargeFireball delayedMeteor=null;

    public void setDelayMeteor(boolean delayMeteor) {
        this.delayMeteor = delayMeteor;
    }

    public boolean getDelayMeteor(){
        return this.delayMeteor;
    }


    public EntityLargeFireball getDelayedMeteor() {
        return this.delayedMeteor;
    }

    public void setDelayedMeteor(EntityLargeFireball delayedMeteor) {
        this.delayedMeteor = delayedMeteor;
    }
}
