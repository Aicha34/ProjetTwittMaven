package fmin362.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

@Table(name="t_message")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy=GenerationType.SEQUENCE)
	private long id;
	
	@Column(name="label", nullable=false)
    private String label = "";

    @Column( name = "date_message")
    @Temporal( TemporalType.DATE )
    private Date date;
    
    @Column(name="time_message")
    @Temporal(TemporalType.TIME)
    private Date time;
    
    public Message()
    {
        // Constructeur par défaut nécessaire pour JPA
    }

    public Message( long codeMessage,String label, Date date )
    {
        this.id=codeMessage;
    	this.label = label;
        this.date = date;
        
    }

    public Long getId()
    {
        return id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate( Date date )
    {
        this.date = date;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel( String label )
    {
        this.label= label;
    }
    
    
    


}
