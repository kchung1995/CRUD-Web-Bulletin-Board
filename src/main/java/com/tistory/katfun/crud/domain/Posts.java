package com.tistory.katfun.crud.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(length = 100, nullable = false)
    private String category;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String createId;

    @Column(nullable = false)
    private Date createTime;

    @Column(nullable = false)
    private Date lastEditTime;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private int viewCount;

    @Builder
    public Posts(Long postId, String category, String title, String createId, Date createTime, Date lastEditTime, String content, int viewCount) {
        this.postId = postId;
        this.category = category;
        this.title = title;
        this.createId = createId;
        this.createTime = createTime;
        this.lastEditTime = lastEditTime;
        this.content = content;
        this.viewCount = viewCount;
    }

    public void update(String title, String content) {
        this.title = title;
        this.lastEditTime = new Date(System.currentTimeMillis());
        this.content = content;
    }

    public void delete() {

    }
}
