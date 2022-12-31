package com.raptorbk.CyanWarriorSwordsRedux;

import com.raptorbk.CyanWarriorSwordsRedux.util.RegistryHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class ABILITY_TOTEM extends Item {

    public ABILITY_TOTEM() {
        super(new Item.Properties().tab(CyanWarriorSwordsReduxMod.TAB));
    }

    public ActionResult<ItemStack> use(World world, PlayerEntity entity, Hand handIn) {
        ItemStack x=entity.getItemInHand(handIn);
        Map<Enchantment, Integer> xEnc= EnchantmentHelper.getEnchantments(x);

        if(xEnc.containsKey(RegistryHandler.inh_ENCHANTMENT.get())) {
            xEnc.remove(RegistryHandler.inh_ENCHANTMENT.get());
            EnchantmentHelper.setEnchantments(xEnc,x);
            ITextComponent chatMessage=new TranslationTextComponent("chat.cwsr.usage.deactivation.inh");
            if(!world.isClientSide()) {
                entity.sendMessage(chatMessage,entity.getUUID());
            }
        }else{
            ITextComponent chatMessage=new TranslationTextComponent("chat.cwsr.usage.activation.inh");
            if(!world.isClientSide()) {
                entity.sendMessage(chatMessage,entity.getUUID());
            }
            x.enchant(RegistryHandler.inh_ENCHANTMENT.get(),1);
        }
        return new ActionResult<>(ActionResultType.SUCCESS, x);
    }




    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.cwsr.ability_totem"));
    }

}
