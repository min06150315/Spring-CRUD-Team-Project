package org.example.springteamproject.vo;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProblemVO {
    private int id;
    private String title;
    private String description;
    private String created_at;
    private String language;
    private String filePath;
    private String difficulty;
    private String writer;
}
