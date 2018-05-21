package com.jonahstarling.androidrealmnspredicateparser;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;

public class NSPredicateParser <T extends RealmObject> {

    private String predicate;
    private ArrayList<RealmQueryPart> realmQueryParts;
    private ArrayList<Object> variableStack;
    private RealmQuery<T> realmQuery;
    private EnumSet<NSPredicateEnum> predicateEnums;

    public NSPredicateParser(Realm realm, String predicate, Class<T> objectClass, ArrayList<Object> variableStack) {
        this.predicate = predicate;
        this.variableStack = variableStack;
        this.realmQueryParts = new ArrayList<>();
        this.realmQuery = realm.where(objectClass);
        this.predicateEnums = EnumSet.allOf(NSPredicateEnum.class);
    }

    public RealmQuery<T> parsePredicate() {
        String[] predicateParts = this.predicate.split("\\s+");
        Boolean lookingForPieces = false;
        Boolean aggregateOperator = false;
        String uncaughtPiece = "";
        NSPredicateEnum currentPartPredicateEnum = null;
        ArrayList<Object> currentPartVariables = new ArrayList<>();
        int endGroupsToAdd;
        for (String predicatePart : predicateParts) {
            if (lookingForPieces) {
                lookingForPieces = false;
                endGroupsToAdd = checkForEndGroup(predicatePart);
                if (endGroupsToAdd != 0) {
                    predicatePart = predicatePart.substring(0, predicatePart.length() - endGroupsToAdd);
                }
                currentPartVariables.addAll(parseAndCastVariables(uncaughtPiece, predicatePart));
                if (aggregateOperator) {
                    this.realmQueryParts.add(new RealmQueryPart(NSPredicateEnum.ANY, currentPartVariables));
                    aggregateOperator = false;
                } else {
                    this.realmQueryParts.add(new RealmQueryPart(currentPartPredicateEnum, currentPartVariables));
                }
                addEndGroups(endGroupsToAdd);
                currentPartVariables = new ArrayList<>();
            } else {
                predicatePart = checkForBeginGroup(predicatePart);
                NSPredicateEnum predicateEnum = knownCommand(predicatePart);
                if (predicateEnum != null) {
                    if (aggregateOperator) {
                        currentPartVariables.add(predicateEnum);
                    } else {
                        currentPartPredicateEnum = predicateEnum;
                    }
                    if (predicateEnum.getPiecesNeeded() == 0) {
                        this.realmQueryParts.add(new RealmQueryPart(currentPartPredicateEnum, currentPartVariables));
                    } else if (predicateEnum.equals(NSPredicateEnum.ANY)) {
                        aggregateOperator = true;
                        currentPartPredicateEnum = NSPredicateEnum.ANY;
                    } else {
                        lookingForPieces = true;
                    }
                } else {
                    uncaughtPiece = predicatePart;
                }
            }
        }
        realmQuery = buildRealmQuery();
        return realmQuery;
    }

    private NSPredicateEnum knownCommand(String predicatePart) {
        NSPredicateEnum knownPredicateEnum = null;
        for (NSPredicateEnum predicateEnum : predicateEnums) {
            String[] predicateValues = predicateEnum.getPredicateValue();
            if (predicateValues[0].equals(predicatePart)) {
                knownPredicateEnum = predicateEnum;
                break; // I know using the break practice is bad but who cares, I'll refactor eventually
            } else if (predicateValues.length > 1 && predicateValues[1].equals(predicatePart)) {
                knownPredicateEnum = predicateEnum;
                break; // Same here
            }
        }
        return knownPredicateEnum;
    }

    private RealmQuery<T> buildRealmQuery() {
        for (RealmQueryPart realmQueryPart : this.realmQueryParts) {
            switch(realmQueryPart.predicateEnum) {
                case EQUAL_TO: {
                    NSPredicateLogic.equalToLogic(realmQuery, realmQueryPart);
                    break;
                }
                case NOT_EQUAL_TO: {
                    NSPredicateLogic.notEqualToLogic(realmQuery, realmQueryPart);
                    break;
                }
                case IN: {
                    NSPredicateLogic.inLogic(realmQuery, realmQueryPart);
                    break;
                }
                case GREATER_THAN: {
                    NSPredicateLogic.greaterThanLogic(realmQuery, realmQueryPart);
                    break;
                }
                case LESS_THAN: {
                    NSPredicateLogic.lessThanLogic(realmQuery, realmQueryPart);
                    break;
                }
                case GREATER_THAN_OR_EQUAL_TO: {
                    NSPredicateLogic.greaterThanOrEqualToLogic(realmQuery, realmQueryPart);
                    break;
                }
                case LESS_THAN_OR_EQUAL_TO: {
                    NSPredicateLogic.lessThanOrEqualToLogic(realmQuery, realmQueryPart);
                    break;
                }
                case AND: {
                    NSPredicateLogic.andLogic(realmQuery);
                    break;
                }
                case OR: {
                    NSPredicateLogic.orLogic(realmQuery);
                    break;
                }
                case NOT: {
                    NSPredicateLogic.notLogic(realmQuery);
                }
                case BEGINS_WITH: {
                    NSPredicateLogic.beginsWithLogic(realmQuery, realmQueryPart);
                    break;
                }
                case ENDS_WITH: {
                    NSPredicateLogic.endsWithLogic(realmQuery, realmQueryPart);
                    break;
                }
                case CONTAINS: {
                    NSPredicateLogic.containsLogic(realmQuery, realmQueryPart);
                    break;
                }
                case LIKE: {
                    NSPredicateLogic.likeLogic(realmQuery, realmQueryPart);
                    break;
                }
                case ANY: {
                    NSPredicateLogic.anyLogic(realmQuery, realmQueryPart);
                    break;
                }
                case SOME: {
                    NSPredicateLogic.someLogic(realmQuery, realmQueryPart);
                    break;
                }
                case BEGIN_GROUP: {
                    NSPredicateLogic.beginGroupLogic(realmQuery);
                    break;
                }
                case END_GROUP: {
                    NSPredicateLogic.endGroupLogic(realmQuery);
                    break;
                }
                default: {
                    Log.w("BuildRealmQuery", "defaulted: " + realmQueryPart.toString());
                }
            }
        }
        return realmQuery;
    }

    private ArrayList parseAndCastVariables(String var0, String var1) {
        if (var1.equals("%@")) {
            if (this.variableStack.size() >= 1) {
                ArrayList list = new ArrayList<>(Arrays.asList(var0, this.variableStack.get(0)));
                this.variableStack.remove(0);
                return list;
            }
        } else if (var1.startsWith("'") && var1.endsWith("'")) {
            var1 = var1.substring(1, var1.length()-1);
            return new ArrayList<>(Arrays.asList(var0, var1));
        } else if (var1.startsWith("\"") && var1.endsWith("\"")) {
            var1 = var1.substring(1, var1.length()-1);
            return new ArrayList<>(Arrays.asList(var0, var1));
        } else if (var1.equalsIgnoreCase("true")) {
            return new ArrayList<>(Arrays.asList(var0, true));
        } else if (var1.equalsIgnoreCase("false")) {
            return new ArrayList<>(Arrays.asList(var0, false));
        } else if (var1.matches("^[0-9]*$")) {
            try {
                Integer intVar1 = Integer.valueOf(var1);
                return new ArrayList<>(Arrays.asList(var0, intVar1));
            } catch (NumberFormatException e1) {
                try {
                    Long longVar1 = Long.valueOf(var1);
                    return new ArrayList<>(Arrays.asList(var0, longVar1));
                } catch (NumberFormatException e2) {
                    Log.w("UncaughtCast","Nothing to cast it to!");
                    e1.printStackTrace();
                    e2.printStackTrace();
                }
            }
        } else if (var1.matches("^[0-9]*[.][0-9]*$")) {
            try {
                Float floatVar1 = Float.valueOf(var1);
                return new ArrayList<>(Arrays.asList(var0, floatVar1));
            } catch (NumberFormatException e1) {
                try {
                    Double doubleVar1 = Double.valueOf(var1);
                    return new ArrayList<>(Arrays.asList(var0, doubleVar1));
                } catch (NumberFormatException e2) {
                    Log.w("UncaughtCast", "Nothing to cast it to!");
                    e1.printStackTrace();
                    e2.printStackTrace();
                }
            }
        }
        return new ArrayList<>(Arrays.asList(var0, var1));
    }

    private String checkForBeginGroup(String predicate) {
        Boolean beginGroupFound;
        do {
            if (predicate.startsWith("(")) {
                beginGroupFound = true;
                predicate = predicate.substring(1, predicate.length());
                realmQueryParts.add(new RealmQueryPart(NSPredicateEnum.BEGIN_GROUP, null));
            } else {
                beginGroupFound = false;
            }
        } while (beginGroupFound);
        return predicate;
    }

    private int checkForEndGroup(String predicate) {
        Boolean endGroupFound;
        int endGroupsToAdd = 0;
        do {
            if (predicate.endsWith(")")) {
                endGroupFound = true;
                predicate = predicate.substring(0, predicate.length()-1);
                endGroupsToAdd++;
            } else {
                endGroupFound = false;
            }
        } while (endGroupFound);
        return endGroupsToAdd;
    }

    private void addEndGroups(int endGroupsToAdd) {
        if (endGroupsToAdd != 0) {
            for (int i = 0; i < endGroupsToAdd; i++) {
                this.realmQueryParts.add(new RealmQueryPart(NSPredicateEnum.END_GROUP, null));
            }
        }
    }

}
