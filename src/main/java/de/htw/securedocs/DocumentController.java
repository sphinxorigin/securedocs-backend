package de.htw.securedocs;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController {

    @GetMapping("/documents")
    public List<Document> getDocuments() {
        return List.of(
            new Document(
                1L,
                "studienbescheinigung.pdf",
                "Studienbescheinigung für das Wintersemester"
            ),
            new Document(
                2L,
                "projektbeschreibung.pdf",
                "Beschreibung des SecureDocs-Projekts"
            )
        );
    }
}