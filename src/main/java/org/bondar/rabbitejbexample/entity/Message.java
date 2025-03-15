//package org.bondar.rabbitejbexample.entity;
//
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@Entity
//@Table(name = "messages")
//public class Message implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, length = 500)
//    private String content;
//
//    @Column(nullable = false)
//    private Boolean processedSuccessfully;
//}
