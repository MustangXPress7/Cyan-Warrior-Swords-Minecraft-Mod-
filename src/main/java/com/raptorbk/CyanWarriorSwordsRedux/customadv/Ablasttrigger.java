package com.raptorbk.CyanWarriorSwordsRedux.customadv;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.raptorbk.CyanWarriorSwordsRedux.swords.Mixing.WIND_BLAST;
import com.raptorbk.CyanWarriorSwordsRedux.swords.Mixing.WIND_BOOM;
import net.minecraft.advancements.criterion.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.ResourceLocation;

public class Ablasttrigger extends AbstractCriterionTrigger<Ablasttrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation("cwsr","a_blast_trigger");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    protected Instance createInstance(JsonObject json, EntityPredicate.AndPredicate entityPredicate, ConditionArrayParser conditionsParser) {
        return new Ablasttrigger.Instance(entityPredicate);
    }

    public void trigger(ServerPlayerEntity entity) {
        this.trigger(entity, (instance) -> {
             return instance.test(entity.getMainHandItem().getItem(),entity.getOffhandItem().getItem());
        });
    }

    public static class Instance extends CriterionInstance {
        public Instance(EntityPredicate.AndPredicate player) {
            super(Ablasttrigger.ID, player);
        }

        public boolean test(Item mainI, Item offI) {
            if((mainI instanceof WIND_BOOM && offI instanceof WIND_BLAST) || (mainI instanceof WIND_BLAST && offI instanceof WIND_BOOM)){
                return true;
            }else{
                return false;
            }
        }
    }
}