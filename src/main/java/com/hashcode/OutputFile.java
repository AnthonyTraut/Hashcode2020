package com.hashcode;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutputFile {
    
    private Integer number;
    private List<Section> descriptions;
    
    public OutputFile(final Integer number, final List<Section> descriptions) {
        this.number = number;
        this.descriptions = descriptions;
    }
}
