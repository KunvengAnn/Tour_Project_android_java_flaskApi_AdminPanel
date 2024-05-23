from flask import request
from flask_restful import Resource
from connection import connect_to_sql_server
import datetime

class CustomerPaymentPKTour(Resource):
    def get(self):
        try:
            conn = connect_to_sql_server()
            cursor = conn.cursor(as_dict=True)

            cursor.execute("EXEC SP_Payment @action='getCustomerPaymentPKTour'")
            results = cursor.fetchall()
            conn.close()

            # print("CustomerPaymentPktour", results)
            # Convert the results to JSON-serializable format
            for result in results:
                for key, value in result.items():
                    if isinstance(value, datetime.date):
                        result[key] = value.isoformat()
                    elif isinstance(value, bool):
                        result[key] = str(value).lower()

            return {'results': results}, 200
        except Exception as e:
            return {'error': str(e)}, 500