package com.raptorbk.CyanWarriorSwordsRedux.customadv;

import com.google.gson.JsonObject;
import com.raptorbk.CyanWarriorSwordsRedux.swords.Mixing.WIND_BLAST;
import com.raptorbk.CyanWarriorSwordsRedux.swords.Mixing.WIND_BOOM;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;


public class Ablasttrigger extends SimpleCriterionTrigger<Ablasttrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation("cwsr","a_blast_trigger");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    protected Instance createInstance(JsonObject json, EntityPredicate.Composite entityPredicate, DeserializationContext conditionsParser) {
        return new Ablasttrigger.Instance(entityPredicate);
    }

    public void trigger(ServerPlayer entity) {
        this.trigger(entity, (instance) -> {
             return instance.test(entity.getMainHandItem().getItem(),entity.getOffhandItem().getItem());
        });
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        public Instance(EntityPredicate.Composite player) {
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