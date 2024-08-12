package com.example.library.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "books")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column
  private String title;
  @Column
  private String author;
}