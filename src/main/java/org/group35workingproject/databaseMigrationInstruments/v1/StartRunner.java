package org.group35workingproject.databaseMigrationInstruments.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Slf4j
//@Component
public class StartRunner implements CommandLineRunner {

    private FillDatabase fillDatabase;

    @Override
    public void run(String... args) throws Exception {
        log.info("Load roles into database table ...");
        fillDatabase.fillRoleTable();
        log.info("Done ...");
    }
}
