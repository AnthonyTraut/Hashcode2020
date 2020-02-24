package com.hashcode.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InputFile {

    private List<Integer> information;
    private List<Integer> scores;
    private List<Section> sections = new ArrayList<>();
}
