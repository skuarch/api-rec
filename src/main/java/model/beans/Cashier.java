package model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "cashier")
public class Cashier {

    @Id
    @Column(name = "cashier_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    @Min(1)
    private long id;    
    
    @OneToOne
    @JoinColumn(name ="person_id", nullable = false)
    private Person person;
    
    @Column(name = "chashier_is_soft_deleted", columnDefinition = "int default 0")
    private int isSoftDelete = 0;
    
    public Cashier() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getIsSoftDelete() {
        return isSoftDelete;
    }

    public void setIsSoftDelete(int isSoftDelete) {
        this.isSoftDelete = isSoftDelete;
    }
    
}