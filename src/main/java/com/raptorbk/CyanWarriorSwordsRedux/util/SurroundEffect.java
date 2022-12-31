package com.raptorbk.CyanWarriorSwordsRedux.util;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public interface SurroundEffect {
    void execute(World world, EntityPlayer entity, EnumHand handIn, Block blk);

}
