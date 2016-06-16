




echo $PATH
OSNAME=`uname -s`
DB_PATH=/tmp/applifire/db/S6J2NNKMX7PVQGDNWWZFQ
ART_CREATE_PATH=/tmp/applifire/db/S6J2NNKMX7PVQGDNWWZFQ/art/create
AST_CREATE_PATH=/tmp/applifire/db/S6J2NNKMX7PVQGDNWWZFQ/ast/create
MYSQL=/usr/bin
APPLFIREUSER=root
APPLFIREPASSWORD=root
APPLFIREHOST=localhost

if [ $OSNAME == "Darwin" ]; then
echo "Setting up MYSQL PATH for OS $OSNAME"
MYSQL=/usr/local/mysql/bin/
fi



DB_NAME=salesdb
USER=salesdb
PASSWORD=salesdb
PORT=3306
HOST=localhost


