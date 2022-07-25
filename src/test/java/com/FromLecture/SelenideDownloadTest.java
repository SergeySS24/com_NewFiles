package com.FromLecture;

import com.codeborne.pdftest.PDF;
import com.codeborne.pdftest.matchers.ContainsExactText;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.impl.FileContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;


public class SelenideDownloadTest {

    @Test
    void downloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File textFile = $("#raw-url").download();

        try (InputStream is = new FileInputStream(textFile)){
            byte[] fileContent = is.readAllBytes();
            String strContent = new String(fileContent, StandardCharsets.UTF_8);

            assertThat(strContent).contains("Contributions to JUnit 5");
        }
    }

    @Test
    void pdfParsingTest() throws Exception {
       InputStream stream = getClass().getClassLoader().getResourceAsStream("pdf/junit-user-guide-5.8.2.pdf");
       PDF pdf = new PDF(stream);
        Assertions.assertEquals(166, pdf.numberOfPages);
    }
}
