package com.example.library.assignment;

import static org.assertj.core.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.*;

@SuppressWarnings("unchecked")
public class VisitorCounterTest {

  private VisitorCounter underTest;

  @BeforeEach
  public void setup() {
    underTest = new VisitorCounter();
  }

  @Test
  void test_count_null_array() {
    final Map<Long, Long> actual = underTest.count(null);
    assertThat(actual).isEmpty();
  }

  @Test
  void test_count_empty_maps() {
    final Map<String, UserStats>[] input = new Map[] {Map.of(), Map.of()};
    final Map<Long, Long> actual = underTest.count(input);
    assertThat(actual).isEmpty();
  }

  @Test
  void test_count_map_key_not_long() {
    final Map<String, UserStats>[] input = new Map[] {Map.of("not-long", UserStats.builder().visitCount(Optional.of(34L)).build()), Map.of()};
    final Map<Long, Long> actual = underTest.count(input);
    assertThat(actual).isEmpty();
  }

  @Test
  void test_count_map_key_user_stats_visit_count_absent() {
    final Map<String, UserStats>[] input = new Map[] {Map.of("123", UserStats.builder().visitCount(Optional.empty()).build()), Map.of()};
    final Map<Long, Long> actual = underTest.count(input);
    assertThat(actual).isEmpty();
  }

}
