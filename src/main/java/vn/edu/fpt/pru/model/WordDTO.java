package vn.edu.fpt.pru.model;

public class WordDTO {
    private Integer id;
    private String key;
    private boolean status;

    public WordDTO() {
    }

    public WordDTO(Integer id, String key, boolean status) {
        this.id = id;
        this.key = key;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
