import pymssql
from env import server,database,username,password

def connect_to_sql_server():
    conn = pymssql.connect(server=server,
                           user=username,
                           password=password,
                           database=database)
    return conn