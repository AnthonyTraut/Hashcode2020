package com.hashcode.model;

import java.util.List;

import lombok.Getter;

@Getter
public class AlgoInfo {
    private int nbBooks;
    private int nbLib;
    private int nbDays;
    
    public AlgoInfo(List<Integer> informations) {
        nbBooks = informations.get(0);
        nbLib = informations.get(1);
        nbDays = informations.get(2);
    }
}
