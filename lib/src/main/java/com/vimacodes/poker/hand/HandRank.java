package com.vimacodes.poker.hand;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class HandRank {
    Category category;
    String specialName;
    // int rank;

    public HandRank(Category category) {
        this(category, null);
    }

    public String toPrettyPrint() {
        var name = (specialName == null) ? "" : " (" + specialName + ")";
        return category.toPrettyPrint() + name;
    }


}
