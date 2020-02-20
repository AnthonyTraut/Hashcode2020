package com.hashcode;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Section {
    
    private final List<Integer> information;
    private final List<Integer> ids;
    
    public Section(final List<Integer> information, final List<Integer> ids) {
        this.information = information;
        this.ids = ids;
    }
}
