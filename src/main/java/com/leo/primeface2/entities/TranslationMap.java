/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leo.primeface2.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sf
 */
@Entity
@Table(name = "TRANSLATION_MAP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TranslationMap.findAll", query = "SELECT t FROM TranslationMap t"),
    @NamedQuery(name = "TranslationMap.findByNameLat", query = "SELECT t FROM TranslationMap t WHERE t.translationMapPK.nameLat = :nameLat"),
    @NamedQuery(name = "TranslationMap.findByNameTranslation", query = "SELECT t FROM TranslationMap t WHERE t.nameTranslation = :nameTranslation"),
    @NamedQuery(name = "TranslationMap.findByLocale", query = "SELECT t FROM TranslationMap t WHERE t.translationMapPK.locale = :locale")})
public class TranslationMap implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TranslationMapPK translationMapPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NAME_TRANSLATION")
    private String nameTranslation;

    public TranslationMap() {
    }

    public TranslationMap(TranslationMapPK translationMapPK) {
        this.translationMapPK = translationMapPK;
    }

    public TranslationMap(TranslationMapPK translationMapPK, String nameTranslation) {
        this.translationMapPK = translationMapPK;
        this.nameTranslation = nameTranslation;
    }

    public TranslationMap(String nameLat, String locale) {
        this.translationMapPK = new TranslationMapPK(nameLat, locale);
    }

    public TranslationMapPK getTranslationMapPK() {
        return translationMapPK;
    }

    public void setTranslationMapPK(TranslationMapPK translationMapPK) {
        this.translationMapPK = translationMapPK;
    }

    public String getNameTranslation() {
        return nameTranslation;
    }

    public void setNameTranslation(String nameTranslation) {
        this.nameTranslation = nameTranslation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (translationMapPK != null ? translationMapPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TranslationMap)) {
            return false;
        }
        TranslationMap other = (TranslationMap) object;
        if ((this.translationMapPK == null && other.translationMapPK != null) || (this.translationMapPK != null && !this.translationMapPK.equals(other.translationMapPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.leo.primeface2.entities.TranslationMap[ translationMapPK=" + translationMapPK + " ]";
    }
    
}
