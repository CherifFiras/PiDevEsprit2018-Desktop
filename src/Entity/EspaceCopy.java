/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author hero
 */

public class EspaceCopy {

    private Integer id;
    private String titre;
    private String description;
    private String nom;
    private String prenom;
    private int idEsp;

    public EspaceCopy() {
    }

    public EspaceCopy(String titre, String description, String nom, String prenom, int idEsp) {
        this.titre = titre;
        this.description = description;
        this.nom = nom;
        this.prenom = prenom;
        this.idEsp = idEsp;
    }

    public EspaceCopy(Integer id, String titre, String description, String nom, String prenom, int idEsp) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.nom = nom;
        this.prenom = prenom;
        this.idEsp = idEsp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getIdEsp() {
        return idEsp;
    }

    public void setIdEsp(int idEsp) {
        this.idEsp = idEsp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EspaceCopy)) {
            return false;
        }
        EspaceCopy other = (EspaceCopy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.EspaceCopy[ id=" + id + " ]";
    }
    
}
