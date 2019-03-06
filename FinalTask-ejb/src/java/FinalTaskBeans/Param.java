/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalTaskBeans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lesya
 */
@Entity
@Table(name = "PARAM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Param.findAll", query = "SELECT p FROM Param p")
    , @NamedQuery(name = "Param.findByUser", query = "SELECT p FROM Param p WHERE p.paramPK.user = :user")
    , @NamedQuery(name = "Param.findByName", query = "SELECT p FROM Param p WHERE p.paramPK.name = :name")
    , @NamedQuery(name = "Param.findByValue", query = "SELECT p FROM Param p WHERE p.value = :value")})
public class Param implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParamPK paramPK;
    @Column(name = "value")
    private Integer value;
    @JoinColumn(name = "user", referencedColumnName = "user", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Param() {
    }

    public Param(ParamPK paramPK) {
        this.paramPK = paramPK;
    }

    public Param(String user, String name) {
        this.paramPK = new ParamPK(user, name);
    }

    public ParamPK getParamPK() {
        return paramPK;
    }

    public void setParamPK(ParamPK paramPK) {
        this.paramPK = paramPK;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paramPK != null ? paramPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Param)) {
            return false;
        }
        Param other = (Param) object;
        if ((this.paramPK == null && other.paramPK != null) || (this.paramPK != null && !this.paramPK.equals(other.paramPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FinalTaskBeans.Param[ paramPK=" + paramPK + " ]";
    }
    
    
    
}
