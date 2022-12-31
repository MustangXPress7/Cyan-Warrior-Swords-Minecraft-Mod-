package com.raptorbk.CyanWarriorSwordsRedux.customadv;


import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class Sworddestroyedtrigger extends SimpleCriterionTrigger<Sworddestroyedtrigger.Instance> {
    private static final ResourceLocation ID = new ResourceLocation("cwsr","sword_destroyed_trigger");

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    protected Sworddestroyedtrigger.Instance createInstance(JsonObject json, EntityPredicate.Composite entityPredicate, DeserializationContext conditionsParser) {
        return new Sworddestroyedtrigger.Instance(entityPredicate);
    }

    public void trigger(ServerPlayer entity) {
        this.trigger(entity, (instance) -> {
            return instance.test();
        });
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        public Instance(EntityPredicate.Composite player) {
            super(Sworddestroyedtrigger.ID, player);
        }

        public boolean test() {
            return true;
        }
    }
}