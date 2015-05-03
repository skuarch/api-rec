package model.beans;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author skuarch-lap
 */
public class TestBean {

    private String name;
    private MultipartFile multiPartFile;

    public TestBean() {        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getMultiPartFile() {
        return multiPartFile;
    }

    public void setMultiPartFile(MultipartFile multiPartFile) {
        this.multiPartFile = multiPartFile;
    }

}
