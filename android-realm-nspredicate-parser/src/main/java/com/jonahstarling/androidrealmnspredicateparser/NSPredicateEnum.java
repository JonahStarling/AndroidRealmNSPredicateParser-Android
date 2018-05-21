package com.jonahstarling.androidrealmnspredicateparser;

public enum NSPredicateEnum {
    // Basic Comparisons
    EQUAL_TO                    (new String[]{"==", "="}, 2),
    NOT_EQUAL_TO                (new String[]{"!=", "<>"}, 2),
    IN                          (new String[]{"IN"}, 2),
    GREATER_THAN                (new String[]{">"}, 2),
    LESS_THAN                   (new String[]{"<"}, 2),
    GREATER_THAN_OR_EQUAL_TO    (new String[]{">=", "=>"}, 2),
    LESS_THAN_OR_EQUAL_TO       (new String[]{"<=", "=<"}, 2),

    // Basic Compound Predicates
    AND                         (new String[]{"AND", "&&"}, 0),
    OR                          (new String[]{"OR", "||"}, 0),
    NOT                         (new String[]{"NOT", "!"}, 0),

    // String Comparison Operators
    BEGINS_WITH                 (new String[]{"BEGINSWITH"}, 2),
    ENDS_WITH                   (new String[]{"ENDSWITH"}, 2),
    CONTAINS                    (new String[]{"CONTAINS"}, 2),
    LIKE                        (new String[]{"LIKE"}, 2),

    // Aggregate Operators
    // These will probably be the hardest to implement if I was to guess
    ANY                         (new String[]{"ANY"}, 3),
    SOME                        (new String[]{"SOME"}, 3),
    ALL                        (new String[]{"ALL"}, 3),

    // Parenthesis
    BEGIN_GROUP                 (new String[]{"("}, 0),
    END_GROUP                   (new String[]{")"}, 0);

    private final String[] predicateValue;
    private final int piecesNeeded;

    NSPredicateEnum(String[] predicateValue, int piecesNeeded) {
        this.predicateValue = predicateValue;
        this.piecesNeeded = piecesNeeded;
    }

    public String[] getPredicateValue() {
        return predicateValue;
    }

    public int getPiecesNeeded() {
        return piecesNeeded;
    }
}
