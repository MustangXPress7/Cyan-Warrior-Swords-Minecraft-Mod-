package com.cyanwarriorswords.cyanwarriorswordsmod.Swords;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemwildNatureSword extends ItemSword{

	public ItemwildNatureSword(int par1, ToolMaterial par2EnumToolMaterial) {
		super(par2EnumToolMaterial);
		this.setMaxDamage(3000);
		this.setMaxStackSize(1);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{  
	        this.itemIcon = ir.registerIcon("CyanWarriorSwords:WildNatureSword");
	}
	
	public boolean isFull3D()
    {
        return true;
    }
public int getItemEnchantability()
{
    return 20;
}
public int getMaxItemUseDuration(ItemStack par1ItemStack)
{
    return 200000;
}
public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
{
    return 1.5F;
}
public int getDamageVsEntity(Entity par1Entity)
{
    return 5;
}
public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entity){
float var4 = 1.0F;
int i = (int)(entity.prevPosX + (entity.posX - entity.prevPosX) * (double)var4);
int j = (int)(entity.prevPosY + (entity.posY - entity.prevPosY) * (double)var4 + 1.62D - (double)entity.yOffset);
int k = (int)(entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)var4);

MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, entity, true);

if (movingobjectposition == null)
{
    return itemstack;
}
else
{
    if (movingobjectposition.typeOfHit == MovingObjectPosition
            .MovingObjectType.BLOCK)
    {
        int x = movingobjectposition.blockX;
        int y = movingobjectposition.blockY;
        int z = movingobjectposition.blockZ;

        if (!world.canMineBlock(entity, x, y, z))
        {
            return itemstack;
        }

        if (!entity.canPlayerEdit(x, y, z, movingobjectposition.sideHit, itemstack))
        {
            return itemstack;
        }

        if (world.isAirBlock(x+1, y+2, z-1))
        {
            world.setBlock(x+1, y+2, z-1, Blocks.cactus);
        }
        if (world.isAirBlock(x-3, y+2, z-1))
        {
           world.setBlock(x-3, y+2, z-1, Blocks.cactus); 
        }
        if (world.isAirBlock(x-1, y+2, z+1))
        {
            world.setBlock(x-1, y+2, z+1, Blocks.cactus);
        }
        if (world.isAirBlock(x-1, y+2, z-3))
        {
            world.setBlock(x-1, y+2, z-3, Blocks.cactus);
        }
        if (world.isAirBlock(x+1, y+1, z-1))
        {
            world.setBlock(x+1, y+1, z-1, Blocks.cactus);
        }
        if (world.isAirBlock(x-3, y+1, z-1))
        {
            world.setBlock(x-3, y+1, z-1, Blocks.cactus);
        }
        if (world.isAirBlock(x-1, y+1, z+1))
        {
            world.setBlock(x-1, y+1, z+1, Blocks.cactus);
        }
        if (world.isAirBlock(x-1, y+1, z-3))
        {
            world.setBlock(x-1, y+1, z-3, Blocks.cactus);
        }
    }    
    }
entity.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 0));

entity.fallDistance = 0.0F;

itemstack.damageItem(25, entity);

return itemstack;
}
public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
{
         par2EntityLivingBase.addPotionEffect(new PotionEffect(Potion.poison.id, 1200, 4));
         par1ItemStack.damageItem(1, par3EntityLivingBase);

return true;
}
public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack)
{
    return true;
}

}
