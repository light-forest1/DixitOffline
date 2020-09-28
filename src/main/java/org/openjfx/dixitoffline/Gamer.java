/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjfx.dixitoffline;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author v-user
 */
public class Gamer {
    private String gamerName;
    private ArrayList<String> gamerImages;
    private int gamerCurrentImageNum;
    private String imageAssociation;
    private ArrayList<String> votedGamers;
    private int allPoints;
    private int currentPoints;
    
    public Gamer (String gamerName, ArrayList<String> gamerImages, int gamerCurrentImageNum, 
            String imageAssociation, ArrayList<String> votedGamers, int allPoints, int currentPoints) {
        this.gamerName = gamerName;
        this.gamerImages = gamerImages;
        this.gamerCurrentImageNum = gamerCurrentImageNum;
        this.imageAssociation = imageAssociation;
        this.votedGamers = votedGamers;
        this.allPoints = allPoints;
        this.currentPoints = currentPoints;
    }
    
    public String getGamerName() {
        return gamerName;
    }
    
    public void setGamerName(String gamerName) {
        this.gamerName = gamerName;
    }
    
    public ArrayList<String> getGamerImages() {
        return gamerImages;
    }
    
    public void setGamerImages(ArrayList<String> gamerImages) {
        this.gamerImages = gamerImages;
    }
    
    public int getGamerCurrentImageNum() {
        return gamerCurrentImageNum;
    }
    
    public void setGamerCurrentImageNum(int gamerCurrentImageNum) {
        this.gamerCurrentImageNum = gamerCurrentImageNum;
    }
    
    public String getImageAssociation() {
        return imageAssociation;
    }
    
    public void setImageAssociation(String imageAssociation) {
        this.imageAssociation = imageAssociation;
    }
    
    public ArrayList<String> getVotedGamers() {
        return votedGamers;
    }
    
    public void setVotedGamers(ArrayList<String> votedGamers) {
        this.votedGamers = votedGamers;
    }
    
    public int getCurrentPoints() {
        return currentPoints;
    }
    
    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }
    
    public int getAllPoints() {
        return allPoints;
    }
    
    public void setAllPoints(int allPoints) {
        this.allPoints = allPoints;
    }
}
