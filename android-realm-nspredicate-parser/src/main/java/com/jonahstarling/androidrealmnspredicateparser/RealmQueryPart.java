package com.jonahstarling.androidrealmnspredicateparser;

import java.util.ArrayList;

import io.realm.RealmObject;

public class RealmQueryPart <T extends RealmObject> {

    public NSPredicateEnum predicateEnum;
    public ArrayList variables;

    public RealmQueryPart(NSPredicateEnum predicateEnum, ArrayList variables) {
        this.predicateEnum = predicateEnum;
        this.variables = variables;
    }

}
