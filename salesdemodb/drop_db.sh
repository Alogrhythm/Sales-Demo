




echo $PATH
DB_PATH=/tmp/applifire/db/S6J2NNKMX7PVQGDNWWZFQ
MYSQL=/usr/bin
USER=salesdb
PASSWORD=salesdb
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'