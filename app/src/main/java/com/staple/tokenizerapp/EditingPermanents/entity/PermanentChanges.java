package com.staple.tokenizerapp.EditingPermanents.entity;

import com.staple.tokenizerapp.PickingDecks.entity.Card;

import java.util.List;

public class PermanentChanges {
    private Long permId;
    private Integer mode;
    private String newName;
    private String newPicUrl;
    private List<Card> bonds;

    public Long getPermId() {
        return permId;
    }

    public Integer getMode() {
        return mode;
    }

    public String getNewName() {
        return newName;
    }

    public String getNewPicUrl() {
        return newPicUrl;
    }

    public List<Card> getBonds() {
        return bonds;
    }

    public void setPermId(Long permId) {
        this.permId = permId;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public void setNewPicUrl(String newPicUrl) {
        this.newPicUrl = newPicUrl;
    }

    public void setBonds(List<Card> bonds) {
        this.bonds = bonds;
    }
}
