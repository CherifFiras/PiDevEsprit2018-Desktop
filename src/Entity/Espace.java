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

public class Espace {
    
    private Integer id;
    private String titre;
    private String description;
    private String adresse;
    private String photo;
    private int etat;
    private int nbrating;
    private int rating;
    private int idUser;

    public Espace() {
    }
    
    public Espace(Integer id, String titre, String description, String adresse, String photo, int etat, int nbrating, int rating) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.adresse = adresse;
        this.photo = photo;
        this.etat = etat;
        this.nbrating = nbrating;
        this.rating = rating;
    }
    
    public Espace(String titre, String description, String adresse, String photo, int etat, int nbrating, int rating) {
        this.titre = titre;
        this.description = description;
        this.adresse = adresse;
        this.photo = photo;
        this.etat = etat;
        this.nbrating = nbrating;
        this.rating = rating;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getNbrating() {
        return nbrating;
    }

    public void setNbrating(int nbrating) {
        this.nbrating = nbrating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof Espace)) {
            return false;
        }
        Espace other = (Espace) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Espace[ id=" + id + " ]";
    }
    
}
