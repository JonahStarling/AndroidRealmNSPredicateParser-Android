package com.jonahstarling.androidrealmnspredicateparser;

import android.util.Log;
import java.util.Date;

import io.realm.RealmQuery;

public class NSPredicateLogic {

    public static void equalToLogic(RealmQuery realmQuery, RealmQueryPart realmQueryPart) {
        Object var0 = realmQueryPart.variables.get(0);
        Object var1 = realmQueryPart.variables.get(1);
        if (var0 instanceof String) {
            if (var1 instanceof String) {
                realmQuery.equalTo((String) var0, (String) var1);
            } else if (var1 instanceof Boolean) {
                realmQuery.equalTo((String) var0, (Boolean) var1);
            } else if (var1 instanceof Double) {
                realmQuery.equalTo((String) var0, (Double) var1);
            } else if (var1 instanceof Float) {
                realmQuery.equalTo((String) var0, (Float) var1);
            } else if (var1 instanceof Long) {
                realmQuery.equalTo((String) var0, (Long) var1);
            } else if (var1 instanceof Integer) {
                realmQuery.equalTo((String) var0, (Integer) var1);
            } else if (var1 instanceof Date) {
                realmQuery.equalTo((String) var0, (Date) var1); // TODO: Currently not supported
            }
        } else {
            Log.w("Failed Query Build", "failed in equal_to: " + realmQueryPart.toString());
        }
    }

    public static void notEqualToLogic(RealmQuery realmQuery, RealmQueryPart realmQueryPart) {
        Object var0 = realmQueryPart.variables.get(0);
        Object var1 = realmQueryPart.variables.get(1);
        if (var0 instanceof String) {
            if (var1 instanceof String) {
                realmQuery.notEqualTo((String) var0, (String) var1);
            } else if (var1 instanceof Boolean) {
                realmQuery.notEqualTo((String) var0, (Boolean) var1);
            } else if (var1 instanceof Double) {
                realmQuery.notEqualTo((String) var0, (Double) var1);
            } else if (var1 instanceof Float) {
                realmQuery.notEqualTo((String) var0, (Float) var1);
            } else if (var1 instanceof Long) {
                realmQuery.notEqualTo((String) var0, (Long) var1);
            } else if (var1 instanceof Integer) {
                realmQuery.notEqualTo((String) var0, (Integer) var1);
            } else if (var1 instanceof Date) {
                realmQuery.notEqualTo((String) var0, (Date) var1); // TODO: Currently not supported
            }
        } else {
            Log.w("Failed Query Build", "failed in not_equal_to: " + realmQueryPart.toString());
        }
    }

    public static void inLogic(RealmQuery realmQuery, RealmQueryPart realmQueryPart) {
        Object var0 = realmQueryPart.variables.get(0);
        Object var1 = realmQueryPart.variables.get(1);
        if (var0 instanceof String) {
            if (var1 instanceof String[]) {
                realmQuery.in((String) var0, (String[]) var1);
            } else if (var1 instanceof Boolean[]) {
                realmQuery.in((String) var0, (Boolean[]) var1);
            } else if (var1 instanceof Double[]) {
                realmQuery.in((String) var0, (Double[]) var1);
            } else if (var1 instanceof Float[]) {
                realmQuery.in((String) var0, (Float[]) var1);
            } else if (var1 instanceof Long[]) {
                realmQuery.in((String) var0, (Long[]) var1);
            } else if (var1 instanceof Integer[]) {
                realmQuery.in((String) var0, (Integer[]) var1);
            } else if (var1 instanceof Date[]) {
                realmQuery.in((String) var0, (Date[]) var1); // TODO: Currently not supported
            }
        } else {
            Log.w("Failed Query Build", "failed in not_equal_to: " + realmQueryPart.toString());
        }
    }

    public static void greaterThanLogic(RealmQuery realmQuery, RealmQueryPart realmQueryPart) {
        Object var0 = realmQueryPart.variables.get(0);
        Object var1 = realmQueryPart.variables.get(1);
        if (var0 instanceof String) {
            if (var1 instanceof Float) {
                realmQuery.greaterThan((String) var0, (Float) var1);
            } else if (var1 instanceof Double) {
                realmQuery.greaterThan((String) var0, (Double) var1);
            } else if (var1 instanceof Long) {
                realmQuery.greaterThan((String) var0, (Long) var1);
            } else if (var1 instanceof Integer) {
                realmQuery.greaterThan((String) var0, (Integer) var1);
            } else if (var1 instanceof Date) {
                realmQuery.greaterThan((String) var0, (Date) var1); // TODO: Currently not supported
            }
        } else {
            Log.w("Failed Query Build", "failed in greater_than: " + realmQueryPart.toString());
        }
    }

    public static void lessThanLogic(RealmQuery realmQuery, RealmQueryPart realmQueryPart) {
        Object var0 = realmQueryPart.variables.get(0);
        Object var1 = realmQueryPart.variables.get(1);
        if (var0 instanceof String) {
            if (var1 instanceof Float) {
                realmQuery.lessThan((String) var0, (Float) var1);
            } else if (var1 instanceof Double) {
                realmQuery.lessThan((String) var0, (Double) var1);
            } else if (var1 instanceof Long) {
                realmQuery.lessThan((String) var0, (Long) var1);
            } else if (var1 instanceof Integer) {
                realmQuery.lessThan((String) var0, (Integer) var1);
            } else if (var1 instanceof Date) {
                realmQuery.lessThan((String) var0, (Date) var1); // TODO: Currently not supported
            }
        } else {
            Log.w("Failed Query Build", "failed in less_than: " + realmQueryPart.toString());
        }
    }

    public static void greaterThanOrEqualToLogic(RealmQuery realmQuery, RealmQueryPart realmQueryPart) {
        Object var0 = realmQueryPart.variables.get(0);
        Object var1 = realmQueryPart.variables.get(1);
        if (var0 instanceof String) {
            if (var1 instanceof Float) {
                realmQuery.greaterThanOrEqualTo((String) var0, (Float) var1);
            } else if (var1 instanceof Double) {
                realmQuery.greaterThanOrEqualTo((String) var0, (Double) var1);
            } else if (var1 instanceof Long) {
                realmQuery.greaterThanOrEqualTo((String) var0, (Long) var1);
            } else if (var1 instanceof Integer) {
                realmQuery.greaterThanOrEqualTo((String) var0, (Integer) var1);
            } else if (var1 instanceof Date) {
                realmQuery.greaterThanOrEqualTo((String) var0, (Date) var1); // TODO: Currently not supported
            }
        } else {
            Log.w("Failed Query Build", "failed in greater_than_or_equal_to: " + realmQueryPart.toString());
        }
    }

    public static void lessThanOrEqualToLogic(RealmQuery realmQuery, RealmQueryPart realmQueryPart) {
        Object var0 = realmQueryPart.variables.get(0);
        Object var1 = realmQueryPart.variables.get(1);
        if (var0 instanceof String) {
            if (var1 instanceof Float) {
                realmQuery.lessThanOrEqualTo((String) var0, (Float) var1);
            } else if (var1 instanceof Double) {
                realmQuery.lessThanOrEqualTo((String) var0, (Double) var1);
            } else if (var1 instanceof Long) {
                realmQuery.lessThanOrEqualTo((String) var0, (Long) var1);
            } else if (var1 instanceof Integer) {
                realmQuery.lessThanOrEqualTo((String) var0, (Integer) var1);
            } else if (var1 instanceof Date) {
                realmQuery.lessThanOrEqualTo((String) var0, (Date) var1); // TODO: Currently not supported
            }
        } else {
            Log.w("Failed Query Build", "failed in less_than_or_equal_to: " + realmQueryPart.toString());
        }
    }

    public static void andLogic(RealmQuery realmQuery) {
        realmQuery.and();
    }

    public static void orLogic(RealmQuery realmQuery) {
        realmQuery.or();
    }

    public static void notLogic(RealmQuery realmQuery) {
        realmQuery.not();
    }

    public static void beginsWithLogic(RealmQuery realmQuery, RealmQueryPart realmQueryPart) {
        Object var0 = realmQueryPart.variables.get(0);
        Object var1 = realmQueryPart.variables.get(1);
        if (var0 instanceof String) {
            if (var1 instanceof String) {
                realmQuery.beginsWith((String) var0, (String) var1);
            }
        } else {
            Log.w("Failed Query Build", "failed in begins_with: " + realmQueryPart.toString());
        }
    }

    public static void endsWithLogic(RealmQuery realmQuery, RealmQueryPart realmQueryPart) {
        Object var0 = realmQueryPart.variables.get(0);
        Object var1 = realmQueryPart.variables.get(1);
        if (var0 instanceof String) {
            if (var1 instanceof String) {
                realmQuery.endsWith((String) var0, (String) var1);
            }
        } else {
            Log.w("Failed Query Build", "failed in ends_with: " + realmQueryPart.toString());
        }
    }

    public static void containsLogic(RealmQuery realmQuery, RealmQueryPart realmQueryPart) {
        Object var0 = realmQueryPart.variables.get(0);
        Object var1 = realmQueryPart.variables.get(1);
        if (var0 instanceof String) {
            if (var1 instanceof String) {
                realmQuery.contains((String) var0, (String) var1);
            }
        } else {
            Log.w("Failed Query Build", "failed in contains: " + realmQueryPart.toString());
        }
    }

    public static void likeLogic(RealmQuery realmQuery, RealmQueryPart realmQueryPart) {
        Object var0 = realmQueryPart.variables.get(0);
        Object var1 = realmQueryPart.variables.get(1);
        if (var0 instanceof String) {
            if (var1 instanceof String) {
                realmQuery.like((String) var0, (String) var1);
            }
        } else {
            Log.w("Failed Query Build", "failed in like: " + realmQueryPart.toString());
        }
    }

    public static void anyLogic(RealmQuery realmQuery, RealmQueryPart realmQueryPart) {

    }

    public static void someLogic(RealmQuery realmQuery, RealmQueryPart realmQueryPart) {

    }

    public static void beginGroupLogic(RealmQuery realmQuery) {
        realmQuery.beginGroup();
    }

    public static void endGroupLogic(RealmQuery realmQuery) {
        realmQuery.endGroup();
    }


}
