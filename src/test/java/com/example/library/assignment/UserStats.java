package com.example.library.assignment;

import java.util.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserStats {

  private Optional<Long> visitCount;
}
