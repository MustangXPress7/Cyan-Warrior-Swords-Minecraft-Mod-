package com.raptorbk.CyanWarriorSwordsRedux.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.raptorbk.CyanWarriorSwordsRedux.CyanWarriorSwordsReduxMod;
import com.raptorbk.CyanWarriorSwordsRedux.blocks.impl.CyanWarriorSwordRecipeRegistrar;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.RecipeUnlockedTrigger;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class CyanWarriorSwordsRecipeBuilder<BUILDER extends CyanWarriorSwordsRecipeBuilder<BUILDER>> {
    protected static ResourceLocation bmSerializer(String name)
    {
        return new ResourceLocation(CyanWarriorSwordsReduxMod.MOD_ID, name);
    }

    protected final List<ICondition> conditions = new ArrayList<>();
    protected final Advancement.Builder advancementBuilder = Advancement.Builder.advancement();
    protected final ResourceLocation serializerName;


    protected CyanWarriorSwordsRecipeBuilder(ResourceLocation serializerName)
    {
        this.serializerName = serializerName;
    }

    public BUILDER addCriterion(RecipeCriterion criterion)
    {
        return addCriterion(criterion.name, criterion.criterion);
    }

    public BUILDER addCriterion(String name, ICriterionInstance criterion)
    {
        advancementBuilder.addCriterion(name, criterion);
        return (BUILDER) this;
    }


    public BUILDER addCondition(ICondition condition)
    {
        conditions.add(condition);
        return (BUILDER) this;
    }

    protected boolean hasCriteria()
    {
        return !advancementBuilder.getCriteria().isEmpty();
    }

    protected abstract RecipeResult getResult(ResourceLocation id);

    protected void validate(ResourceLocation id)
    {
    }

    public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation id)
    {
        validate(id);
        if (hasCriteria())
        {
            // If there is a way to "unlock" this recipe then add an advancement with the
            // criteria
            advancementBuilder.parent(new ResourceLocation("recipes/root")).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(IRequirementsStrategy.OR);
        }
        consumer.accept(getResult(id));
    }

    protected abstract class RecipeResult implements IFinishedRecipe {

        private final ResourceLocation id;

        public RecipeResult(ResourceLocation id) {
            this.id = id;
        }



        @Override
        public JsonObject serializeRecipe() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("type", serializerName.toString());
            if (!conditions.isEmpty()) {
                JsonArray conditionsArray = new JsonArray();
                for (ICondition condition : conditions) {
                    conditionsArray.add(CraftingHelper.serialize(condition));
                }
                jsonObject.add("conditions", conditionsArray);
            }
            this.serializeRecipeData(jsonObject);
            return jsonObject;
        }


        @Nonnull
        @Override
        public IRecipeSerializer<?> getType() {
            // Note: This may be null if something is screwed up but this method isn't
            // actually used so it shouldn't matter
            // and in fact it will probably be null if only the API is included. But again,
            // as we manually just use
            // the serializer's name this should not effect us
            return ForgeRegistries.RECIPE_SERIALIZERS.getValue(serializerName);
        }

        @Nonnull
        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return hasCriteria() ? advancementBuilder.serializeToJson() : null;
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return new ResourceLocation(id.getNamespace(), "recipes/" + id.getPath());
        }
    }
}
