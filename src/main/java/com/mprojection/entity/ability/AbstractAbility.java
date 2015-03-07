package com.mprojection.entity.ability;

import com.mprojection.entity.Translation;

/**
 * Created by Дмитрий on 2/22/2015.
 */
public abstract class AbstractAbility {

    private short id;
    private Translation nameTranslation;


    protected abstract void setNameTranslation();

}
