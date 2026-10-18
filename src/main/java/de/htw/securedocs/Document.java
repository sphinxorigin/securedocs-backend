package de.htw.securedocs;

public class Document {
    private final Long id;
    private final String filename;
    private final String description;

    public Document(Long id, String filename, String description) {
        this.id = id;
        this.filename = filename;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public String getDescription() {
        return description;
    }
}