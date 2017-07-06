/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leo.primeface2.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author sf
 */
@Embeddable
public class TranslationMapPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NAME_LAT")
    private String nameLat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "LOCALE")
    private String locale;

    public TranslationMapPK() {
    }

    public TranslationMapPK(String nameLat, String locale) {
        this.nameLat = nameLat;
        this.locale = locale;
    }

    public String getNameLat() {
        return nameLat;
    }

    public void setNameLat(String nameLat) {
        this.nameLat = nameLat;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nameLat != null ? nameLat.hashCode() : 0);
        hash += (locale != null ? locale.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TranslationMapPK)) {
            return false;
        }
        TranslationMapPK other = (TranslationMapPK) object;
        if ((this.nameLat == null && other.nameLat != null) || (this.nameLat != null && !this.nameLat.equals(other.nameLat))) {
            return false;
        }
        if ((this.locale == null && other.locale != null) || (this.locale != null && !this.locale.equals(other.locale))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.leo.primeface2.entities.TranslationMapPK[ nameLat=" + nameLat + ", locale=" + locale + " ]";
    }
    
}
