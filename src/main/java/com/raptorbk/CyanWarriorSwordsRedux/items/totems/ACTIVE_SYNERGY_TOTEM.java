package com.raptorbk.CyanWarriorSwordsRedux.items.totems;

import com.raptorbk.CyanWarriorSwordsRedux.Main;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModEnchantments;
import com.raptorbk.CyanWarriorSwordsRedux.init.ModItems;
import com.raptorbk.CyanWarriorSwordsRedux.util.IHasModel;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.Map;

public class ACTIVE_SYNERGY_TOTEM extends Item implements IHasModel{

    public ACTIVE_SYNERGY_TOTEM(String name)
    {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(ModItems.cyanWarriorTab);

        ModItems.ITEMS.add(this);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack x=playerIn.getHeldItem(handIn);
        Map<Enchantment, Integer> xEnc= EnchantmentHelper.getEnchantments(x);
        if(playerIn.isSneaking()){
            if(xEnc.containsKey(ModEnchantments.InhEnchantment)){
                xEnc.remove(ModEnchantments.InhEnchantment);
                EnchantmentHelper.setEnchantments(xEnc,x);
                ITextComponent chatMessage=new TextComponentTranslation("chat.usage.deactivation.inh");
                if(!worldIn.isRemote){
                    playerIn.sendMessage(chatMessage);
                }
            }else{
                ITextComponent chatMessage=new TextComponentTranslation("chat.usage.activation.inh");
                if(!worldIn.isRemote){
                    playerIn.sendMessage(chatMessage);
                }
                x.addEnchantment(ModEnchantments.InhEnchantment,1);
            }
            playerIn.getCooldownTracker().setCooldown(x.getItem(), 40);
            return new ActionResult<>(EnumActionResult.SUCCESS, x);
        }else{
            ItemStack synergy_totemStack = new ItemStack(ModItems.SynergyTotem,1);

            if(xEnc.containsKey(ModEnchantments.InhEnchantment)){
                synergy_totemStack.addEnchantment(ModEnchantments.InhEnchantment,1);
            }


            ITextComponent chatMessage=new TextComponentTranslation("chat.usage.deactivation.dw");
            if(!worldIn.isRemote){
                playerIn.sendMessage(chatMessage);
            }

            playerIn.getCooldownTracker().setCooldown(synergy_totemStack.getItem(), 40);
            return new ActionResult<>(EnumActionResult.SUCCESS, synergy_totemStack);
        }
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this,0,"inventory");
    }

}
