package com.back.entity;

import com.back.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseIdAndTime {
    @ManyToOne(fetch = LAZY)
    private Member author;
    private String title;
    private String content;
}
