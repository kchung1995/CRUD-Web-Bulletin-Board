package com.tistory.katfun.crud.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String postId;

    @Column(length = 100, nullable = false)
    private String createId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private Date lastEditTime;

    @Type(type="yes_no")
    @Column(length = 1)
    private Boolean secretYn;

    @Type(type="yes_no")
    @Column(length = 1)
    private Boolean editedYn;

}
