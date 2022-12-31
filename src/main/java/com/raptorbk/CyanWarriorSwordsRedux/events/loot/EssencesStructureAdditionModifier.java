package com.raptorbk.CyanWarriorSwordsRedux.events.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.common.util.JsonUtils;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;

public class EssencesStructureAdditionModifier extends LootModifier {
    private final Item addition;

    public EssencesStructureAdditionModifier(LootItemCondition[] conditionsIn, Item addition) {
        super(conditionsIn);
        this.addition = addition;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(addition.getDefaultInstance());
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<EssencesStructureAdditionModifier> {

        @Override
        public EssencesStructureAdditionModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn) {
            Item addition = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(GsonHelper.getAsString(object, "item")));
            return new EssencesStructureAdditionModifier(conditionsIn, addition);
        }

        @Override
        public JsonObject write(EssencesStructureAdditionModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("item", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            return json;
        }
    }
}