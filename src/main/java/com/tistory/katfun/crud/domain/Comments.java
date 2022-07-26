package com.tistory.katfun.crud.domain;

import lombok.Builder;
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
    private Long postId;

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

    @Builder
    public Comments(Long postId, String createId, String content, Date lastEditTime, Boolean secretYn, Boolean editedYn) {
        this.postId = postId;
        this.createId = createId;
        this.content = content;
        this.lastEditTime = lastEditTime;
        this.secretYn = secretYn;
        this.editedYn = editedYn;
    }

    public void update(String content) {
        this.content = content;
        this.lastEditTime = new Date(System.currentTimeMillis());
        this.editedYn = true;
    }
}
