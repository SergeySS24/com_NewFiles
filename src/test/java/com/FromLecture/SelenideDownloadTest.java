package com.FromLecture;

import com.codeborne.pdftest.PDF;
import com.codeborne.pdftest.matchers.ContainsExactText;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.impl.FileContent;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;


public class SelenideDownloadTest {

    ClassLoader cl = SelenideDownloadTest.class.getClassLoader();

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

    @Test
    void xlsParsingTest() throws Exception {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("xls/18. Файл!!!!_2.xlsx");
        XLS xls = new XLS(stream);
        String stringCellValue = xls.excel.getSheetAt(0).getRow(5).getCell(1).getStringCellValue();
        org.assertj.core.api.Assertions.assertThat(stringCellValue).contains("Зоя");
    }

    @Test
    void csvParsingTest() throws Exception {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("csv/tead.txt");
            CSVReader reader = new CSVReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {

                List<String[]> content = reader.readAll();
                org.assertj.core.api.Assertions.assertThat(content).contains(
                        new String[] {"Name", "Surname"},
                        new String[] {"Sergey","Starostin"},
                        new String[] {"Vadim","Lester"}
                );
            }
    }

    @Test
    void zipParcingTest() throws Exception {
        //ZipFile zf = new ZipFile(new File())
        InputStream is = new ZipInputStream(getClass().getClassLoader().getResourceAsStream("zip/ht_5.zip"));
        ZipEntry entry;
        //while ((entry = is.getNextEntry()) != null ) {
         //   entry.getName(org.assertj.core.api.Assertions.assertThat(entry.getName()).isEqualTo("ht_5.zip");

        }

    }
















