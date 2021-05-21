import json
import os

from flask import Flask
from flask_mysqldb import MySQL

app = Flask(__name__)

app.config['MYSQL_HOST'] = '34.101.238.80'
app.config['MYSQL_USER'] = 'user'
app.config['MYSQL_PASSWORD'] = 'Rahasia'
app.config['MYSQL_DB'] = 'BANGKIT'

mysql = MySQL(app)

@app.route("/")
def index():
    return "Hello world"

@app.route('/fetch/<value>', methods=['POST'])
def fetch(value):
    return value

@app.route('/api/get/user/<int:id>', methods=['GET'])
def get_user(id):
    cur = mysql.connection.cursor()
    cur.execute('SELECT * FROM DETAIL_USER WHERE ID_USER =' + str(id))
    res = cur.fetchall()
    cur.close()
    return json.dumps(res)

# @app.route("/adduser")
# def adduser():
#     cur = mysql.connection.cursor()
#     cur.execute('INSERT INTO USER_TABLE(NAME, SKILLS, PICTURE, CITY) VALUES ( \'Christo\', \'Video editor\', '
#                 '\'http://www.speednews-manado.com/wp-content/uploads/2019/08/Christo.jpeg\',\'Jakarta\')')
#     mysql.connection.commit()
#     cur.close()
#     return 'sukses'

if __name__ == '__main__':
    # app.run()
    app.run(host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))