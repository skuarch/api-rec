package model.beans;

/**
 *
 * @author skuarch
 */
public class UpdatePasswordBean {

    private long id;
    private String currentPassword;
    private String newPassword;
    private String newPassword2;    
    
    public UpdatePasswordBean() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    @Override
    public String toString() {
        return "id= " + id + " newPassword=" + newPassword + " newPassword2=" + newPassword2;
    } 
    
}