package org.example.springteamproject.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
