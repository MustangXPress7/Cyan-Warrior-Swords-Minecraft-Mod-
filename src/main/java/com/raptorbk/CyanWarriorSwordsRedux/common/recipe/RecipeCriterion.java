package com.raptorbk.CyanWarriorSwordsRedux.common.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;

public class RecipeCriterion {


    public final String name;
    public final CriterionTriggerInstance criterion;

    private RecipeCriterion(String name, CriterionTriggerInstance criterion)
    {
        this.name = name;
        this.criterion = criterion;
    }

    public static RecipeCriterion of(String name, CriterionTriggerInstance criterion)
    {
        return new RecipeCriterion(name, criterion);
    }
}
