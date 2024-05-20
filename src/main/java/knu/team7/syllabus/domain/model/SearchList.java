package knu.team7.syllabus.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SearchList implements Search{
    private String key;

    @Builder
    public SearchList(String key) {
        this.key = key;
    }

}
