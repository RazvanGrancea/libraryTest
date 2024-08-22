package com.example.library.assignment;

import java.util.*;
import java.util.stream.*;

public class VisitorCounter {

  public Map<Long, Long> count(final Map<String, UserStats>[] stats) {
    if (stats == null) {
      return Map.of(); // input array is null
    }

    return Arrays.stream(stats)
        .filter(Objects::nonNull) // filter out null maps (null elements from the array)
        .filter(map -> !map.isEmpty()) // filter out maps that are empty
        .flatMap(map -> map.entrySet().stream())  // flatten the list of maps into a stream of entries
        .filter(entry -> isParsableToLong(entry.getKey())) // filter out String keys that are not parseable to Longs
        .filter(entry -> entry.getValue() != null) // filter out UserStats values that are null
        .filter(entry -> entry.getValue().getVisitCount().isPresent()) // filter out UserStats values that are absent
        .collect(Collectors.toMap(
            entry -> Long.parseLong(entry.getKey()), // map String keys to Long, these are the final user ids longs
            entry -> entry.getValue().getVisitCount().get(), // map UserStats to visitCount Long value
            Long::sum // Sum the counts for duplicate keys (for the same userId). This is the duplicate key remapping function.
        ));
  }

  private boolean isParsableToLong(final String key) {
    try {
      Long.parseLong(key);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
