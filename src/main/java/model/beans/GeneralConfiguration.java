package model.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author skuarch-lap
 */
@Entity
@Table(name = "general_configuration")
public class GeneralConfiguration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "general_configuration_id")        
    private long id;    
    
    @Column(name = "general_configuration_upload_path")
    private String uploadPath;
    
    @Column(name = "general_configuration_url_static_images")
    private String urlStaticImages;
    
    public GeneralConfiguration() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getUrlStaticImages() {
        return urlStaticImages;
    }

    public void setUrlStaticImages(String urlStaticImages) {
        this.urlStaticImages = urlStaticImages;
    }
    
}
