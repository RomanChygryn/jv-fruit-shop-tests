package core.basesyntax.service.impl;

import core.basesyntax.dao.FruitStorageDao;
import core.basesyntax.service.ReportGenerator;
import java.util.Comparator;
import java.util.Map;

public class CsvFormatReportGenerator implements ReportGenerator {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String CSV_SEPARATOR = ",";
    private static final String START_MESSAGE = "fruit,quantity";
    private final FruitStorageDao storageDao;

    public CsvFormatReportGenerator(FruitStorageDao storageDao) {
        this.storageDao = storageDao;
    }

    @Override
    public String create() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(START_MESSAGE);

        storageDao.getAll().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey)) // Sort by fruit name
                .forEach(e -> stringBuilder.append(LINE_SEPARATOR)
                        .append(e.getKey())
                        .append(CSV_SEPARATOR)
                        .append(storageDao.getQuantity(e.getKey())));

        return stringBuilder.toString();
    }
}