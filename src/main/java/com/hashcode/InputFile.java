package com.hashcode;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputFile {
    
    private List<Integer> information;
    private List<Integer> scores;
    private List<Section> sections = new ArrayList<>();
}
