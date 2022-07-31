package com.MyCode;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class Attempt1 {

    @Test
    public void downloadFile() throws Exception {
        Selenide.open("https://github.com/junit-team/junit5/blob/main/README.md");
        File newFile = $("#raw-url").download();

        try (InputStream is = new FileInputStream(newFile)) {
                byte[] fileContent = is.readAllBytes();
                String strContent = new  String(fileContent, StandardCharsets.UTF_8);

            assertThat(strContent).contains("Contributions to JUnit 5");
            }

        }

}
