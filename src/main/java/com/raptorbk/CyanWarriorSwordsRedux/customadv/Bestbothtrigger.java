package com.raptorbk.CyanWarriorSwordsRedux.customadv;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class Bestbothtrigger extends SimpleCriterionTrigger<Bestbothtrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation("cwsr","best_both_trigger");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    protected Bestbothtrigger.Instance createInstance(JsonObject json, EntityPredicate.Composite entityPredicate, DeserializationContext conditionsParser) {
        return new Bestbothtrigger.Instance(entityPredicate);
    }

    public void trigger(ServerPlayer entity) {
        this.trigger(entity, (instance) -> {
            return instance.test();
        });
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        public Instance(EntityPredicate.Composite player) {
            super(Bestbothtrigger.ID, player);
        }

        public boolean test() {
            return true;
        }
    }
}