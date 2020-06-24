# captures initial state to file specified in
# `liquibase.yaml` file `outputChangeLogFile` property
# based on `prod` link
mvn liquibase:generateChangeLog
