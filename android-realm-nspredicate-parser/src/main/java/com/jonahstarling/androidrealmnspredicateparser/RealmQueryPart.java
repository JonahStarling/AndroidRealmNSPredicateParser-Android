package com.jonahstarling.androidrealmnspredicateparser;

import java.util.List;

import io.realm.RealmObject;

public class RealmQueryPart <T extends RealmObject> {

    public NSPredicateEnum predicateEnum;
    public List variables;

    public RealmQueryPart(NSPredicateEnum predicateEnum, List variables) {
        this.predicateEnum = predicateEnum;
        this.variables = variables;
    }

}
