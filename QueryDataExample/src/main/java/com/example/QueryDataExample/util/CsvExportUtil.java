package com.example.QueryDataExample.util;

import com.example.QueryDataExample.annotation.Excel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CsvExportUtil {
    @SuppressWarnings("java:S3011")
    public static <T> void writeToCsv(List<T> data, Class<T> clazz, Writer out) throws IOException, IllegalAccessException {
        // Get all exportable fields with annotation naming @Excel
        List<Field> exportableFields = Arrays.stream(clazz.getDeclaredFields())
                .filter(c -> c.isAnnotationPresent(Excel.class))
                .sorted(Comparator.comparingInt(f -> f.getAnnotation(Excel.class).order()))
                .toList();

        // Get all header from annotation
        String[] headers = exportableFields.stream()
                .map(f -> f.getAnnotation(Excel.class).name())
                .toArray(String[]::new);

        CSVPrinter csvPrinter = new CSVPrinter(out, CSVFormat.DEFAULT.builder()
                .setHeader(headers)
                .setSkipHeaderRecord(false)
                .setTrim(true)
                .build());

        for (T item : data) {
            List<String> recordList = new ArrayList<>();
            for (Field f : exportableFields) {
                f.setAccessible(true);
                Object value = f.get(item);
                recordList.add(value != null ? value.toString() : "");
            }
            csvPrinter.printRecord(recordList);
        }
        csvPrinter.flush();
    }
}
